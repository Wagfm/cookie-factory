package br.maciel.graphics.components.panels;

import br.maciel.factory.enums.CookieType;
import br.maciel.factory.enums.IngredientId;
import br.maciel.graphics.components.buttons.RequestButton;
import br.maciel.graphics.components.forms.IngredientForm;
import br.maciel.utilities.constants.Graphic;
import br.maciel.utilities.constants.Palette;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

public class RequestPanel extends BasePanel {
    private final JLabel titleLabel;
    private final Map<IngredientId, IngredientForm> ingredientForms;
    private static RequestPanel requestPanel;

    public static RequestPanel getInstance() {
        if (requestPanel == null) requestPanel = new RequestPanel();
        return requestPanel;
    }

    public double getQuantityById(IngredientId ingredientId) {
        return this.ingredientForms.get(ingredientId).getQuantity();
    }

    @Override
    public void refresh() {
        // Nothing to do for the moment...
    }

    private RequestPanel() {
        super();
        this.ingredientForms = new HashMap<>();
        this.titleLabel = new JLabel("Request Cookies");
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.setup();
    }

    private void setup() {
        this.setLayout(new GridLayout(6, 1, 0, 50));
        this.titleLabel.setFont(new Font(Graphic.DEFAULT_FONT, Font.BOLD, 30));
        this.titleLabel.setForeground(Palette.BLACK);
        this.add(this.titleLabel);
        IngredientForm form;
        for (IngredientId id : IngredientId.values()) {
            form = new IngredientForm(id);
            this.ingredientForms.put(id, form);
            this.add(form);
        }
        this.add(SelectionPanel.getInstance());
        this.add(RequestButton.getInstance());
        this.setPreferredSize(new Dimension(250, 100));
    }
}
