/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

package trashworld;

public class Command
{
    private CommandWord commandWord;
    private String secondWord;
    
    //Command constructor that makes a Command object - consists of a Commandword of type CommandWord and secondWord of type String.
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    //getter method for commandWord and of return-type CommandWord
    public CommandWord getCommandWord()
    {
        return commandWord;
    }
    
    //getter method for secondWord and of return-type String
    public String getSecondWord()
    {
        return secondWord;
    }
    
    //method for invalid commands
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }
    
    //checks if the command has a secondWord
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}