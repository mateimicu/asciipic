package com.asciipic.crawl.transformers;

public interface Transformer<S, T> {
    T transform(S entity);
}
