package com.example.dnd_personage.CharacterRaces.Elf;


import com.example.dnd_personage.RaceAbstractFactory;
import com.example.dnd_personage.attributes.Stats;

public class ElfFactory implements RaceAbstractFactory {

    @Override
    public Elf create() {
        return new Elf(new Stats(0,2,0,0,0,1));
    }
}
