/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tworld;

/**
 *
 * @author svane
 */
public class Item {
    private String name;
    private String description;
    
    public Item (String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void addToInventory(){
        com.mycompany.tworld.Player.getInventory().add(this);
    }
    
    
    
   
    
    
}
