package trashworld;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Parser 
{
    private CommandWords commands;
    private Scanner reader;

    //no-arg constructor that creates a Parser object
    //objects has a CommandWords object called commands and a Scanner object called reader. System.in means that the input is read by the program.
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    //method that takes an input from the player in the commandline, by using the Scanner object, and converts it to a new Command object.
    //The tokenizer splits the users input (which is a string) into tokens (It takes whatever the user types in the commandline and
    //splits them up into two different instances called word1 and word2). Then these are used to return a new Command object.
    public Command getCommand() 
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> "); 

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next(); 
            }
        }

        return new Command(commands.getCommandWord(word1), word2);
    }

    //method that runs the showAll method on commands
    public void showCommands()
    {
        commands.showAll();
    }
    
    
}
