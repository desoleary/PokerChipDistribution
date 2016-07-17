package com.solium.pcd.util;

import org.junit.Assert;

import java.text.MessageFormat;
import java.util.Collection;

public class TestCase extends Assert {

    protected void assertSize(int expected, Collection<?> collection) {
        String errorMessage = MessageFormat.format("The collection should have had {0} items, instead it had {1} items.",
                expected, collection.size());
        assertEquals(errorMessage, expected, collection.size());
    }
}
