package trashworld;

import java.util.Scanner;

public class Game {

    private Parser parser;
    private Room currentRoom;
    private Player player;

    //no-arg constructor that creates a Game object. The Game object runs the createRooms method, and creates a new Parser object.
    public Game() {
        player = new Player();
        createRooms();
        parser = new Parser();
    }

    //instantiates the rooms
    public Room garden, kitchen, livingRoom, bedroom, office, supermarket;

    //method with return type void that creates new Room objects.
    //it also defines what exits the different rooms have and sets the starting room by using currentRoom
    private void createRooms() {

        //everything that has to do with garden
        //defines the garden rooms description
        garden = new Room("in the garden outside your house."
                + "\n To the east is the path to the supermarket. "
                + "\n To the west is the office. "
                + "\n To the north is the kitchen. ");

        //everything that has to do with kitchen
        kitchen = new Room("in your kitchen. "
                + "\n To the west is the living room."
                + "\n To the south is the garden.");

        livingRoom = new Room("in your living room. "
                + "\n To the south is the office. "
                + "\n To the east is the kitchen."
                + "\n To the north is the bedroom.");

        bedroom = new Room("in your bedroom. "
                + "\n To the south is the living room.");

        office = new Room("in the office"
                + "\n To the north is the living room."
                + "\n To the east is the garden.");

        supermarket = new Room("in the supermarket"
                + "\n To the west is the path home to your garden.");

        garden.setExit("east", supermarket);
        garden.setExit("north", kitchen);
        garden.setExit("west", office);

        kitchen.setExit("west", livingRoom);
        kitchen.setExit("south", garden);

        livingRoom.setExit("north", bedroom);
        livingRoom.setExit("south", office);
        livingRoom.setExit("east", kitchen);

        bedroom.setExit("south", livingRoom);

        office.setExit("east", garden);
        office.setExit("north", livingRoom);

        supermarket.setExit("west", garden);

        //garden items instantiated
        Item tomato = new Item("Tomato", "This is a tomato");
        Item banana = new Item("Banana", "This is a banana");
        Item orange = new Item("Orange", "This is an orange");
        //add items to garden
        garden.addItem("Tomato", tomato);
        garden.addItem("Banana", banana);
        garden.addItem("Orange", orange);

        Question[] gardenQuestions = {
            new Question(
            "What color is grass? \n(a) green \n(b) red \n(c) blue",
            "a",
            "The grass is indeed green",
            "No, the grass is green"),
            new Question(
            "What color is grass? \n(a) green \n(b) red \n(c) blue",
            "a",
            "The grass is indeed green",
            "No, the grass is green"),
            new Question(
            "What color is grass? \n(a) green \n(b) red \n(c) blue",
            "a",
            "The grass is indeed green",
            "No, the grass is green"),
            new Question(
            "What color is grass? \n(a) green \n(b) red \n(c) blue",
            "a",
            "The grass is indeed green",
            "No, the grass is green"),};
        Quiz gardenQuiz = new Quiz(gardenQuestions);
        garden.setRoomQuiz(gardenQuiz);

        currentRoom = garden;
    }

    //method that runs the printWelcome method, creates a boolean-type variable called finished which is set to false.
    //while loop runs as long as finished IS NOT true. Runs getCommand on the parser object and converts it to a Command object called command.
    //as soon as finished = true, it jumps out of the loops and prints the string.
    public void play() {
        setPlayerName();
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing " + player.getName() + ".  Good bye.");
    }

    //method with a scanner that takes a user input from the Scanner and stores it in the player object as the players name
    public void setPlayerName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        player.setName(input.nextLine());
    }

    //method that enables the player to pick up items. The method finds the item in the items HashMap attribute from the Room-class
    //and adds that item to the player inventory Array.
    public void pickUp(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        String item = command.getSecondWord();
        try {
            currentRoom.getItems().get(item).addToInventory();
            System.out.println("You picked up: " + item);
            currentRoom.getItems().remove(item);
        } catch (NullPointerException npe) {
            System.out.println("That item doesn't exist. Try again with another item-name and/or remember that the item names are case-sensitive");
        }
    }

    //method that initiates the quiz in current room. If the quiz has already been completed, the player is notified and won't be allowed to try again
    public void startQuiz(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Start what? If you're trying to start a quiz, remember to type 'start quiz'.");
            return;
        }

        if (currentRoom.getRoomQuiz().isDone == false) {
            if (currentRoom == garden) {
                System.out.println("You start the quiz for garden.");
            } else if (currentRoom == office) {
                System.out.println("You start the quiz for office");
            } else if (currentRoom == kitchen) {
                System.out.println("You start the quiz for kitchen");
            } else if (currentRoom == supermarket) {
                System.out.println("You start the quiz for supermarket");
            } else if (currentRoom == bedroom) {
                System.out.println("You start the quiz for bedroom");
            } else if (currentRoom == livingRoom) {
                System.out.println("You start the quiz for living room");
            }
            System.out.println("You enter your response as just a single letter");

            for (int i = 0; i < currentRoom.getRoomQuiz().questions.length; i++) {
                System.out.println(currentRoom.getRoomQuiz().questions[i].getPrompt());
                Scanner input = new Scanner(System.in);
                Question.playerInput = input.nextLine();
                if (Question.playerInput.equals(currentRoom.getRoomQuiz().questions[i].getAnswer())) {
                    System.out.println("Correct! " + currentRoom.getRoomQuiz().questions[i].getCorrectResponse());
                    Player.points++;
                    System.out.println("You received 1 point!");
                } else {
                    System.out.println("Wrong! " + currentRoom.getRoomQuiz().questions[i].getIncorrectResponse());
                    System.out.println("You didn't receive any points for that answer.");
                }
            }
            Quiz.isDone = true;
            System.out.println("You have completed the quiz!");
        } else{
            System.out.println("You have already completed this quiz. Please continue to another room");
        }
    }

    //prints a welcome message on screen
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to TrashWorld " + player.getName() + "!");
        System.out.println("TrashWorld is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.printItems(currentRoom.getItems()));
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
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.TAKE) {
            pickUp(command);
        } else if (commandWord == CommandWord.INVENTORY) {
            printInventory();
        } else if (commandWord == CommandWord.START) {
            startQuiz(command);
        } else if (commandWord == CommandWord.POINTS){
            System.out.println("You currently have " + Player.points + " points.");
        }
        return wantToQuit;
    }

    //prints all items from the inventory array
    public void printInventory() {
        String inventorystring = new String();
        System.out.println("Your inventory has the following items: ");
        for (int i = 0; i < Player.inventory.size(); i++) {
            inventorystring = inventorystring.concat("'" + Player.inventory.get(i).getName() + "'");
        }
        System.out.println(inventorystring);
    }

    //method that prints the help string
    private void printHelp() {
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
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.printItems(currentRoom.getItems()));
            System.out.println(currentRoom.getLongDescription());
        }
    }

    //method that returns a boolean value, takes a Command type as argument
    //if the QUIT command has a second word when run, print "Quit what?"
    //else, returns true
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    //main method
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
