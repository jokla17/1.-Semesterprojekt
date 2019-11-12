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
        Item weed = new Item("Weed", "You picked up the weeds and "
                + "placed it in the compost heap. This action prevented waste to the environment.");
        Item grass = new Item("Grass", "When cutting grass, remember to leave the grass trimmings on the lawn. "
                + "\n It will break down eventually and prevent waste in the form of plastic bags if you had chosen to throw out the trimmings");
        Item apple = new Item("Apple", "Your appletree is dropping apples to the ground. "
                + "\n Remember that, instead of throwing them out, apples can either be: "
                + "\n 1. Put in your compost heap. When they rot, they will provide great nutrients to the soil."
                + "\n 2. Given out for free to people around you."
                + "\n This will make sure that you don't generate any unnecessary waste for the environment");
        
        //add items to garden
        garden.addItem("Weed", weed);
        garden.addItem("Grass", grass);
        garden.addItem("Apple", apple);
        
        //kitchen items instantiated
        Item meat = new Item("Meat", "You bought too much meat. "
                + "\n You choose to freeze down the remaining meat to prevent mictroorganisms from developing and to keep your meat fresh and edible");
        Item stew = new Item("Stew", "You have some stew leftovers from last nights meal. "
                + "\n You choose to eat it for dinner tonight instead of trowing it out and thereby you prevent more waste.");
        Item cake = new Item("Cake", "The expiration date of the cake has expired with two days but does show any sign of rotting."
                + "\n To prevent the waste of food you choose to eat it some of it and give away the rest.");
        
        //add items to kitchen
        kitchen.addItem("Meat", meat);
        kitchen.addItem("Stew", stew);
        kitchen.addItem("Cake", cake);
        
        //Livingroom items instantiated
        Item television = new Item("Television", "You recently acquired a new TV and now you have to get rid of this old one, "
                + "\n You choose to sell the old television on Ebay since there's nothing wrong with it. "
                + "\n Thereby you prevented generating more unnecessary electronical-waste");
        Item sofa = new Item("Sofa", "You want to get rid of your old sofa, what do you do? "
                + "\n Instead of throwing you sofa to a trashcontainer you choose to give it to a recycling center"
                + "\n because other people might still be able to use it.");
        Item cd = new Item("Cd", "When throwing out this cd,  you remember  to place it into the container for combustible waste, "
                + "\n so it can be used to generate electricity and heating for others.");
        
        //add items to Livingroom
        livingRoom.addItem("Television", television);
        livingRoom.addItem("Sofa", sofa);
        livingRoom.addItem("Cd", cd);
        
        //bedroom items instantiated
        Item clothing = new Item("Clothing", "You want to get rid of this old clothing you don't use anymore, "
                + "\n so you find a local recycling-center and donate it to prevent waste.");
        Item chipsBag = new Item("Chipsbag", "Next time you buy snacks or anything else, try to avoid buying items in plastic packaging");
        Item lightbulb = new Item("Lightbulb", "When the lightbulb is no longer of use to you, dispose of it at the nearest recycling-center.");
        
        //add items to Bedroom
        bedroom.addItem("Clothing", clothing);
        bedroom.addItem("Chipsbag", chipsBag);
        bedroom.addItem("Lightbulb", lightbulb);
        
        //office items instantiated
        Item laptop = new Item("Laptop", "When throwing out this laptop, "
                + "remember to place it into the electronic container.");
        Item coffee = new Item("Coffee", "Instead of buying coffee in cardboard cups, get a thermos-bottle instead. "
                + "\n This will prevent a lot of paper/carboard waste");
        Item calender = new Item("Calender", "Stop buying physical calendars and create them online to reduce waste of paper");
        
        //add items to office
        office.addItem("Laptop", laptop);
        office.addItem("Coffee", coffee);
        office.addItem("Calender", calender);
        
        //supermarket items instantiated
        Item bread = new Item("Bread", "When picking a pack of bread on the aisle, "
                + "be aware of the expiration date since most supermarkets throw out their expired products."
                + "\n By buying bread that expire right around the time you will have used it, you prevent waste of food products");
        Item fish = new Item("Fish", "While looking for fish for tonights dinner, you see a fish that expires today."
                + "\n You pick the fish that expires today, since it will still be fine to eat, and will most likely be thrown out by the supermarket tommorow");
        Item fruit = new Item("Fruit", "When buying fruit, look for fruits that are packaged in the least ammount of packagin possible."
                + "\n It is always preferrable to buy fruit and vegetables in loose weight since they usually aren't packaged at all");
        
        //add items to supermarket
        supermarket.addItem("Bread", bread);
        supermarket.addItem("Fish", fish);
        supermarket.addItem("Fruit", fruit);
        
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
            System.out.println(currentRoom.getItems().get(item).getDescription());
            System.out.println("Good Job! You scored 1 point");
            Player.points++;
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
            if (currentRoom == garden) {
                Item officeKey = new Key(office, "Key(office)", "a key to the office");
                currentRoom.addItem("Key(office)", officeKey);
                System.out.println("Key(office) has been dropped");

            } else if (currentRoom == office) {
                Item kitchenKey = new Key(kitchen, "Key(kitchen)", "a key to the kitchen");
                currentRoom.addItem("Key(kitchen)", kitchenKey);
                System.out.println("Key(kitchen) has been dropped");

            } else if (currentRoom == kitchen) {
                Item bedroomKey = new Key(bedroom, "Key(bedroom)", "a key to the bedroom");
                currentRoom.addItem("Key(bedroom)", bedroomKey);
                System.out.println("Key(bedroom) has been dropped");

            } else if (currentRoom == bedroom) {
                Item livingRoomKey = new Key(livingRoom, "Key(livingroom)", "a key to the livingroom");
                currentRoom.addItem("Key(livingroom)", livingRoomKey);
                System.out.println("Key(livingroom) has been dropped");

            } else if (currentRoom == livingRoom) {
                Item supermarketKey = new Key(supermarket, "Key(supermarket)", "a key to the supermarket");
                currentRoom.addItem("Key(supermarket)", supermarketKey);
                System.out.println("Key(supermarket) has been dropped");

            } else if (currentRoom == supermarket) {
                System.out.println("Your have completed all of the quizzes!");
            }
        } else {
            System.out.println("You have already completed this quiz. Please continue to another room");
        }   
    }

    //prints a welcome message on screen
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to TrashWorld " + player.getName() + "!");
        System.out.println("TrashWorld is a new game, that is here to help you understand how you can help save the environment!"
                + "\nTrashWorld is made to be a learning experience, for you to expand your knowledge in regards to how to handle waste in your everyday life."
                + "\nThe game is simple:"
                + "\n1: Pick up items around the rooms and learn something new!(note: after you pick up the items, you don't have to do anything else with them)"
                + "\n2: Complete the quizzes in the rooms to proceed to the next one"
                + "\n3: Score as many points as possible and see how you did in the end");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        System.out.println(currentRoom.printItems(currentRoom.getItems()));
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
        } else if (commandWord == CommandWord.ITEMS){
            System.out.println(currentRoom.printItems(currentRoom.getItems()));
        }
        return wantToQuit;
    }

    //prints all items from the inventory array
    public void printInventory() {
        if (Player.inventory.isEmpty()){
            System.out.println("Your inventory is empty at the moment. You can pick up items by typing 'take' followed by the name of the item");
            return;
        }
        String inventorystring = new String();
        System.out.println("Your inventory has the following items: ");
        for (int i = 0; i < Player.inventory.size(); i++) {
            inventorystring = inventorystring.concat("'" + Player.inventory.get(i).getName() + "'");
        }
        System.out.println(inventorystring);
    }

    //method that prints the help string
    private void printHelp() {
        System.out.println(currentRoom.getLongDescription());
        System.out.println("You called for the 'help' command!");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("");
        System.out.println("'go' 'direction' - moves you into the room that is in that direction."
                + "\n'quit' - quits the game."
                + "\n'help' - prints this again."
                + "\n'take' 'item' - picks up an item."
                + "\n'inventory' - displays the items that are currently held in inventory."
                + "\n'start quiz' - starts the quiz in the current room."
                + "\n'points' - displays your points."
                + "\n'items' - prints all items that are in the current room.");
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
        } else if(nextRoom.isUnlockable() || nextRoom == garden){
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println(currentRoom.printItems(currentRoom.getItems()));
        } else if (!nextRoom.isUnlockable()){
            System.out.println("Door is locked");
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
            System.out.println("You scored " + Player.points + " points total. Good job!");
            return true;
        }
    }

    //main method
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
