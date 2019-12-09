package com.mycompany.tworld;

import java.util.Scanner;

public class Game {

    private Parser parser;
    public Room currentRoom;
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
                + "\nTo the east is the path to the supermarket. "
                + "\nTo the west is the office. "
                + "\nTo the north is the kitchen. ");

        //everything that has to do with kitchen
        kitchen = new Room("in your kitchen. "
                + "\nTo the west is the living room."
                + "\nTo the south is the garden.");

        livingRoom = new Room("in your living room. "
                + "\nTo the south is the office. "
                + "\nTo the east is the kitchen."
                + "\nTo the north is the bedroom.");

        bedroom = new Room("in your bedroom. "
                + "\nTo the south is the living room.");

        office = new Room("in the office"
                + "\nTo the north is the living room."
                + "\nTo the east is the garden.");

        supermarket = new Room("in the supermarket"
                + "\nTo the west is the path home to your garden.");

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
        Item weed = new Item("Weeds", "You place the weeds in the compost heap "
                + "\nGood job!"
                + "\nThis action prevented waste to the environment.");
        Item grass = new Item("Grass", "When cutting grass, remember to leave the grass trimmings on the lawn. "
                + "\nIt will break down eventually and prevent waste in the form of plastic bags "
                + "\nif you instead had chosen to throw out the trimmings into plastic bags."
                + "\nIt will also prevent excess CO2 emission since it will be incinerated if you chose to throw it out.");
        Item apple = new Item("Apple", "Your apple-tree is dropping apples on the ground. "
                + "\nRemember that instead of throwing them out, apples can either be: "
                + "\n1. Put in your compost heap. When they start decomposing, they will provide great nutrients for the soil."
                + "\n2. Given out for free to people around you."
                + "\nThis will make sure that you don't generate any unnecessary waste for the environment");
        
        //add items to garden
        garden.addItem("Weed", weed);
        garden.addItem("Grass", grass);
        garden.addItem("Apple", apple);
        
        //kitchen items instantiated
        Item meat = new Item("Meat", "You bought too much meat. "
                + "\nYou choose to freeze down the remaining meat to prevent mictroorganisms from "
                + "\ndeveloping in there and to keep your meat fresh and edible. Nice!");
        Item stew = new Item("Stew", "You have some stew leftovers from last nights meal. "
                + "\nYou choose to eat it for dinner tonight instead of trowing it out and thereby you prevent generating more waste.");
        Item cake = new Item("Cake", "The expiration date of the cake has expired with two days but does show any sign of rotting."
                + "\nTo prevent the waste of food you choose to eat some of it and give away the rest.");
        
        //add items to kitchen
        kitchen.addItem("Meat", meat);
        kitchen.addItem("Stew", stew);
        kitchen.addItem("Cake", cake);
        
        //Livingroom items instantiated
        Item television = new Item("Television", "You recently acquired a new TV and now you have to get rid of the old one, "
                + "\nYou choose to sell the old television on Ebay since there's nothing wrong with it. "
                + "\nThereby you prevented generating more unnecessary electronical-waste");
        Item sofa = new Item("Sofa", "You want to get rid of your old sofa, what do you do? "
                + "\nInstead of throwing the sofa in a trash-container you choose to give it to a recycling center"
                + "\nbecause other people might still be able to use it. Good decision!");
        Item cd = new Item("Cd", "When throwing out this cd, you remember  to place it into the container for combustible waste"
                + "\nso it can be used to generate electricity and heating for others when the recycling center send it to be incinerated.");
        
        //add items to Livingroom
        livingRoom.addItem("Television", television);
        livingRoom.addItem("Sofa", sofa);
        livingRoom.addItem("Cd", cd);
        
        //bedroom items instantiated
        Item clothing = new Item("Clothing", "You want to get rid of this old clothing you don't use anymore,"
                + "\nso you find a local recycling-center and donate it to prevent waste.");
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
                + "\nThis will prevent a lot of paper/carboard waste");
        Item calender = new Item("Calendar", "Stop buying physical calendars and create them online to reduce waste of paper");
        
        //add items to office
        office.addItem("Laptop", laptop);
        office.addItem("Coffee", coffee);
        office.addItem("Calender", calender);
        
        //supermarket items instantiated
        Item bread = new Item("Bread", "When choosing a pack of bread on the aisle, "
                + "\npay attention to the expiration date since most supermarkets throw out their expired products."
                + "\nBy buying bread that expire right around the time you will have used it, you prevent waste of food products");
        Item fish = new Item("Fish", "While looking for fish for tonights dinner, you spot a fish that expires today."
                + "\nYou pick the fish that expires today, since it will still be fine to eat, "
                + "\nand will most likely be thrown out by the supermarket tommorow");
        Item fruit = new Item("Fruit", "When buying fruit, look for fruits that are packaged in the least ammount of packagin possible."
                + "\nIt is always preferrable to buy fruit and vegetables in loose weight since they usually aren't packaged at all");
        
        //add items to supermarket
        supermarket.addItem("Bread", bread);
        supermarket.addItem("Fish", fish);
        supermarket.addItem("Fruit", fruit);
        
        //gardenquiz created
        Question[] gardenQuestions = new Question[]{
            new Question("Scenario 1: Your garden has been fixed, but you have a lot of garden waste. "
                    + "\nWhat will you get rid of all the leaves, grass, sticks and weed?  "
                    + "\n(a) You'll burn it all bacause it is compost"
                    + "\n(b) You'll drive the compost to the recycling station "
                    + "\n(c) You'll put it in your green trash can", 
                    "b", 
                    "Correct! The recycling station have different trash-containers just for garden waste. "
                            + "\n Thereby you make sure that your garden waste is disposed of in the best possible way", 
                    "Wrong! If you want to get rid of compost properly, drive it to the recycling station."), 
            new Question("Scenario 2: You have to throw out a small trampoline that is broken. "
                    + "\nHow do you do it in the most sustainable and environmentally friendly way? "
                    + "\n(a) You drive it to the recycling station and throw it into a container "
                    + "\n(b) You separate the trampoline into: the jump cloth, edge protection, "
                    + "\nframe and springs. Afterwards you drive it to the recycling station. "
                    + "\n(c) You throw the trampoline in the green trashcan", 
                    "b", 
                    "Correct! Seperating the trampoline is the right way, you can then put it in the right containers for that specific material", 
                    "Wrong! The correct way is to split the trampoline into different materials, and put it in the right containers")};
        Quiz gardenQuiz = new Quiz(gardenQuestions);
        garden.setRoomQuiz(gardenQuiz);
        
        //kitchenquiz created
        Question[] kitchenQuestions = new Question[]{
            new Question("Scenario 1: You're planning to cook spaghetti bolognese for dinner. "
                    + "\nFor this occasion, you have bought one kilogram beef. However you have found out that you only need 500 grams. "
                    + "\nWhat will you do with the rest of the beef?  "
                    + "\n(a) You will throw out the meat in the trash can, since you are not going to use it."
                    + "\n(b) You decide to freeze the meat, since you want to save it for another day. "
                    + "\n(c) You leave the meat on the table.", 
                    "b", 
                    "Correct! -  Freezing meat will you the opportunity to use the remaining meat again.  Thereby you will avoid food waste. "
                            + "\nFood waste cost the Danes 13.5 billion crowns a year including VAT and taxes. "
                            + "\nFurthermore, every Dane throws out an average of 47 kg edible food every year.", 
                    "Wrong! -  The meat is still edible and therefore it is important to keep it, so you can make use of it again. "
                            + "\nEither you eat it the next day, or freeze it down. "
                            + "\nFood waste cost the Danes 13.5 billion crowns including VAT and taxes a year. "
                            + "\nFurthermore, every Dane throws out an average of 47 kilograms edible food every year."), 
            new Question("Scenario 2: You need to decide what you want to do with the packaging from the meat you just ate. "
                    + "\nThe packaging consists of plastic foil and plastic tray respectively. What do you do with the packaging? "
                    + "\n(a) You throw out the packaging unsorted. "
                    + "\n(b) You tear the plastic foil off the plastic tray, rinse the tray, and then sort the two varieties for waste. "
                    + "\n(c) You throw it over the fence to your neighbor.", 
                    "b", 
                    "Correct! - It is important to recycle as much waste as possible. "
                            + "\n Waste recycling options will be very limited if not sorted. "
                            + "\nTherefore, it is important that the plastic foil is sorted from the plastic tray", 
                    "Wrong! - It is important to recycle as much waste as possible. "
                            + "\nWaste recycling options will be very limited if it's not sorted beforehand. "
                            + "\n Therefore, it is important that the plastic foil is sorted from the plastic tray")};
        Quiz kitchenQuiz = new Quiz(kitchenQuestions);
        kitchen.setRoomQuiz(kitchenQuiz);
        
        //officequiz created
        Question[] officeQuestions = new Question[]{
            new Question("Scenario 1: Your office is getting renovated, but you have a lot of things lying on your table "
                    + "\nthat can't be used for anything (batteries, pens etc). "
                    + "\nHow do you want to get rid of these things? "
                    + "\n(a) You choose to throw it all in the trashcan "
                    + "\n(b) You go to the recycling station with the things that you want to get rid off. "
                    + "\nHere you sort the waste, so things get to the right places. "
                    + "\n(c) You go to the recycling station with the things. "
                    + "\nHere you choose to throw all the office supplies in the same container.", 
                    "b", 
                    "Correct! - It is important to sort waste, as there is a big difference between waste. "
                            + "\nWhen waste is mixed, it all ends up getting mixed in incineration. "
                            + "\n If, on the other hand, you choose to sort in the waste, "
                            + "\nthere is the possibility of recycling, which is better for the environment.", 
                    "Wrong! - It is important to sort waste, as there is a big difference between waste. "
                            + "\nWhen waste is mixed, it ends up in incineration. "
                            + "\n If, on the other hand, you choose to sort in the waste, "
                            + "\nthere is the possibility of recycling, which is better for the environment."), 
            new Question("Scenario 2: You have a lamp where the bulb has broken. "
                    + "\nWhat kind of bulb should you replace your current bulb with? "
                    + "\n(a) Incanescent bulb "
                    + "\n(b) Hydrogn bulb "
                    + "\n(c) LED bulb", 
                    "c", 
                    "Correct! - LED bulbs are the best bulb in the current market. It does not use much electricity, "
                            + "\n but they last incredibly long.", 
                    "Wrong! - LED bulbs are the best bulb in the current market. It does not use much electricity, "
                            + "\n but they last incredibly long. That is the best option.")};
        Quiz officeQuiz = new Quiz(officeQuestions);
        office.setRoomQuiz(officeQuiz);
        
        //livingroom-quiz created
        Question[] livingroomQuestions = new Question[]{
            new Question("Scenario 1: Your television is broken, at the same time you have a lot of old wires "
                    + "\nthat just need to be thrown out. Where do you throw it out? "
                    + "\n(a) You throw it in the trash. The TV and the wires can easily be in it, which is easiest. "
                    + "\n(b) You throw out the wires and run the TV at the recycling station.  "
                    + "\n(c) You drive in the recycling bin, and sort the TV and wires, into the right containers. ", 
                    "c", 
                    "Correct! - The right way is to sort the TV and corts in to there own containers", 
                    "Wrong! -  It is escpecialy importent to drive the TV and corts to the recycling station, "
                            + "\nand sort it in the right containers, because electric trash needs to be exposed the right way"), 
            new Question("Scenario 2: You have hosted a party, and the next morning there are a lot of bottles and cans left over. "
                    + "\nWhat is the most usable way to get rid of it? "
                    + "\n(a) You throw all the cans and bottles without a deposit in the trash, "
                    + "\nand go into the vending machine with the remaining deposit cans and bottles. "
                    + "\n(b) You throw all the cans and bottles out in the trash. "
                    + "\n(c) You go to the bottle refund machine with deposit cans and bottles, "
                    + "\nand drive to the recycling station with the remaining bottles and cans "
                    + "\nwhere you divide them into the right containers.", 
                    "c", 
                    "Correct!- The return percentage for bottles and cans in Denmark is 90%, "
                            + "\nbut at the same time it is also important to get rid of the remaining cans and bottles without a deposit in the correct way. "
                            + "\nAt the recycling stations, there are specific containers for both cans and bottles, "
                            + "\nas they will then be recycled in the same way as if it were pledged. "
                            + "\nTherefore, do not mix bottles and cans with your other garbage as it will not be recycled.", 
                    "Wrong! - It is important to sort bottles and cans, and bottles that cannot be returned must be taking to the recycling station")};
        Quiz livingroomQuiz = new Quiz(livingroomQuestions);
        livingRoom.setRoomQuiz(livingroomQuiz);
        
        //bedroomquiz created
        Question[] bedroomQuestions = new Question[]{
            new Question("Scenario 1: You can no longer fit your old trousers, so you want to get rid of them. "
                    + "\nHow can you get rid of them most environmentally and possibly get some new ones?  "
                    + "\n(a) You throw them out in the rycycling station, and then buy some new clothes from a store."
                    + "\n(b) You buy some new clothes and leave the other one in the wardrobe. "
                    + "\n(c) You take your old clothes to the Red Cross organisation, and only buy only what you need maybe at a secondhand store.", 
                    "c", 
                    "Correct! - Right - The textile industry is the second most polluting industry in the world. "
                            + "\nOnly surpassed by the oil and gas industry. "
                            + "\nTherefore it is important that you think about what you do with your clothes and how to get something new. "
                            + "\nHere it is essential that you recycle your clothes and that you do not feel the need to always have something new.", 
                    "Wrong! -  Wrong! - The textile industry is the second most polluting industry in the world. "
                            + "\nOnly surpassed by the oil and gas industry. "
                            + "\nTherefore it is important that you think about what you do with your clothes and how to get something new. "
                            + "\nHere it is essential that you recycle your clothes and that you do not feel the need to always have something new."), 
            new Question("Scenario 2: You read books before going to bed in the evening, and you've just finished the last one. "
                    + "\nWhat would be a good thing to do when buying books? "
                    + "\n(a) You'll buy cheap book at the supermarket. "
                    + "\n(b) You'll buy books, at the bookstore "
                    + "\n(c) You'll start buying e-book.", 
                    "c", 
                    "Correct! - To avoid the large production behind the production of a book, "
                            + "\nit is far more environmentally conscious to buy an e-book.", 
                    "Wrong! - To avoid the large production behind the production of a book, "
                            + "\nit is far more environmentally conscious to buy an e-book.")};
        Quiz bedroomQuiz = new Quiz(bedroomQuestions);
        bedroom.setRoomQuiz(bedroomQuiz);
        
        //supermarket-quiz created
        Question[] supermarketQuestions = new Question[]{
            new Question("Scenario 1: You have now arrived at the supermarket. You have to shop. What is the most important thing to think about when shopping?  "
                    + "\n(a) You only buy what you need, ensuring that it is eaten while at the same time ensuring that nothing is wasted."
                    + "\n(b) You only think about what you want to eat. Therefore, you end up buying redundant quantities. "
                    + "\n(c) You think about the price. You buy cheap and you don't care if you buy unnecessary quantities.", 
                    "a", 
                    "Correct! - It is important to think about the amount of food and drink you buy when you shop, "
                            + "\nas food waste impacts the environment and costs billions in lost money.", 
                    "Wrong! - It is important to think about the amount of food and drink you buy when you shop, "
                            + "\nas food waste impacts the environment and costs billions in lost money."), 
            new Question("Scenario 2: You have to pay for your groceries and you realize that you haven't brought a bag to carry them home. "
                    + "\nWhat do you do? "
                    + "\n(a) Buy a plastic bag for 1 DKK. "
                    + "\n(b) Buy a paper bag for 2.5 DKK. "
                    + "\n(c) Buy a fabric bag for 10 DKK.", 
                    "c", 
                    "Correct! - The fabric bag cab be reused an almost infinite number of times and thereby you reduce the ammount of waste you produce.", 
                    "Wrong! - The fabric bag is the way to go. These types of bags can be reused and almost infinite number of times. "
                            + "\n On the other hand, the plastic and/or paper bags are only good for a few times and then they have to be thrown out.")};
        Quiz supermarketQuiz = new Quiz(supermarketQuestions);
        supermarket.setRoomQuiz(supermarketQuiz);

        //defines starting room
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
                    System.out.println(currentRoom.getRoomQuiz().questions[i].getCorrectResponse());
                    Player.points++;
                    System.out.println("You received 1 point!");
                } else {
                    System.out.println(currentRoom.getRoomQuiz().questions[i].getIncorrectResponse());
                    System.out.println("You didn't receive any points for that answer.");
                }
            }
            
            currentRoom.getRoomQuiz().isDone = true;
            
            System.out.println("You have completed the quiz!");
            if (currentRoom == garden) {
                Item officeKey = new Key(office, "Key(office)", "a key to the office");
                currentRoom.addItem("Key(office)", officeKey);
                System.out.println("Key(office) has been dropped don't forget to pick it up!");

            } else if (currentRoom == office) {
                Item livingRoomKey = new Key(livingRoom, "Key(livingroom)", "a key to the livingroom");
                currentRoom.addItem("Key(livingroom)", livingRoomKey);
                System.out.println("Key(livingroom) has been dropped, don't forget to pick it up!");

            } else if (currentRoom == kitchen) {
                Item bedroomKey = new Key(bedroom, "Key(bedroom)", "a key to the bedroom");
                currentRoom.addItem("Key(bedroom)", bedroomKey);
                System.out.println("Key(bedroom) has been dropped don't forget to pick it up!");

            } else if (currentRoom == bedroom) {
                Item supermarketKey = new Key(supermarket, "Key(supermarket)", "a key to the supermarket");
                currentRoom.addItem("Key(supermarket)", supermarketKey);
                System.out.println("Key(supermarket) has been dropped don't forget to pick it up!");

            } else if (currentRoom == livingRoom) {
                Item kitchenKey = new Key(kitchen, "Key(kitchen)", "a key to the kitchen");
                currentRoom.addItem("Key(kitchen)", kitchenKey);
                System.out.println("Key(kitchen) has been dropped don't forget to pick it up!");

            } else if (currentRoom == supermarket) {
                System.out.println("You have completed all of the quizzes!");
            }
        } else {
            System.out.println("You have already completed this quiz. Please continue to another room");
        }   
    }

    //prints a welcome message on screen
    public void printWelcome() {
        System.out.println();
        System.out.println("Welcome to TrashWorld " + player.getName() + "!");
        System.out.println("TrashWorld is a new game, that is here to help you understand "
                + "\nhow YOU can help save the environment from drowning in trash!"
                + "\nTrashWorld is made to be a learning experience, for you to expand your knowledge in regards to "
                + "\nhow to handle waste in your everyday life."
                + "\nThe game is simple:"
                + "\n1: Pick up items around the rooms and learn something new!"
                + "\n(note: after you pick up the items, you don't have to do anything else with them)"
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
    public void printHelp() {
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
                + "\n'items' - displays all items that are in the current room.");
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
            System.out.println("That door is locked! You may need to complete another quiz to get the key for this door.");
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
        App.load();
    }
}
