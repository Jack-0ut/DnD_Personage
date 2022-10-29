package com.example.dnd_personage.visitor;

import com.example.dnd_personage.Character;
import com.example.dnd_personage.CharacterRaces.CharacterRace;
import com.example.dnd_personage.CharactersClasses.CharacterClass;
import com.example.dnd_personage.attributes.Stats;

import java.util.TreeMap;

/**
 * Implements DataElementsVisitor interface
 * and define visit method for all objects to visit
 **/
public class ElementVisitor implements DataElementsVisitor {
    @Override
    public TreeMap visit(Character character) {
        TreeMap characterMap = new TreeMap();
        characterMap.put("Name",character.getName());
        characterMap.put("Health",character.getHealth());
        return characterMap;
    }

    @Override
    public TreeMap visit(CharacterRace characterRace) {
        TreeMap characterRaceMap = new TreeMap();
        characterRaceMap.put("Race",characterRace.getRaceName());
        return characterRaceMap;
    }

    @Override
    public TreeMap visit(CharacterClass characterClass) {
        TreeMap characterClassMap = new TreeMap();
        characterClassMap.put("Class",characterClass.getClassName());
        return characterClassMap;
    }

    @Override
    public TreeMap visit(Stats stats) {
        TreeMap attributesMap = new TreeMap();
        attributesMap.put("Attributes",stats.getStats());
        return attributesMap;
    }
}