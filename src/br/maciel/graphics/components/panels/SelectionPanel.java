package br.maciel.graphics.components.panels;

import br.maciel.factory.enums.CookieType;
import br.maciel.graphics.components.indicators.BaseRadioButton;
import br.maciel.utilities.NamesHandler;

import javax.swing.ButtonGroup;
import java.util.HashMap;
import java.util.Map;

public class SelectionPanel extends BasePanel {
    private final Map<CookieType, BaseRadioButton> radioButtons;
    private static SelectionPanel selectionPanel;

    public static SelectionPanel getInstance() {
        if (selectionPanel == null) selectionPanel = new SelectionPanel();
        return selectionPanel;
    }

    public CookieType getSelectedType() {
        for (CookieType type : CookieType.values())
            if (this.radioButtons.get(type).isSelected()) return type;
        return CookieType.PLAIN_COOKIE;
    }

    @Override
    public void refresh() {
        // Nothing to do here at the moment...
    }

    private SelectionPanel() {
        super();
        ButtonGroup group = new ButtonGroup();
        this.radioButtons = new HashMap<>();
        String name;
        for (CookieType type : CookieType.values()) {
            name = NamesHandler.getInstance().nameFromId(type.name());
            name = NamesHandler.getInstance().capitalize(name);
            BaseRadioButton radioButton = new BaseRadioButton(name);
            this.add(radioButton);
            group.add(radioButton);
            this.radioButtons.put(type, radioButton);
        }
        this.radioButtons.get(CookieType.PLAIN_COOKIE).setSelected(true);
    }
}
