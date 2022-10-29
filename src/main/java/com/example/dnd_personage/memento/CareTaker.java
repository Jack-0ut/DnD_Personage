package com.example.dnd_personage.memento;

import java.util.HashMap;
public class CareTaker {
    private static HashMap<String,Memento> mementoHashMap = new HashMap<>();
    //private List<Memento> mementoList = new ArrayList<Memento>();
    private static int mementoID = 0;

    public  void add(Memento memento){

        //mementoList.add(mementoID,memento);
        //mementoID++;
        mementoHashMap.put(memento.getStateName(),memento);

    }
    public static boolean isMementoNameInMap(String name){
        return mementoHashMap.containsKey(name);
    }
    public Memento get(String mementoName){
        return  mementoHashMap.get(mementoName);
    }

    public static HashMap<String, Memento> getMementoHashMap() {
        return mementoHashMap;
    }
}