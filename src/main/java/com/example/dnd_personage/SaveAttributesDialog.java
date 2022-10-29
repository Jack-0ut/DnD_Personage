package com.example.dnd_personage;

import com.example.dnd_personage.memento.CareTaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;

public class SaveAttributesDialog extends Dialog<String> {
    @FXML
    protected TextField inputAttributeNameField;

    public SaveAttributesDialog() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("dialog_save.fxml"));
        loader.setController(this);
        DialogPane dialogPane;

        try {
            dialogPane = loader.load();

            setDialogPane(dialogPane);
            setTitle("Save Name");
            setResultConverter();

            Button okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
            okButton.addEventFilter(ActionEvent.ACTION, actionEvent -> {
                if (!isInputValid()) {
                    actionEvent.consume();
                }
            });

        } catch (IOException e) {
            throw new RuntimeException("Can't import dialog_save.fxml");
        }
    }

    private void setResultConverter() {
        Callback<ButtonType, String> stringResultConverter = param -> {
            if (param == ButtonType.OK) {
                return inputAttributeNameField.getText();
            }
            return null;
        };
        setResultConverter(stringResultConverter);
    }

    private boolean isInputValid() {
        return !inputAttributeNameField.getText().isBlank() &&
                !CareTaker.isMementoNameInMap(inputAttributeNameField.getText());
    }
}
