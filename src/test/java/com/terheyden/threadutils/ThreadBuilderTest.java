package com.terheyden.threadutils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * ThreadBuilderTest unit tests.
 */
public class ThreadBuilderTest {

    private static final Logger LOG = getLogger(ThreadBuilderTest.class);
    private static final String NAME = "my thread";

    @Test
    public void test() {

        ThreadBuilder threadBuilder = ThreadBuilder
            .runner(() -> LOG.info("Running from thread."))
            .name(NAME)
            .setDaemon(true);

        assertNotNull(threadBuilder.runnable());
        assertEquals(NAME, threadBuilder.name());
        assertTrue(threadBuilder.isDaemon());
        assertNotNull(threadBuilder.buildAndStart());
    }
}
