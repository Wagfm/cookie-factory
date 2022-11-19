package br.maciel;

import br.maciel.graphics.MainFrame;
import br.maciel.utilities.ThreadPoolHandler;
import br.maciel.utilities.constants.Graphic;

public class Program {
    public static void main(String[] args) {
        ThreadPoolHandler.getInstance().schedule(MainFrame.getInstance(), Graphic.FPS);
    }
}