package com.example.dnd_personage;

import com.example.dnd_personage.CharacterRaces.Gnome.GnomeFactory;
import com.example.dnd_personage.CharactersClasses.ClassFactory;
import com.example.dnd_personage.attributes.Stats;
import com.example.dnd_personage.json.JSONWriter;
import com.example.dnd_personage.visitor.ElementVisitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Responsible for Character information scene and writing to JSON
 **/
public class CharacterController {
    @FXML
    private Label nameValue,classValue,raceValue,healthValue;
    @FXML
    private Label strengthValue,dexterityValue,constitutionValue,intellectValue,wisdomValue,charismaValue;
    private Character character;

    public void fillCharacterInfo(Character character){
        this.character = character;

        nameValue.setText(character.getName());
        classValue.setText(character.getCharacterClass().getClassName());
        raceValue.setText(character.getRace().getRaceName());
        healthValue.setText(String.valueOf(character.getHealth()));
    }

    public void fillAttributes(HashMap<String,Integer> attributes){
        strengthValue.setText(attributes.get("Strength").toString());
        dexterityValue.setText(attributes.get("Dexterity").toString());
        constitutionValue.setText(attributes.get("Constitution").toString());
        intellectValue.setText(attributes.get("Intellect").toString());
        wisdomValue.setText(attributes.get("Wisdom").toString());
        charismaValue.setText(attributes.get("Charisma").toString());
    }
    // writing character data to JSON file
    public void writeToJSON(ActionEvent actionEvent) {
        try {
            ElementVisitor elementVisitor = new ElementVisitor();
            JSONWriter jsonWriter = new JSONWriter();

            FileWriter fileWriter = new FileWriter("result.json");
            fileWriter.write("[");
            fileWriter.write(jsonWriter.formJsonObject(this.character,elementVisitor).toJSONString());
            fileWriter.write("]");

            fileWriter.close();
            Alert.AlertType alertType = Alert.AlertType.INFORMATION;
            Alert alert = new Alert(alertType,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("Character data has been successfully written to file 'result.json'");
            alert.showAndWait();

        } catch (IOException e) {
            Alert.AlertType alertType = Alert.AlertType.ERROR;
            Alert alert = new Alert(alertType,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("Can't write result to JSON!");
            alert.getDialogPane().setContentText("You should create character"+
                    " before generation of character attributes!");
            alert.showAndWait();

            throw new RuntimeException("It's not possible to create file 'result.json'");
        }
    }

    public void goBack(ActionEvent actionEvent) {
        // Switch to 'character_information.fxml'
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = null;
        try {
            root = loader.load();

            Scene newScene = new Scene(root);
            stage.setResizable(false);
            stage.setTitle("Character Info");
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Can't open 'main.fxml'");
        }

    }
}
