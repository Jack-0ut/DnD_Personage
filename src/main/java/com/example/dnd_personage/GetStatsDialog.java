package com.example.dnd_personage;

import com.example.dnd_personage.memento.CareTaker;
import com.example.dnd_personage.memento.Memento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class GetStatsDialog extends Dialog<String> {
    @FXML
    private ListView<String> listView;
    private String selectedSave = null;

    public GetStatsDialog(){
        // load GUI from 'dialog_get_selected_save.fxml'
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("dialog_get_selected_save.fxml"));
        loader.setController(this);
        DialogPane dialogPane;

        try {
            dialogPane = loader.load();

            setDialogPane(dialogPane);
            setTitle("Get Saved Attributes");

            //Add values to ListView
            HashMap<String, Memento> mementoHashMap = CareTaker.getMementoHashMap();
            Set<String> mementoNamesSet = mementoHashMap.keySet();
            listView.getItems().addAll(mementoNamesSet);


            // Convert result of dialog into String
            setResultConverter();

            // When user click button OK, check if he does the needed actions,
            // if not it's not allow user to close window when pressed OK button
            Button okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
            okButton.addEventFilter(ActionEvent.ACTION, actionEvent -> {
                if (selectedSave == null){
                    actionEvent.consume();
                }
            });

            // Select item from the list and put it in selectedSave variable
            listView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) ->
                    selectedSave = listView.getSelectionModel().getSelectedItem());

        } catch (IOException e) {
            throw new RuntimeException("Can't import dialog_get_selected_save.fxml");
        }
    }

    private void setResultConverter() {
        Callback<ButtonType, String> stringResultConverter = new Callback<ButtonType, String>() {
            @Override
            public String call(ButtonType param) {
                if (param == ButtonType.OK) {
                    return selectedSave;
                }
                return null;
            }
        };
        setResultConverter(stringResultConverter);
    }

}
