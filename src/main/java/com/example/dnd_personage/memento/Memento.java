package com.example.dnd_personage.memento;


import com.example.dnd_personage.attributes.Stats;

public class Memento {
    private Stats state;
    private String stateName;
    public Memento(String stateName,Stats state) {
        this.stateName = stateName;
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public Stats getState() {
        return state;
    }



}
