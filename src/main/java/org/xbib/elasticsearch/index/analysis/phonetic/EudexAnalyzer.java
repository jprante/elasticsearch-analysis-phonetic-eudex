package org.xbib.elasticsearch.index.analysis.phonetic;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordTokenizer;

public class EudexAnalyzer extends Analyzer {

    private final EudexAttributeFactory factory;

    private final int bufferSize;

    public EudexAnalyzer(int bufferSize) {
        this.factory = new EudexAttributeFactory();
        this.bufferSize = bufferSize;
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        KeywordTokenizer tokenizer = new KeywordTokenizer(factory, bufferSize);
        return new TokenStreamComponents(tokenizer, tokenizer);
    }

}
