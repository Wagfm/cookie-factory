package br.maciel.factory.queues;

import br.maciel.factory.cookies.Cookie;
import br.maciel.utilities.ReportFileHandler;

public class OutputQueue extends BaseQueue implements Runnable {

    private static OutputQueue outputQueue;

    public static OutputQueue getInstance() {
        if (outputQueue == null) outputQueue = new OutputQueue();
        return outputQueue;
    }

    @Override
    public void run() {
        Cookie cookie = this.poll();
        ReportFileHandler.getInstance().writeProductData(cookie);
    }

    private OutputQueue() {
        super();
    }
}
