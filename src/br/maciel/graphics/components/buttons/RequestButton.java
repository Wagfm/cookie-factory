package br.maciel.graphics.components.buttons;

import br.maciel.factory.Factory;
import br.maciel.factory.enums.IngredientId;
import br.maciel.factory.exceptions.InvalidIngredientQuantityRequested;
import br.maciel.graphics.MainFrame;
import br.maciel.graphics.components.panels.RequestPanel;
import br.maciel.graphics.components.panels.SelectionPanel;
import br.maciel.utilities.constants.Palette;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class RequestButton extends BaseButton {
    private static RequestButton requestButton;

    public static RequestButton getInstance() {
        if (requestButton == null) requestButton = new RequestButton();
        return requestButton;
    }

    @Override
    public void refresh() {
        // Nothing to do for now...
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Map<IngredientId, Double> ingredientsMap = new HashMap<>();
        try {
            for (IngredientId id : IngredientId.values())
                ingredientsMap.put(id, RequestPanel.getInstance().getQuantityById(id));
        } catch (InvalidIngredientQuantityRequested exc) {
            MainFrame.getInstance().showErrorMessage(exc.getMessage());
            return;
        }
        Factory.getInstance().add(ingredientsMap, SelectionPanel.getInstance().getSelectedType());
    }

    private RequestButton() {
        super("Request", Palette.BLUE);
        this.addActionListener(this);
    }
}
