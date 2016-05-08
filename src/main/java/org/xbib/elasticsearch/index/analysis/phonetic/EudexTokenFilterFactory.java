package org.xbib.elasticsearch.index.analysis.phonetic;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.settings.IndexSettingsService;

public class EudexTokenFilterFactory extends AbstractTokenFilterFactory {

    @Inject
    public EudexTokenFilterFactory(Index index,
                                      IndexSettingsService indexSettingsService,
                                      @Assisted String name,
                                      @Assisted Settings settings) {
        super(index, indexSettingsService.indexSettings(), name, settings);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new EudexTokenFilter(tokenStream);
    }

}

