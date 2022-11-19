package br.maciel.graphics.components.entities;

import br.maciel.factory.Factory;
import br.maciel.factory.enums.BaseQueueId;
import br.maciel.utilities.constants.Palette;

public class QueueEntity extends BaseEntity {
    private final BaseQueueId queueId;

    public QueueEntity(BaseQueueId queueId) {
        super();
        this.queueId = queueId;
        this.setBackground(Palette.GREEN);
    }

    @Override
    public void refresh() {
        this.setText(Factory.getInstance().getInfo(this.queueId).toString());
    }
}
