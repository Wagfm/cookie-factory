package br.maciel.utilities;

import br.maciel.utilities.constants.Simulation;
import br.maciel.factory.cookies.Cookie;
import br.maciel.factory.enums.IngredientId;
import br.maciel.graphics.MainFrame;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.Semaphore;

public class ReportFileHandler {
    private static ReportFileHandler reportFileHandler;
    private final static Semaphore semaphore = new Semaphore(1);

    private ReportFileHandler() {
        try (FileWriter fileWriter = new FileWriter(Simulation.reportFilePath, false)) {
            StringBuilder header = new StringBuilder("type,id,");
            for (IngredientId ingredientId : IngredientId.values())
                header.append(ingredientId.name()).append(",");
            header.append("time_ms\n");
            fileWriter.write(header.toString());
        } catch (IOException e) {
            MainFrame.getInstance().showErrorMessage("Error opening report file! Results will not be saved!");
        }
    }

    public static ReportFileHandler getInstance() {
        if (reportFileHandler == null) reportFileHandler = new ReportFileHandler();
        return reportFileHandler;
    }

    public void writeProductData(Cookie cookie) {
        if (cookie == null) return;
        try {
            semaphore.acquire();
            try (FileWriter fileWriter = new FileWriter(Simulation.reportFilePath, true)) {
                StringBuilder row = new StringBuilder(cookie.getCookieType().name());
                row.append(",");
                row.append(cookie.getId());
                row.append(",");
                DecimalFormat formatter = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
                formatter.applyPattern("0.000");

                for (IngredientId ingredientId : IngredientId.values()) {
                    row.append(formatter.format(cookie.getRequestedQuantity(ingredientId)));
                    row.append(",");
                }
                row.append(cookie.getTimeSpent());
                row.append("\n");
                fileWriter.append(row.toString());
            } catch (IOException e) {
                MainFrame.getInstance().showErrorMessage("Error opening report file! Results will not be saved!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        semaphore.release();
    }
}
