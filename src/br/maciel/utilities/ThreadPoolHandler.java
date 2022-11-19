package br.maciel.utilities;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ThreadPoolHandler {
    private final ScheduledExecutorService threads;
    private static ThreadPoolHandler threadPoolHandler;

    public static ThreadPoolHandler getInstance() {
        if (threadPoolHandler == null) threadPoolHandler = new ThreadPoolHandler();
        return threadPoolHandler;
    }

    public ScheduledFuture<?> schedule(Runnable runnable, int rate) {
        return this.threads.scheduleAtFixedRate(runnable, 0, 1000 / rate, TimeUnit.MILLISECONDS);
    }

    public void submit(Runnable runnable) {
        this.threads.submit(runnable);
    }

    private ThreadPoolHandler() {
        this.threads = Executors.newScheduledThreadPool(24);
    }
}
