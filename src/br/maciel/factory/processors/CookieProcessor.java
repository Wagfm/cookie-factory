package br.maciel.factory.processors;

import br.maciel.factory.cookies.Cookie;

import java.time.Clock;
import java.util.concurrent.Semaphore;

public abstract class CookieProcessor implements Runnable {
    private static int nextId = 1;
    private final int id;
    private volatile Cookie cookie;
    private final Semaphore semaphore;
    private boolean isProcessing;
    private long processStart;

    public CookieProcessor() {
        this.id = nextId++;
        this.cookie = null;
        this.semaphore = new Semaphore(1);
        this.isProcessing = false;
        this.processStart = -1;
    }

    public int getId() {
        return id;
    }

    public Cookie getCookie() {
        Cookie cookie = null;
        try {
            this.semaphore.acquire();
            cookie = this.cookie;
            this.semaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public boolean isProcessing() {
        return isProcessing;
    }

    public void setProcessing(boolean processing) {
        isProcessing = processing;
    }

    public long getProcessStart() {
        return processStart;
    }

    public void setProcessStart() {
        this.processStart = Clock.systemDefaultZone().millis();
    }

    public boolean canPush() {
        return this.cookie == null;
    }

    public void push(Cookie cookie) {
        if (!this.canPush()) return;
        try {
            this.semaphore.acquire();
            this.cookie = cookie;
            this.semaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public abstract double getProgress();

    @Override
    public abstract void run();
}
