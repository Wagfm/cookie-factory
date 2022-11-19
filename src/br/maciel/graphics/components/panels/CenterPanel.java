package br.maciel.graphics.components.panels;

import br.maciel.factory.enums.BaseQueueId;
import br.maciel.factory.enums.CookieProcessorId;
import br.maciel.graphics.MainFrame;
import br.maciel.graphics.components.RefreshableComponent;
import br.maciel.graphics.components.entities.BaseEntity;
import br.maciel.graphics.components.entities.ProcessorEntity;
import br.maciel.graphics.components.entities.QueueEntity;
import br.maciel.utilities.constants.Graphic;
import br.maciel.utilities.constants.Palette;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class CenterPanel extends BasePanel {
    private final List<RefreshableComponent> entities;
    private final JPanel superior;
    private final JPanel inferior;
    private static CenterPanel centerPanel;

    public static CenterPanel getInstance() {
        if (centerPanel == null) centerPanel = new CenterPanel();
        return centerPanel;
    }

    private CenterPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.entities = new ArrayList<>();
        this.superior = new JPanel(new GridLayout(5, 3, 10, 10));
        this.inferior = new JPanel(new GridLayout(2, 4, 10, 10));
        this.setupCenterPanel();
    }

    @Override
    public void refresh() {
        try {
            for (RefreshableComponent entity : this.entities)
                entity.refresh();
        } catch (RuntimeException e) {
            MainFrame.getInstance().showErrorMessage(e.getMessage());
        }
    }

    private void setupCenterPanel() {
        this.setOpaque(true);
        this.setupSuperior();
        this.setupInferior();
        this.add(this.superior);
        this.add(Box.createVerticalStrut(10));
        this.add(this.inferior);
    }

    private void setupSuperior() {
        this.superior.setBackground(Palette.GRAY);
        BaseEntity queue;
        for (BaseQueueId id : BaseQueueId.values()) {
            queue = new QueueEntity(id);
            this.superior.add(queue);
            this.entities.add(queue);
        }
        for (int i = 0; i < 3; i++)
            this.superior.add(new JLabel(new ImageIcon(this.getScaledImage("arrow"))));
        BaseEntity mixer;
        for (CookieProcessorId id : CookieProcessorId.values()) {
            if (!id.name().toLowerCase().contains("mixer")) continue;
            mixer = new ProcessorEntity(id);
            this.superior.add(mixer);
            this.entities.add(mixer);
        }
    }

    private Image getScaledImage(String imageName) {
        Image image = new ImageIcon(Graphic.BASE_PATH + imageName + ".png").getImage();
        return image.getScaledInstance(Graphic.DEFAULT_WIDTH / 2, Graphic.DEFAULT_HEIGHT / 2, Image.SCALE_SMOOTH);
    }

    private void setupInferior() {
        this.inferior.setBackground(Palette.GRAY);
        this.inferior.add(new JLabel(""));
        this.inferior.add(new JLabel(new ImageIcon(this.getScaledImage("arrow"))));
        this.inferior.add(new JLabel(new ImageIcon(this.getScaledImage("arrow"))));
        this.inferior.add(new JLabel(""));
        this.inferior.add(new JLabel(""));
        BaseEntity oven;
        for (CookieProcessorId id : CookieProcessorId.values()) {
            if (!id.name().toLowerCase().contains("oven")) continue;
            oven = new ProcessorEntity(id);
            this.inferior.add(oven);
            this.entities.add(oven);
        }
        this.inferior.add(new JLabel(""));
    }
}
