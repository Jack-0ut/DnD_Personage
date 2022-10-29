package com.example.dnd_personage.CharactersClasses;

public class ClassFactory {
    public static CharacterClass getClass(String type){
        if(type == "Barbarian") return  new Barbarian();
        if(type == "Druid") return  new Druid();
        return null;
    }
}
