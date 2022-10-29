package com.example.dnd_personage.CharactersClasses;

import java.util.ArrayList;
import java.util.List;

public class Druid extends CharacterClass {
    private List<String> magics;

    public Druid() {
        this.magics = new ArrayList<>();
        this.className = "Druid";
        this.hp = 8;
        magics.add("Healing Wound");
        magics.add("Animal Shape");
    }


}
