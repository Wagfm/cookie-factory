package br.maciel.graphics.components.entities;

import br.maciel.factory.Factory;
import br.maciel.factory.enums.CookieProcessorId;
import br.maciel.graphics.components.indicators.BaseProgressBar;
import br.maciel.graphics.components.indicators.ProgressBar;
import br.maciel.utilities.constants.Palette;

public class ProcessorEntity extends BaseEntity {
    private final CookieProcessorId processorId;
    private final BaseProgressBar progressBar;

    public ProcessorEntity(CookieProcessorId processorId) {
        super();
        this.processorId = processorId;
        this.progressBar = new ProgressBar(this, this.processorId);
        this.add(this.progressBar);

        // TODO: implement entity image handling instead of solid color
        if (processorId.name().toLowerCase().contains("mixer")) this.setBackground(Palette.YELLOW);
        else if (processorId.name().toLowerCase().contains("oven")) this.setBackground(Palette.RED);
    }

    @Override
    public void refresh() {
        this.setText(Factory.getInstance().getInfo(this.processorId).toString());
        this.progressBar.refresh();
    }


}
