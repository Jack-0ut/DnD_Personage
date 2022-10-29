package com.example.dnd_personage;

import com.example.dnd_personage.CharacterRaces.CharacterRace;
import com.example.dnd_personage.CharactersClasses.CharacterClass;
import com.example.dnd_personage.attributes.Stats;
import com.example.dnd_personage.visitor.DataElementsVisitor;

import java.util.Set;
import java.util.TreeMap;

/**
 * Class that contains all information about character :
 * Name, Attributes, Race,Class
 **/
public class Character implements DataElement {

    private String name;
    private int health;
    private Stats attributes;
    private CharacterRace race;
    private CharacterClass characterClass;

    public Character(String name, CharacterClass characterClass, CharacterRace race) {
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.health = characterClass.getHP();
    }


    // Adding bonuses to stats based on the character race
    private void addRaceBonuses() {
        Set<String> attributesKeySet = attributes.getStats().keySet();
        for (String key : attributesKeySet) {
            int newStat = attributes.getStats().get(key) + race.getBonuses().getStats().get(key);
            attributes.getStats().put(key, newStat);
        }
    }
    // For visitor pattern
    @Override
    public TreeMap accept(DataElementsVisitor elementsVisitor) {
        return elementsVisitor.visit(this);
    }

    public Stats getAttributes() {
        return attributes;
    }

    public CharacterRace getRace() {
        return race;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }


    public void setAttributes(Stats attributes) {
        this.attributes = attributes;
        this.health += Math.floor(this.attributes.getStats().get("Constitution") /2) -5;
        addRaceBonuses();
    }

}
