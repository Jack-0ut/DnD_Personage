package com.example.dnd_personage.CharacterRaces;


import com.example.dnd_personage.DataElement;
import com.example.dnd_personage.attributes.Stats;
import com.example.dnd_personage.visitor.DataElementsVisitor;

import java.util.TreeMap;

/**
 * Abstract class that defines the Races our Characters could have
 * For every race we have name and bonuses
 **/
public abstract class CharacterRace implements DataElement {
    protected String raceName;
    protected Stats bonuses;

    public CharacterRace(Stats bonuses) {
        this.bonuses = bonuses;
    }


    public void print() {
        System.out.println("Race: " + raceName);
        System.out.println("\t\tBonuses");
        bonuses.print();

    }


    // For visitor pattern
    @Override
    public TreeMap accept(DataElementsVisitor elementsVisitor) {
        return elementsVisitor.visit(this);
    }


    public Stats getBonuses() {
        return bonuses;
    }

    public String getRaceName() {
        return raceName;
    }
}
