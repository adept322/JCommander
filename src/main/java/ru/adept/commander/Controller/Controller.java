package ru.adept.commander.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;

/**
 * Controller
 * @author adept
 * @version 1.0
 */

public class Controller {

    /**
     * Event handling method
     * @param actionEvent event
     */
    public void btnExitAction(ActionEvent actionEvent) {
        System.out.println("Закрытие программы");
        Platform.exit();
    }
}
