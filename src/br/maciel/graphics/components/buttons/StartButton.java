package br.maciel.graphics.components.buttons;

import br.maciel.factory.Factory;
import br.maciel.utilities.ThreadPoolHandler;
import br.maciel.utilities.constants.Palette;

import java.awt.event.ActionEvent;

public class StartButton extends BaseButton {
    private static StartButton startButton;

    public static StartButton getInstance() {
        if (startButton == null) startButton = new StartButton();
        return startButton;
    }

    @Override
    public void refresh() {
        this.setEnabled(!Factory.getInstance().isRunning());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Factory.getInstance().isRunning()) ThreadPoolHandler.getInstance().submit(Factory.getInstance());
    }

    private StartButton() {
        super("Start", Palette.GREEN);
        this.addActionListener(this);
    }
}
