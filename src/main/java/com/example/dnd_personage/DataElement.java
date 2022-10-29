package com.example.dnd_personage;

import com.example.dnd_personage.visitor.DataElementsVisitor;

import java.util.TreeMap;

public interface DataElement {
    public TreeMap accept(DataElementsVisitor elementsVisitor);
}