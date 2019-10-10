package worldofzuul;
import java.util.HashMap;


public class CommandWords
{
    //private attribute of type HashMap. HashMap consists of a String and a CommandWord object. Attribute is called validCommands
    private HashMap<String, CommandWord> validCommands;
    
    //No-arg constructor that creates a CommandWords object. 
    //Creates a new HashMap from validCommands.
    //Loops through the values of the CommandWord and checks that the command IS NOT an unknown command
    //If the statement above evaluates to true, maps the validCommands HashMap as a command (converted to a string) and the command
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    //method that returns the command is the command does not reference a null-value. If it does reference a null-value, returns the UNKNOWN command.
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    //method that checks if the command String is a command that exists, by running the containsKey method on the validCommands HashMap.
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    //method that prints all available commands
    public void showAll() 
    {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
