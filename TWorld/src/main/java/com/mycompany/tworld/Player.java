/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tworld;

import java.util.ArrayList;

/**
 *
 * @author svane
 */
public class Player {
    private static String name;
    public static ArrayList<Item> inventory = new ArrayList<>();
    public static int points = 0;
    
    public static ArrayList<Item> getInventory() {
        return inventory;
    }
    
    public String getName() {
        return name;
    }

    public static void setName(String name) {
        Player.name = name;
    }

    public int getPoints() {
        return points;
    }
    
    public static ArrayList<String> inventoryToString(){
        ArrayList<String> stringInventory = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            stringInventory.add(Player.inventory.get(i).getName());
        }
        return stringInventory;
    }
}
