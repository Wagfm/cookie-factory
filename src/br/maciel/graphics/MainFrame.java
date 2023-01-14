package br.maciel.graphics;

import br.maciel.graphics.components.RefreshableComponent;
import br.maciel.graphics.components.panels.CenterPanel;
import br.maciel.graphics.components.panels.WestPanel;
import br.maciel.utilities.constants.Graphic;
import br.maciel.utilities.constants.Palette;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame implements Runnable {
    private final List<RefreshableComponent> components;
    private final List<String> messagesShown;
    private static MainFrame mainFrame;

    public static MainFrame getInstance() {
        if (mainFrame == null) mainFrame = new MainFrame();
        return mainFrame;
    }

    @Override
    public void run() {
        for (RefreshableComponent component : this.components)
            component.refresh();
    }

    public void showErrorMessage(String errorMessage) {
        for (String message : this.messagesShown)
            if (message.equals(errorMessage)) return;
        if (!errorMessage.contains("quantity")) this.messagesShown.add(errorMessage);
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private MainFrame() {
        super("Cookie Factory");
        this.messagesShown = new ArrayList<>();
        this.components = new ArrayList<>();
        this.setupMainFrame();
    }

    private void setupMainFrame() {
        this.setIconImage(new ImageIcon(Graphic.ICON_PATH).getImage());
        JPanel panel = new JPanel();
        panel.setBackground(Palette.GRAY);
        panel.setLayout(new BorderLayout(5, 0));
        panel.add(CenterPanel.getInstance(), BorderLayout.CENTER);
        this.components.add(CenterPanel.getInstance());
        panel.add(WestPanel.getInstance(), BorderLayout.WEST);
        this.components.add(WestPanel.getInstance());
        this.add(panel);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
