package com.example.dnd_personage.CharacterRaces.Elf;


import com.example.dnd_personage.CharacterRaces.CharacterRace;
import com.example.dnd_personage.attributes.Stats;

public class Elf extends CharacterRace {
    public Elf(Stats bonuses){
        super(bonuses);
        this.raceName = "Elf";
        this.bonuses = bonuses;
    }

}
