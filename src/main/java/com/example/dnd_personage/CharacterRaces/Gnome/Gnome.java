package com.example.dnd_personage.CharacterRaces.Gnome;


import com.example.dnd_personage.CharacterRaces.CharacterRace;
import com.example.dnd_personage.attributes.Stats;

public class Gnome extends CharacterRace {
    public Gnome(Stats bonuses){
        super(bonuses);
        this.raceName = "Gnome";
        this.bonuses = bonuses;
    }
}
