package trashworld;
import java.util.Scanner;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
        
    //no-arg constructor that creates a Game object. The Game object runs the createRooms method, and creates a new Parser object.
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
    }

    //method with return type void that creates new Room objects.
    //it also defines what exits the different rooms have and sets the starting room by using currentRoom
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office, projectRoom;
      
        outside = new Room("outside the main entrance of the university. "
                + "\n To the east is the Theatre. "
                + "\n To the west is the Pub. "
                + "\n To the south is the Lab");
        theatre = new Room("in a lecture theatre. "
                + "\n To the west is the outside entrance."
                + "\n To the south is the Computing Admin Office.");
        pub = new Room("in the campus pub. "
                + "\n To the south is the Project-Room. "
                + "\n To the east is the outside entrance.");
        lab = new Room("in a computing lab. "
                + "\n To the west is the Project-Room. "
                + "\n To the north is the outside entrance."
                + "\n To the east is the Computing Admin Office.");
        office = new Room("in the computing admin office"
                + "\n To the north is the Theatre."
                + "\n To the west is the Lab.");
        projectRoom = new Room("in the Project-Room"
                + "\n To the north is the Pub."
                + "\n To the east is the Lab.");
        
        
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);
        theatre.setExit("south", office);

        pub.setExit("east", outside);
        pub.setExit("south", projectRoom);
        
        projectRoom.setExit("north", pub);
        projectRoom.setExit("east", lab);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.setExit("west", projectRoom);

        office.setExit("west", lab);
        office.setExit("north", theatre);

        currentRoom = outside;
    }

    //method that runs the printWelcome method, creates a boolean-type variable called finished which is set to false.
    //while loop runs as long as finished IS NOT true. Runs getCommand on the parser object and converts it to a Command object called command.
    //as soon as finished = true, it jumps out of the loops and prints the string.
    public void play() 
    {            
        setPlayerName();
        printWelcome();

                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing " + player.getName() + ".  Good bye.");
    }
    //method with a scanner that takes a user input from the Scanner and stores it in the player object as the players name
    public void setPlayerName(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        player.setName(input.nextLine());
    }
    
    //prints a welcome message on screen
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to TrashWorld " + player.getName() + "!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    //private method, that returns a boolean value. Takes a Command as argument.
    //initiates variable of type boolean and sets it to false
    //set CommandWord object called commandWord equal to what getCommandWord returns when run on command
    //if commandWord is an unknown command, prints a string and returns false (false = don't do anything)
    //if commandWord is the HELP command, run the printHelp method
    //if commandWord is GO, runs the goRoom method
    //if commandWord is QUIT sets wantToQuit equal to what the quit method returns
    //returns wantToQuit in the end which means that if = true, game ends - if = false, game continues
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    //method that prints the help string
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
        System.out.println(currentRoom.getLongDescription());
    }

    //method goRoom that takes an arguement of type Command
    //if the Command object does not have a second, print "Go where?"
    //direction variable of type string is set to the second word of the command
    //nextRoom variable of type Room is set to the value that is returned when getExit method with direction as argument is run on currentRoom
    //if nextRoom returns null, print "There is no door!"
    //else, set currentRoom to nextRoom and print the long description of the new currenRoom
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    //method that returns a boolean value, takes a Command type as argument
    //if the QUIT command has a second word when run, print "Quit what?"
    //else, returns true
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
    
    //main method
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
