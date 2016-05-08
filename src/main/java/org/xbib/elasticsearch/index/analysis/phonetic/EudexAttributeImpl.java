package org.xbib.elasticsearch.index.analysis.phonetic;

import org.apache.lucene.analysis.tokenattributes.CharTermAttributeImpl;
import org.apache.lucene.util.BytesRef;

import java.nio.charset.StandardCharsets;

public class EudexAttributeImpl extends CharTermAttributeImpl {

    private final Eudex eudex;

    public EudexAttributeImpl() {
        this.eudex = new Eudex();
    }

    @Override
    public BytesRef getBytesRef() {
        byte[] key = toString().getBytes(StandardCharsets.US_ASCII);
        final BytesRef ref = this.builder.get();
        ref.bytes = key;
        ref.offset = 0;
        ref.length = key.length;
        return ref;
    }

    @Override
    public String toString() {
        return Long.toHexString(eudex.encode(super.toString()));
    }
}
