package com.example.dnd_personage.attributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    public static int roll(){
        return ThreadLocalRandom.current().nextInt(1, 7);
    }
    public static int rollStat(){
        ArrayList<Integer> rollList = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++) {
            rollList.add(roll());
        }
        Collections.sort(rollList,Collections.reverseOrder());
        return rollList.get(0) + rollList.get(1) + rollList.get(2);
    }
}
