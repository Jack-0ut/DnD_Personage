package com.example.dnd_personage.CharactersClasses;


import com.example.dnd_personage.DataElement;
import com.example.dnd_personage.visitor.DataElementsVisitor;

import java.util.TreeMap;

/** Abstract class that defines Classes for our Characters
 * For every race we have health ('hp' attribute)
 **/
public abstract class CharacterClass implements DataElement {
    String className;
    int hp;

    @Override
    public String toString() {
        return "Class " + className + "\n" + "Base Health: " + hp;
    }

    // For visitor pattern
    @Override
    public TreeMap accept(DataElementsVisitor elementsVisitor) {
        return elementsVisitor.visit(this);
    }

    public int getHP(){
        return  hp;
    }

    public String getClassName() {
        return className;
    }
}
