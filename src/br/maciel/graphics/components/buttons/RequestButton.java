package br.maciel.graphics.components.buttons;

import br.maciel.factory.Factory;
import br.maciel.factory.cookies.Cookie;
import br.maciel.factory.cookies.PlainCookie;
import br.maciel.factory.cookies.StuffedCookie;
import br.maciel.factory.enums.IngredientId;
import br.maciel.factory.exceptions.InvalidIngredientQuantityRequested;
import br.maciel.graphics.MainFrame;
import br.maciel.graphics.components.panels.RequestPanel;
import br.maciel.graphics.components.panels.SelectionPanel;
import br.maciel.utilities.constants.Palette;

import java.awt.event.ActionEvent;

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
        Cookie cookie;
        switch (SelectionPanel.getInstance().getSelectedType()) {
            case PLAIN_COOKIE -> cookie = new PlainCookie();
            case STUFFED_COOKIE -> cookie = new StuffedCookie();
            default -> cookie = null;
        }
        if (cookie == null) return;
        try {
            for (IngredientId id : IngredientId.values())
                cookie.setRequestedIngredient(id, RequestPanel.getInstance().getQuantityById(id));
        } catch (InvalidIngredientQuantityRequested exc) {
            MainFrame.getInstance().showErrorMessage(exc.getMessage());
            return;
        }
        Factory.getInstance().add(cookie);
    }

    private RequestButton() {
        super("Request", Palette.BLUE);
        this.addActionListener(this);
    }
}
