package com.mycompany.tworld;


//enum is a special CLASS that represents a group of constans. Constants can also be created by using the 'final' keyword.
public enum CommandWord
{
    //group of constants. Defines the name of the command and how to execute the command on the commandline. The string inside
    //the parantheses are how to call the command.
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), TAKE("take"), INVENTORY("inventory"), START("start"), POINTS("points"), ITEMS("items");
    
    //new private attribute of type String called commandString
    private String commandString;
    
    //constructor for constructing a commandWord, takes a String as argument.
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    //converts element to a String type
    public String toString()
    {
        return commandString;
    }
}
