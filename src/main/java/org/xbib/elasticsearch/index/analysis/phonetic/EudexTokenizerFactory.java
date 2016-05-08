package org.xbib.elasticsearch.index.analysis.phonetic;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;
import org.elasticsearch.index.settings.IndexSettingsService;

public class EudexTokenizerFactory extends AbstractTokenizerFactory {

    private final EudexAttributeFactory factory;

    private final int bufferSize;

    @Inject
    public EudexTokenizerFactory(Index index,
                                 IndexSettingsService indexSettingsService,
                                 @Assisted String name,
                                 @Assisted Settings settings) {
        super(index, indexSettingsService.indexSettings(), name, settings);
        this.factory = new EudexAttributeFactory();
        this.bufferSize = settings.getAsInt("buffersize", KeywordTokenizer.DEFAULT_BUFFER_SIZE);
    }

    @Override
    public Tokenizer create() {
        return new KeywordTokenizer(factory, bufferSize);
    }
}
