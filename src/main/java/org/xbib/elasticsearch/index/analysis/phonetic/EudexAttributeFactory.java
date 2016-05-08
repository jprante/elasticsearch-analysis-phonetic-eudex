package org.xbib.elasticsearch.index.analysis.phonetic;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.AttributeFactory;

public class EudexAttributeFactory extends AttributeFactory.StaticImplementationAttributeFactory<EudexAttributeImpl> {

    public EudexAttributeFactory() {
        this(TokenStream.DEFAULT_TOKEN_ATTRIBUTE_FACTORY);
    }

    public EudexAttributeFactory(AttributeFactory delegate) {
        super(delegate, EudexAttributeImpl.class);
    }

    @Override
    protected EudexAttributeImpl createInstance() {
        return new EudexAttributeImpl();
    }
}
