package br.maciel.factory.queues;

import br.maciel.factory.cookies.Cookie;
import br.maciel.factory.processors.CookieProcessor;

public class InputQueue extends BaseQueue {
    private final CookieProcessor firstProcessor;

    public InputQueue(CookieProcessor firstProcessor) {
        super();
        this.firstProcessor = firstProcessor;
    }

    @Override
    public void run() {
        if (this.isEmpty() || !this.firstProcessor.canPush()) return;
        Cookie cookie = this.poll();
        cookie.setProcessStart();
        this.firstProcessor.push(cookie);
    }
}
