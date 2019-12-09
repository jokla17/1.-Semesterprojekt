package com.mycompany.tworld;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

public class Room {

    private String description;
    

    //creates something similar to an array, but instead of accessing the room object by an index, the room-object is
    //accessible by using a string
    private HashMap<String, Room> exits;

    private HashMap<String, Item> items;

    private Quiz roomQuiz;

    //constructor that takes a String as an argument. Creates a Room-object that has a description, and an exit hashmap that contains a String
    //which refers to room-objects they player can enter via the hashmap. 
    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashMap<String, Item>();
    }

    //method that maps exits with a String(direction) to a neighboring room-object. This means that by calling this method
    //we can defines which exits the room has, by giving a direction to the neigboring room, and a room object
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    //getter method for the description attribute
    public String getShortDescription() {
        return description;
    }

    //getter method for a longer description than above.
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    //getter method for telling the player what exits that are currently available
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    //getter method for getting the exit directions. Used in "Game" in the goRoom method.
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public Quiz getRoomQuiz() {
        return roomQuiz;
    }

    public void setRoomQuiz(Quiz roomQuiz) {
        this.roomQuiz = roomQuiz;
    }

    //adds item-object to items-array in a given room
    public void addItem(String description, Item item) {
        items.put(description, item);
    }

    //removes item-object from items-array in a given room
    public void removeItem(String item) {
        items.remove(item);
    }


    //runs the getName() method from item-class to return the name-attribute of the item
    public String getName(Item item) {
        return item.getName();
    }

    //fetches the item-array in the given room
    public HashMap<String, Item> getItems() {
        return items;
    }

    //sets an item in a given room array to another item
    public void setItems(HashMap<String, Item> items) {
        this.items = items;
    }

    public boolean isUnlockable() {
        boolean result = false;
        for (int i = 0; i < Player.inventory.size(); i++) {
            Item d = Player.inventory.get(i);
            if (d instanceof Key) {
                if (((Key) d).unlocks == this) {
                    result = true;
                }
            }
        }
        return result;
    }

    //prints out the items in a given room by getting the items name-attributes and adding these to a string (by using concat) which is returned
    public String printItems(HashMap items) {
        String itemstring = new String();
        if (this.items.isEmpty()) {
            System.out.println("You picked up all the items in this room.");
        } else {
            System.out.println("In this room you see the following items: ");
            Iterator iter = items.entrySet().iterator();
            while (iter.hasNext()) {
                HashMap.Entry entry = (HashMap.Entry) iter.next();
                itemstring = itemstring.concat(entry.getKey().toString() + " ");
            }
        }
        return itemstring;
    }
    
    public ArrayList<String> getRoomItemsList(){
        ArrayList<String> roomItems = new ArrayList<>();
        Iterator iter = items.entrySet().iterator();
        while(iter.hasNext()){
            HashMap.Entry entry = (HashMap.Entry) iter.next();
            roomItems.add(entry.getKey().toString());
        }
        return roomItems;
    }
}
