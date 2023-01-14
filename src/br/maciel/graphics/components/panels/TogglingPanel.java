package br.maciel.graphics.components.panels;

import br.maciel.graphics.components.buttons.BaseButton;
import br.maciel.graphics.components.buttons.StartButton;
import br.maciel.graphics.components.buttons.StopButton;
import br.maciel.utilities.constants.Graphic;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class TogglingPanel extends BasePanel {
    private final List<BaseButton> baseButtons;
    private static TogglingPanel togglingPanel;

    public static TogglingPanel getInstance() {
        if (togglingPanel == null) togglingPanel = new TogglingPanel();
        return togglingPanel;
    }

    @Override
    public void refresh() {
        for (BaseButton button : this.baseButtons)
            button.refresh();
    }

    private TogglingPanel() {
        super();
        this.baseButtons = new ArrayList<>();
        this.setupButtonsList();
        this.setup();
    }

    private void setupButtonsList() {
        this.baseButtons.add(StartButton.getInstance());
        this.baseButtons.add(StopButton.getInstance());
    }

    private void setup() {
        this.setLayout(new GridLayout(1, 2, 10, 0));
        this.setPreferredSize(new Dimension(Graphic.PANEL_WIDTH, 100));
        for (BaseButton button : this.baseButtons)
            this.add(button);
    }
}
