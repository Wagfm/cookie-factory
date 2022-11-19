package br.maciel.graphics.components.forms;

import br.maciel.factory.enums.IngredientId;
import br.maciel.factory.exceptions.InvalidIngredientQuantityRequested;
import br.maciel.utilities.NamesHandler;

public class IngredientForm extends BaseForm {
    private final IngredientId ingredientId;

    public IngredientForm(IngredientId ingredientId) {
        super(NamesHandler.getInstance().capitalize(NamesHandler.getInstance().nameFromId(ingredientId.name())));
        this.ingredientId = ingredientId;
    }

    public double getQuantity() {
        try {
            return Double.parseDouble(this.getFieldText());
        } catch (NumberFormatException | NullPointerException e) {
            this.setFieldText("Invalid!");
            throw new InvalidIngredientQuantityRequested(this.ingredientId);
        }
    }
}
