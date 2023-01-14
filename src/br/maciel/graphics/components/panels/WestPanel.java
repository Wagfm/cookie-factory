package br.maciel.graphics.components.panels;

import br.maciel.utilities.constants.Graphic;
import br.maciel.utilities.constants.Palette;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;

public class WestPanel extends BasePanel {
    private static WestPanel westPanel;

    public static WestPanel getInstance() {
        if (westPanel == null) westPanel = new WestPanel();
        return westPanel;
    }

    private WestPanel() {
        super();
        this.setupWestPanel();
    }

    @Override
    public void refresh() {
        // Nothing to do here at the moment...
    }


    private void setupWestPanel() {
        this.setLayout(new BorderLayout(5, 200));
        this.setPreferredSize(new Dimension(Graphic.PANEL_WIDTH, 200));
        this.setBackground(Palette.GRAY);
        this.setOpaque(true);
        this.setBorder(new BevelBorder(BevelBorder.RAISED));
        this.add(TogglingPanel.getInstance(), BorderLayout.SOUTH);
        this.add(RequestPanel.getInstance(), BorderLayout.CENTER);
    }
}
