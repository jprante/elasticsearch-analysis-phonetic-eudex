package org.xbib.elasticsearch.index.analysis.phonetic;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PackedTokenAttributeImpl;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.AttributeSource;

import java.io.IOException;
import java.nio.charset.CharacterCodingException;

public class EudexTokenFilter extends TokenFilter {

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);

    private final PositionIncrementAttribute posIncAtt = addAttribute(PositionIncrementAttribute.class);

    private AttributeSource.State current;

    private final Eudex eudex;

    protected EudexTokenFilter(TokenStream input) {
        super(input);
        this.eudex = new Eudex();
    }

    @Override
    public final boolean incrementToken() throws IOException {
        if (input.incrementToken()) {
            PackedTokenAttributeImpl token = eudex();
            restoreState(current);
            termAtt.setEmpty().append(token);
            offsetAtt.setOffset(token.startOffset(), token.endOffset());
            posIncAtt.setPositionIncrement(0);
            current = captureState();
            return true;
        } else {
            return false;
        }
    }

    protected PackedTokenAttributeImpl  eudex() throws CharacterCodingException {
        String term = new String(termAtt.buffer(), 0, termAtt.length());
        CharSequence s = Long.toHexString(eudex.encode(term));
        PackedTokenAttributeImpl impl = new PackedTokenAttributeImpl();
        impl.append(s);
        return impl;
    }
}
