package com.terheyden.threadutils;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Thread-related methods.
 */
public final class ThreadUtils {

    private static final Logger LOG = getLogger(ThreadUtils.class);

    private ThreadUtils() {
        // Private constructor since this shouldn't be instantiated.
    }

    /**
     * Unchecked version of {@link Thread#sleep(long)}.
     */
    @SuppressWarnings("unchecked")
    public static <E extends Throwable> void sleep(long millis) throws E {
        try {

            LOG.debug("Sleeping for {} ms.", millis);
            Thread.sleep(millis);

        } catch (Throwable err) {
            throw (E) err;
        }
    }
}
