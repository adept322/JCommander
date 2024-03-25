package ru.adept.commander;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Controller
 * Scene Handler {main.fxml}
 *
 * @author adept
 * @version 1.0
 */

public class Controller implements Initializable {
    @FXML
    TableView<FileInformation> filesTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn<FileInformation, String> fileTypeColumn = new TableColumn<>();
        fileTypeColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getType().getName()));
        fileTypeColumn.setPrefWidth(20);


        TableColumn<FileInformation, String> fileNameColumn = new TableColumn<>("Имя");
        fileNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFilename()));
        fileNameColumn.setPrefWidth(240);



        filesTable.getColumns().addAll(fileTypeColumn, fileNameColumn);
        filesTable.getSortOrder().add(fileTypeColumn);
        updateList(Paths.get("."));
    }

    /** Method that updates the contents of the file table {filesTable}
     * @param path the directory for which the active file table needs to be updated
     */
    public void updateList(Path path) {
        try {
            filesTable.getItems().clear();
            filesTable.getItems().addAll(Files.list(path).map(FileInformation::new).collect(Collectors.toList()));
            filesTable.sort();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Не удалось обновить список файлов", ButtonType.OK);
            alert.showAndWait();
            throw new RuntimeException("Не удалось обновить список файлов по пути:" + path);

        }
    }

    /**
     * Event handling method
     * @param actionEvent event
     */
    public void btnExitAction(ActionEvent actionEvent) {
        System.out.println("Закрытие программы");
        Platform.exit();
    }
}
