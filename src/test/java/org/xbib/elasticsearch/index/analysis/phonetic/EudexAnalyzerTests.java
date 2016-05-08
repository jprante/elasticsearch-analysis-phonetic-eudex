package org.xbib.elasticsearch.index.analysis.phonetic;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.elasticsearch.index.analysis.AnalysisService;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.junit.Assert;
import org.junit.Test;
import org.xbib.elasticsearch.MapperTestUtils;

import java.io.IOException;
import java.io.StringReader;

public class EudexAnalyzerTests extends Assert {

    @Test
    public void testAnalyzer() throws IOException {

        String source = "eins zwei drei";

        String[] expected = {
                "d81214940018a100"
        };
        AnalysisService analysisService = MapperTestUtils.analysisService("eudex_analysis.json");
        Analyzer analyzer = analysisService.analyzer("my_phonetic");
        assertSimpleTSOutput(analyzer.tokenStream(null, new StringReader(source)), expected);
    }

    @Test
    public void testTokenizerFilter() throws IOException {

        String source = "eins zwei drei";

        String[] expected = {
                "d800000000001214",
                "4a00000000000000",
                "c0000000000a100"
        };
        AnalysisService analysisService = MapperTestUtils.analysisService("eudex_analysis.json");
        TokenFilterFactory tokenFilterFactory = analysisService.tokenFilter("my_phonetic");
        Tokenizer tokenizer = analysisService.tokenizer("my_phonetic").create();
        tokenizer.setReader(new StringReader(source));
        assertSimpleTSOutput(tokenFilterFactory.create(tokenizer), expected);
    }

    private void assertSimpleTSOutput(TokenStream stream, String[] expected) throws IOException {
        stream.reset();
        CharTermAttribute termAttr = stream.getAttribute(CharTermAttribute.class);
        assertNotNull(termAttr);
        int i = 0;
        while (stream.incrementToken()) {
            assertTrue(i < expected.length);
            assertEquals(expected[i], termAttr.toString());
            i++;
        }
        assertEquals(i, expected.length);
        stream.close();
    }
}
