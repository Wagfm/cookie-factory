package br.maciel.graphics.components.panels;

import br.maciel.graphics.components.RefreshableComponent;
import br.maciel.utilities.constants.Palette;

import javax.swing.JPanel;

public abstract class BasePanel extends JPanel implements RefreshableComponent {
    public BasePanel() {
        super();
        this.setup();
    }

    @Override
    public abstract void refresh();

    private void setup() {
        this.setBackground(Palette.GRAY);
    }
}
