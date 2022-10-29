package com.example.dnd_personage.json;

import com.example.dnd_personage.Character;
import com.example.dnd_personage.DataElement;
import com.example.dnd_personage.visitor.ElementVisitor;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONWriter {
    public  JSONObject formJsonObject(Character character, ElementVisitor elementVisitor){
        List<DataElement> dataElementList = new ArrayList<>();

        // add all objects that contain info about our character to list
        dataElementList.add(character);
        dataElementList.add(character.getCharacterClass());
        dataElementList.add(character.getRace());
        dataElementList.add(character.getAttributes());

        JSONObject jsonObject = new JSONObject();

        // get TreeMap from every object on the list
        for (DataElement element: dataElementList ) {
            jsonObject.putAll(element.accept(elementVisitor));
        }
        return jsonObject;
    }
}
