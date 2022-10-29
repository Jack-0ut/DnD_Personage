package com.example.dnd_personage;

import com.example.dnd_personage.CharacterRaces.Elf.ElfFactory;
import com.example.dnd_personage.CharacterRaces.Gnome.GnomeFactory;
import com.example.dnd_personage.CharactersClasses.ClassFactory;
import com.example.dnd_personage.attributes.Stats;
import com.example.dnd_personage.memento.CareTaker;
import com.example.dnd_personage.memento.Memento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * Main Controller in application that responsible for all activities on
 * the screen and all the functionality during character creation
 **/

public class Controller implements Initializable {
    @FXML
    private TextField nameInputField;
    @FXML
    private ChoiceBox<String> classChoiceBox,raceChoiceBox;
    @FXML
    private Label strengthValue,dexterityValue,constitutionValue,intellectValue,wisdomValue,charismaValue;
    private Character character;
    private CareTaker careTaker = new CareTaker();
    private HashMap<String,Integer> attributes;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Set items for classChoiceBox and raceChoiceBox
        String [] races = {"Elf","Gnome"};
        String [] classes = {"Barbarian","Druid"};
        classChoiceBox.getItems().addAll(classes);
        raceChoiceBox.getItems().addAll(races);

    }

    // Save character info: name,class,race when this button is clicked
    public void saveCharacterInfo(ActionEvent actionEvent) {

        switch (raceChoiceBox.getValue()) {
            case "Gnome" -> character = new Character(nameInputField.getText(),
                    ClassFactory.getClass(classChoiceBox.getValue()),
                    new GnomeFactory().create()
            );
            case "Elf" -> character = new Character(nameInputField.getText(),
                    ClassFactory.getClass(classChoiceBox.getValue()),
                    new ElfFactory().create()
            );
        }

    }

    // generate character attributes when 'Generate' button is clicked
    public void generateAttributes(ActionEvent actionEvent) {
        // if character has not been initialized  before calling generate
        // then throw error dialog window
        if(character == null){

            Alert.AlertType alertType = Alert.AlertType.ERROR;
            Alert alert = new Alert(alertType,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("Initialize character!");
            alert.getDialogPane().setContentText("You should create character"+
                    " before generation of character attributes!");
            alert.showAndWait();

        }

        // generate stats for current character
        character.setAttributes(Stats.generate());
        // getting the attributes hashMap
        HashMap<String,Integer> attributes = character.getAttributes().getStats();

        // Set labels value as the attribute value from hashMap
        setAttributeLabelsValue(attributes);

    }

    // load saved character attributes from memento when 'Load' button is clicked
    public void loadSavedAttributes(ActionEvent mouseEvent) {
        // Creating dialog that will return name of stats that we will
        // retrieve from CareTaker
        Dialog<String> getStats  = new GetStatsDialog();
        Optional<String> result = getStats.showAndWait();

        if(result.isPresent()){
            String attributeStateName = result.get();
            // Set labels value to that which has stateName equal to what we get from dialog
            attributes = CareTaker.getMementoHashMap().get(attributeStateName).getState().getStats();
            character.getAttributes().setStats(attributes);
            setAttributeLabelsValue(attributes);
        }

    }

    // save character with attributes when 'Add Character' button is clicked
    // and switch to 'character_information.fxml'
    public void addCharacter(ActionEvent actionEvent) {
        try {
            // Switch to 'character_information.fxml'
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("character_information.fxml"));
            Parent root =  loader.load();

            // Set current character information to 'character_information.fxml'
            CharacterController characterController = loader.getController();
            characterController.fillCharacterInfo(character);
            characterController.fillAttributes(character.getAttributes().getStats());

            Scene newScene = new Scene(root);
            stage.setResizable(false);
            stage.setTitle("Character Info");
            stage.setScene(newScene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException("Can't open 'character_information.fxml'");
        }

    }

    // Dialog that return the String that will represent the name of the stats
    // that we will add to CareTaker
    public void saveAttributes(ActionEvent actionEvent) {
        Dialog<String> saveAttributesDialog = new SaveAttributesDialog();
        Optional<String> result = saveAttributesDialog.showAndWait();
        String statsName;
        if(result.isPresent()){
            statsName = result.get();
            careTaker.add(new Memento(statsName,character.getAttributes()));
        }
    }

    // Set Labels attribute labels value
    private void setAttributeLabelsValue(HashMap<String,Integer> attributes){
        strengthValue.setText(attributes.get("Strength").toString());
        dexterityValue.setText(attributes.get("Dexterity").toString());
        constitutionValue.setText(attributes.get("Constitution").toString());
        intellectValue.setText(attributes.get("Intellect").toString());
        wisdomValue.setText(attributes.get("Wisdom").toString());
        charismaValue.setText(attributes.get("Charisma").toString());
    }
}