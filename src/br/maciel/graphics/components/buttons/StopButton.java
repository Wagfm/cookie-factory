package br.maciel.graphics.components.buttons;

import br.maciel.factory.Factory;
import br.maciel.utilities.constants.Palette;

import java.awt.event.ActionEvent;

public class StopButton extends BaseButton {
    private static StopButton stopButton;

    public static StopButton getInstance() {
        if (stopButton == null) stopButton = new StopButton();
        return stopButton;
    }

    @Override
    public void refresh() {
        this.setEnabled(Factory.getInstance().isRunning());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Factory.getInstance().isRunning()) Factory.getInstance().stop();
    }

    private StopButton() {
        super("Stop", Palette.RED);
        this.addActionListener(this);
    }
}
