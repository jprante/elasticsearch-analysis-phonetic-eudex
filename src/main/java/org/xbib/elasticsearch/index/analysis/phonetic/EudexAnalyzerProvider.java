package org.xbib.elasticsearch.index.analysis.phonetic;

import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;
import org.elasticsearch.index.settings.IndexSettingsService;

public class EudexAnalyzerProvider extends AbstractIndexAnalyzerProvider<EudexAnalyzer> {

    private final int bufferSize;

    @Inject
    public EudexAnalyzerProvider(Index index,
                                 IndexSettingsService indexSettingsService,
                                 @Assisted String name,
                                 @Assisted Settings settings) {
        super(index, indexSettingsService.indexSettings(), name, settings);
        this.bufferSize = settings.getAsInt("buffersize", KeywordTokenizer.DEFAULT_BUFFER_SIZE);
    }

    @Override
    public EudexAnalyzer get() {
        return new EudexAnalyzer(bufferSize);
    }
}
