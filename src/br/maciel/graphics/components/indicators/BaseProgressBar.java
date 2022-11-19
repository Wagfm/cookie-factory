package br.maciel.graphics.components.indicators;

import br.maciel.graphics.components.RefreshableComponent;
import br.maciel.utilities.constants.Graphic;
import br.maciel.utilities.constants.Palette;

import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import java.awt.Font;

public abstract class BaseProgressBar extends JProgressBar implements RefreshableComponent {
    public BaseProgressBar() {
        super();
        this.setup();
    }

    private void setup() {
        this.setFont(new Font(Graphic.DEFAULT_FONT, Font.BOLD, 15));
        this.setForeground(Palette.GREEN);
        this.setStringPainted(true);
        this.setBorder(new LineBorder(Palette.BLACK, 2));
    }

    @Override
    public abstract void refresh();
}
