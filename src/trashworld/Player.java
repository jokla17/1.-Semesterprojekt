/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trashworld;

import java.util.ArrayList;

/**
 *
 * @author svane
 */
public class Player {
    private String name;
    public static ArrayList<Item> inventory = new ArrayList<>();
    public static int points = 0;
    
    public static ArrayList<Item> getInventory() {
        return inventory;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }
}
