package br.maciel.factory.daos;

import br.maciel.factory.queues.InputQueue;

public class BaseQueueDao {
    private final int size;

    public BaseQueueDao(InputQueue productionQueue) {
        this.size = productionQueue.size();
    }

    @Override
    public String toString() {
        return "Queued: " + this.size;
    }
}
