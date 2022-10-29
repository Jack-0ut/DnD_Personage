package com.example.dnd_personage.memento;


import com.example.dnd_personage.attributes.Stats;

public class Originator {
    private Stats state;
    private String stateName;

    public Stats getState() {
        return state;
    }

    public void setState(Stats state) {
        this.state = state;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateName() {
        return stateName;
    }

    public Memento saveStateToMemento(){
        return  new Memento(stateName,state);
    }

    public void getStateFromMemento(Memento memento){
        state = memento.getState();
    }
}