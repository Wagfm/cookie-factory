package br.maciel.graphics.components.entities;

import br.maciel.graphics.components.RefreshableComponent;
import br.maciel.utilities.constants.Graphic;
import br.maciel.utilities.constants.Palette;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;


public abstract class BaseEntity extends JLabel implements RefreshableComponent {

    public BaseEntity() {
        super();
        this.setLayout(new FlowLayout(FlowLayout.CENTER, -2, -2));
        this.setPreferredSize(new Dimension(Graphic.DEFAULT_WIDTH, Graphic.DEFAULT_HEIGHT));
        this.setVerticalAlignment(CENTER);
        this.setHorizontalAlignment(CENTER);
        this.setBorder(new LineBorder(Palette.BLACK, 2));
        this.setOpaque(true);
        this.setupFont();
    }

    @Override
    public abstract void refresh();

    private void setupFont() {
        this.setFont(new Font(Graphic.DEFAULT_FONT, Font.BOLD, 20));
        this.setForeground(Palette.BLACK);
    }
}
