package com.terheyden.threadutils;

import javax.annotation.Nullable;

import io.vavr.CheckedRunnable;

/**
 * Builder class for creating {@link Thread} objects.
 * Can be reused to build multiple threads.
 */
public final class ThreadBuilder {

    private CheckedRunnable runnable;
    @Nullable private String name;
    private boolean isDaemon;

    private ThreadBuilder(CheckedRunnable runnable) {
        this.runnable = runnable;
    }

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain Thread#Thread(ThreadGroup,Runnable,String) Thread}
     * {@code (null, target, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     *
     * @param target
     *         the object whose {@code run} method is invoked when this thread
     *         is started. If {@code null}, this classes {@code run} method does
     *         nothing.
     */
    public static ThreadBuilder runner(CheckedRunnable target) {
        return new ThreadBuilder(target);
    }

    /**
     * Specify the Runnable to invoke when the thread is started.
     */
    public ThreadBuilder runnable(CheckedRunnable target) {
        runnable = target;
        return this;
    }

    public CheckedRunnable runnable() {
        return runnable;
    }

    /**
     * Changes the name of this thread to be equal to the argument {@code name}.
     * <p>
     * First the {@code checkAccess} method of this thread is called
     * with no arguments. This may result in throwing a
     * {@code SecurityException}.
     *
     * @param name the new name for this thread.
     * @throws SecurityException if the current thread cannot modify this
     *             thread.
     * @see        Thread#getName
     * @see        Thread#checkAccess()
     */
    public ThreadBuilder name(String threadName) {
        name = threadName;
        return this;
    }

    @Nullable
    public String name() {
        return name;
    }

    /**
     * Marks this thread as either a {@linkplain Thread#isDaemon daemon} thread
     * or a user thread. The Java Virtual Machine exits when the only
     * threads running are all daemon threads.
     *
     * <p> This method must be invoked before the thread is started.
     *
     * @throws IllegalThreadStateException
     *          if this thread is {@linkplain Thread#isAlive alive}
     *
     * @throws SecurityException
     *          if {@link Thread#checkAccess} determines that the current
     *          thread cannot modify this thread
     */
    public ThreadBuilder setDaemon(boolean isDaemon) {
        this.isDaemon = isDaemon;
        return this;
    }

    public boolean isDaemon() {
        return isDaemon;
    }

    /**
     * Creates and returns a new {@link Thread} instance without starting it.
     * Each time this method is called, a new, separate Thread object is
     * created and returned.
     */
    public Thread build() {

        Thread thread = new Thread(runnable.unchecked());

        if (name != null) {
            thread.setName(name);
        }

        thread.setDaemon(isDaemon);
        return thread;
    }

    /**
     * Builds a new {@link Thread} instance from the given settings, and starts the thread.
     * Starting the thread causes execution to begin; the Java Virtual Machine
     * calls the {@code run} method of the thread.
     * <p>
     * The result is that two threads are running concurrently: the
     * current thread (which returns from the call to the
     * {@code start} method) and the other thread (which executes its
     * {@code run} method).
     * <p>
     * If this method is called more than once, a new, separate Thread will be created
     * each time.
     *
     * @see    Thread#run()
     * @return the started {@link Thread} object
     */
    public Thread buildAndStart() {
        Thread thread = build();
        thread.start();
        return thread;
    }
}
