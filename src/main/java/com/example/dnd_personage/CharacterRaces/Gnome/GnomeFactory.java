package com.example.dnd_personage.CharacterRaces.Gnome;


import com.example.dnd_personage.RaceAbstractFactory;
import com.example.dnd_personage.attributes.Stats;

public class GnomeFactory implements RaceAbstractFactory {
    @Override
    public Gnome create() {
        return  new Gnome(new Stats(0,0,1,2,0,0));
    }
}
