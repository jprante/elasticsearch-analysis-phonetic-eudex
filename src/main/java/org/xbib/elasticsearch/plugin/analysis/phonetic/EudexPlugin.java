package org.xbib.elasticsearch.plugin.analysis.phonetic;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.plugins.Plugin;
import org.xbib.elasticsearch.index.analysis.phonetic.EudexAnalysisBinderProcessor;

public class EudexPlugin extends Plugin {

    private final Settings settings;

    @Inject
    public EudexPlugin(Settings settings) {
        this.settings = settings;
    }

    @Override
    public String name() {
        return "analysis-phonetic-eudex";
    }

    @Override
    public String description() {
        return "Eudex phonetic analysis";
    }

    public void onModule(AnalysisModule module) {
        if (settings.getAsBoolean("plugins.analysis.eudex.enabled", true)) {
            module.addProcessor(new EudexAnalysisBinderProcessor());
        }
    }
}
