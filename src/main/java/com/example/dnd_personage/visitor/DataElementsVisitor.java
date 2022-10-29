package com.example.dnd_personage.visitor;

import com.example.dnd_personage.Character;
import com.example.dnd_personage.CharacterRaces.CharacterRace;
import com.example.dnd_personage.CharactersClasses.CharacterClass;
import com.example.dnd_personage.attributes.Stats;

import java.util.TreeMap;

/**
 * interface that define all objects we could visit
 */
public interface DataElementsVisitor {
    public TreeMap visit(Character character);

    public TreeMap visit(CharacterRace characterRace);

    public TreeMap visit(CharacterClass characterClass);

    public TreeMap visit(Stats stats);
}