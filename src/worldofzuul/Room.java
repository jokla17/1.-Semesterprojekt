package worldofzuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room 
{
    private String description;
    
    //creates something similar to an array, but instead of accessing the room object by an index, the room-object is
    //accessible by using a string
    private HashMap<String, Room> exits;
    
    //constructor that takes a String as an argument. Creates a Room-object that has a description, and an exit hashmap that contains a String
    //which refers to room-objects they player can enter via the hashmap. 
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    //method that maps exits with a String(direction) to a neighboring room-object. This means that by calling this method
    //we can defines which exits the room has, by giving a direction to the neigboring room, and a room object
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    //getter method for the description attribute
    public String getShortDescription()
    {
        return description;
    }
    
    //getter method for a longer description than above.
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }
    
    //getter method for telling the player what exits that are currently available
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    //getter method for getting the exit directions. Used in "Game" in the goRoom method.
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

