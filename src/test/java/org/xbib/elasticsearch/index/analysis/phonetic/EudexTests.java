package org.xbib.elasticsearch.index.analysis.phonetic;

import org.junit.Assert;
import org.junit.Test;

public class EudexTests extends Assert {

    @Test
    public void testExact() throws Exception {
        Eudex eudex = new Eudex();
        assertEquals(eudex.encode("JAva"), eudex.encode("jAva"));
        assertEquals(eudex.encode("co!mputer"), eudex.encode("computer"));
        assertEquals(eudex.encode("comp@u#te?r"), eudex.encode("computer"));
        assertEquals(eudex.encode("java"), eudex.encode("jiva"));
        assertEquals(eudex.encode("lal"), eudex.encode("lel"));
        assertEquals(eudex.encode("rindom"), eudex.encode("ryndom"));
        assertEquals(eudex.encode("riiiindom"), eudex.encode("ryyyyyndom"));
        assertEquals(eudex.encode("riyiyiiindom"), eudex.encode("ryyyyyndom"));
        assertEquals(eudex.encode("triggered"), eudex.encode("TRIGGERED"));
        assertEquals(eudex.encode("repert"), eudex.encode("ropert"));
    }

    @Test
    public void testMismatch() {
        Eudex eudex = new Eudex();
        assertNotEquals(eudex.encode("reddit"), eudex.encode("eddit"));
        assertNotEquals(eudex.encode("lol"), eudex.encode("lulz"));
        assertNotEquals(eudex.encode("ijava"), eudex.encode("java"));
        assertNotEquals(eudex.encode("jesus"), eudex.encode("iesus"));
        assertNotEquals(eudex.encode("aesus"), eudex.encode("iesus"));
        assertNotEquals(eudex.encode("iesus"), eudex.encode("yesus"));
        assertNotEquals(eudex.encode("rupirt"), eudex.encode("ropert"));
        assertNotEquals(eudex.encode("rupirt"), eudex.encode("ropert"));
        assertNotEquals(eudex.encode("ripert"), eudex.encode("ropyrt"));
        assertNotEquals(eudex.encode("rrr"), eudex.encode("rraaaa"));
        assertNotEquals(eudex.encode("randomal"), eudex.encode("randomai"));
    }

    @Test
    public void testDistance() {
        Eudex eudex = new Eudex();
        assertTrue(eudex.distance("lizzard", "wizzard") > eudex.distance("rick", "rolled"));
        assertTrue(eudex.distance("bannana", "panana") >= eudex.distance("apple", "abple"));
        assertTrue(eudex.distance("trump", "drumpf") < eudex.distance("gangam", "style"));
        // implementation dependent tests
        assertTrue(64 - Long.bitCount(eudex.distance("right", "write")) > 64 - Long.bitCount(eudex.distance("write", "abdominohysterotomy")));
        assertEquals(Long.bitCount(eudex.distance("redox", "linux")), 3);
    }

    @Test
    public void testReflexivity() {
        Eudex eudex = new Eudex();
        assertEquals(eudex.distance("a", "b"), eudex.distance("b", "a"));
        assertEquals(eudex.distance("youtube", "facebook"), eudex.distance("facebook", "youtube"));
        assertEquals(eudex.distance("Rust", "Go"), eudex.distance("Go", "Rust"));
        assertEquals(eudex.distance("rick", "rolled"), eudex.distance("rolled", "rick"));
    }

    @Test
    public void testSimilar() {
        Eudex eudex = new Eudex();
        assertTrue(eudex.similar("yay", "yuy"));
        assertTrue(eudex.similar("what", "wat"));
        assertTrue(eudex.similar("", ""));
        assertTrue(eudex.similar("jumpo", "jumbo"));
        assertTrue(eudex.similar("lol", "lulz"));
        assertTrue(eudex.similar("maier", "meyer"));
        assertTrue(eudex.similar("möier", "meyer"));
        assertTrue(eudex.similar("fümlaut", "fymlaut"));
        assertTrue(eudex.similar("fümlaut", "fymlaut"));

        // implementation dependent tests
        assertTrue(Long.bitCount(eudex.distance("crack", "crakk")) < 10);
        assertTrue(Long.bitCount(eudex.distance("schmid", "schmidt")) < 14);
    }

    @Test
    public void testNotSimilar() {
        Eudex eudex = new Eudex();
        assertFalse(eudex.similar("youtube", "reddit"));
        assertFalse(eudex.similar("yet", "vet"));
        assertFalse(eudex.similar("hacker", "4chan"));
        assertFalse(eudex.similar("awesome", "me"));
        assertFalse(eudex.similar("prisco", "vkisco"));
        assertFalse(eudex.similar("no", "go"));
        assertFalse(eudex.similar("horse", "norse"));
        assertFalse(eudex.similar("nice", "mice"));
    }
}
