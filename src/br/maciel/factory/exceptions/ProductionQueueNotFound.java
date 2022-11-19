package br.maciel.factory.exceptions;

import br.maciel.factory.enums.BaseQueueId;

public class ProductionQueueNotFound extends RuntimeException {
    public ProductionQueueNotFound(BaseQueueId id) {
        super("Production queue Id=" + id.name() + " not found!");
    }
}
