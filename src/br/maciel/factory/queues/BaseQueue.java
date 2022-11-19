package br.maciel.factory.queues;

import br.maciel.factory.cookies.Cookie;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public abstract class BaseQueue implements Runnable {
    private final Queue<Cookie> demands;
    private final Semaphore semaphore;

    public BaseQueue() {
        this.demands = new LinkedList<>();
        this.semaphore = new Semaphore(1);
    }

    public void add(Cookie cookie) {
        try {
            this.semaphore.acquire();
            this.demands.add(cookie);
            this.semaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Cookie poll() {
        if (this.demands.isEmpty()) return null;
        Cookie cookie = null;
        try {
            this.semaphore.acquire();
            cookie = this.demands.poll();
            this.semaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return cookie;
    }

    public int size() {
        return this.demands.size();
    }

    public boolean isEmpty() {
        return this.demands.isEmpty();
    }

    @Override
    public abstract void run();

}
