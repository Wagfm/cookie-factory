package br.maciel.factory.exceptions;

import br.maciel.factory.enums.IngredientId;
import br.maciel.utilities.NamesHandler;

public class InvalidIngredientQuantityRequested extends RuntimeException {
    public InvalidIngredientQuantityRequested(IngredientId ingredientId) {
        super("Not a valid " + NamesHandler.getInstance().nameFromId(ingredientId.name()) + " quantity!");
    }
}
