package br.maciel.graphics.components.indicators;

import br.maciel.factory.Factory;
import br.maciel.factory.enums.CookieProcessorId;
import br.maciel.graphics.components.entities.BaseEntity;

import java.awt.Dimension;

public class ProgressBar extends BaseProgressBar {
    private final BaseEntity parentEntity;
    private final CookieProcessorId processorId;

    public ProgressBar(BaseEntity parentEntity, CookieProcessorId processorId) {
        super();
        this.parentEntity = parentEntity;
        this.processorId = processorId;
    }

    @Override
    public void refresh() {
        this.setPreferredSize(new Dimension(this.parentEntity.getSize().width, 25));
        this.updateProgress(Factory.getInstance().getProgress(this.processorId), Factory.getInstance().isRunning());
    }

    private void updateProgress(double progress, boolean isRunning) {
        int value = (int) (progress * 100);
        this.setValue(value);
        if (value >= 100) {
            if (isRunning) this.setString("Waiting...");
            else this.setString("STOPPED");
        } else {
            this.setString(value + "%");
        }
    }
}
