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
        Item weed = new Item("Weeds", "You place the weeds in your compost heap "
                + "\nGood job!"
                + "\nThis action prevented additional waste-production.");
        Item grass = new Item("Grass", "When mowing your lawn, remember to leave the grass trimmings on the lawn. "
                + "\nIt will break down eventually and prevent additional waste in the form of plastic bags "
                + "\nif you instead had chosen to put the trimmings into plastic bags like most people do."
                + "\nIt will also prevent excess CO2 emission since it will be incinerated if you had chosen to throw it out.");
        Item apple = new Item("Apple", "Your apple-tree is dropping. "
                + "\nRemember that instead of throwing them out, apples can either be: "
                + "\n1. Put in your compost heap. When they start decomposing, they will provide the soil with great nutrients."
                + "\n2. Given out for free to people around you."
                + "\nThis will make sure that you don't produce any unnecessary waste for the environment");
        
        //add items to garden
        garden.addItem("Weed", weed);
        garden.addItem("Grass", grass);
        garden.addItem("Apple", apple);
        
        //kitchen items instantiated
        Item meat = new Item("Meat", "Oh no! You bought too much meat yesterday. "
                + "\nYou choose to freeze down the remaining meat to prevent the meat from "
                + "\ngoing bad and to keep your meat fresh and edible. Nice!");
        Item stew = new Item("Stew", "You have some leftover stew from last nights meal. "
                + "\nYou choose to eat it for dinner tonight instead of trowing it out and thereby you prevent producing more waste.");
        Item cake = new Item("Cake", "The cake has expired two days ago but does show any sign of rotting."
                + "\nTo prevent wasting food you choose to eat some of it and give away the rest.");
        
        //add items to kitchen
        kitchen.addItem("Meat", meat);
        kitchen.addItem("Stew", stew);
        kitchen.addItem("Cake", cake);
        
        //Livingroom items instantiated
        Item television = new Item("Television", "You recently bought a new TV and now you have to get rid of the old one. "
                + "\nYou choose to sell the old television on Ebay since there's nothing wrong with it. "
                + "\nYou prevented generating more unnecessary electronical-waste by doing this, good job!");
        Item sofa = new Item("Sofa", "You want to get rid of your old sofa. "
                + "\nInstead of throwing out the sofa you choose to give it away to a recycling center"
                + "\nbecause other people still might be able to use it. Good decision!");
        Item cd = new Item("Cd", "When throwing out this cd, you remember to place it in the container for combustible waste"
                + "\nso it can be used to generate electricity and heating for others when the recycling center sends it to be incinerated.");
        
        //add items to Livingroom
        livingRoom.addItem("Television", television);
        livingRoom.addItem("Sofa", sofa);
        livingRoom.addItem("Cd", cd);
        
        //bedroom items instantiated
        Item clothing = new Item("Clothing", "You want to get rid of this old clothing that you don't use anymore,"
                + "\nso you find a local recycling-center and donate it to prevent waste.");
        Item chipsBag = new Item("Chipsbag", "Next time you buy snacks or anything else, try to avoid buying items in plastic packaging");
        Item lightbulb = new Item("Lightbulb", "When the lightbulb is no longer of use to you, dispose of it at the nearest recycling-center.");
        
        //add items to Bedroom
        bedroom.addItem("Clothing", clothing);
        bedroom.addItem("Chipsbag", chipsBag);
        bedroom.addItem("Lightbulb", lightbulb);
        
        //office items instantiated
        Item laptop = new Item("Laptop", "When you one day have to throw out this laptop, "
                + "remember to place it into the electronical waste container at the recycling-center.");
        Item coffee = new Item("Coffee", "Instead of buying coffee in cardboard cups, get a thermos-bottle. "
                + "\nThis will prevent a lot of paper/carboard waste");
        Item calender = new Item("Calendar", "Stop buying physical calendars and create them online to reduce waste of paper");
        
        //add items to office
        office.addItem("Laptop", laptop);
        office.addItem("Coffee", coffee);
        office.addItem("Calender", calender);
        
        //supermarket items instantiated
        Item bread = new Item("Bread", "When picking a pack of bread on the aisle, "
                + "\npay attention to the expiration date, since most supermarkets throw out their expired products."
                + "\nBy buying bread that expire right around the time you will have used it, you prevent additional waste of food products");
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
            new Question("Scenario 1: A lot of garden work has been done, but now you have a lot of garden-waste. "
                    + "\nWhat will you do to get rid of all the leaves, grass, sticks and weed?  "
                    + "\n(a) You'll burn it all because it's organic material anyways"
                    + "\n(b) You'll take the garden-waste to the recycling-center"
                    + "\n(c) You'll put it in your green trash can", 
                    "b", 
                    "Correct! The recycling-center has different trash-containers specifically for garden waste. "
                            + "\n Thereby you make sure that your garden waste is disposed of in the right manner", 
                    "Wrong! If you want to get rid of compost properly, drive it to the recycling-center."), 
            new Question("Scenario 2: You have to throw out a small trampoline that is broken. "
                    + "\nHow do you do it in the most sustainable and environmentally friendly way? "
                    + "\n(a) You drive it to the recycling station and throw it into a container "
                    + "\n(b) You separate the trampoline into: the jump cloth, edge protection, "
                    + "\nframe and springs. Afterwards you drive it to the recycling station and place the parts in the"
                    + "\ncorresponding containers. "
                    + "\n(c) You throw the trampoline in the green trashcan", 
                    "b", 
                    "Correct! Seperating the trampoline is the right way, you can then put it in the right containers for that specific material", 
                    "Wrong! The correct way is to split the trampoline into different materials, and put it in the right containers")};
        Quiz gardenQuiz = new Quiz(gardenQuestions);
        garden.setRoomQuiz(gardenQuiz);
        
        //kitchenquiz created
        Question[] kitchenQuestions = new Question[]{
            new Question("Scenario 1: You're planning to cook spaghetti bolognese for dinner. "
                    + "\nFor this occasion, you have bought one kilogram of beef. However you've found out that you only need 500 grams. "
                    + "\nWhat will you do with the rest of the beef?  "
                    + "\n(a) You will throw the meat out in the trash can, since you are not going to use it."
                    + "\n(b) You decide to put the meat in the freezer, since you want to save it for another day. "
                    + "\n(c) You leave the meat on the table.", 
                    "b", 
                    "Correct! -  Putting the meat in the freezer gives you the opportunity to use the remaining meat another time.  "
                            + "\nThereby you will avoid food waste. "
                            + "\nFood waste cost the Danes 13.5 billion crowns a year including VAT and taxes. "
                            + "\nFurthermore, every Dane throws out an average of 47 kgs of edible food every year.", 
                    "Wrong! -  The meat is still edible and therefore it is important to keep it, so you can make use of it again. "
                            + "\nYou can either eat it the next day or put it in the freezer. "
                            + "\nFood waste cost the Danes 13.5 billion crowns including VAT and taxes a year. "
                            + "\nFurthermore, every Dane throws out an average of 47 kgs of edible food every year."), 
            new Question("Scenario 2: You need to decide what you want to do with the packaging from the meat you just ate. "
                    + "\nThe packaging consists of plastic foil and plastic tray respectively. What do you do with the packaging? "
                    + "\n(a) You throw all the packaging in the trashcan unsorted. "
                    + "\n(b) You tear the plastic foil off the plastic tray, rinse the tray, and then put the parts in their respective waste bins. "
                    + "\n(c) You throw it over the fence to your neighbor.", 
                    "b", 
                    "Correct! - It is important to recycle as much waste as possible. "
                            + "\nThe options for recycling your waste will be very limited if it's not sorted. "
                            + "\nTherefore, it is important that the plastic foil is sorted from the plastic tray", 
                    "Wrong! - It is important to recycle as much waste as possible. "
                            + "\nThe options for recycling your waste will be very limited if it's not sorted. "
                            + "\nTherefore, it is important that the plastic foil is sorted from the plastic tray")};
        Quiz kitchenQuiz = new Quiz(kitchenQuestions);
        kitchen.setRoomQuiz(kitchenQuiz);
        
        //officequiz created
        Question[] officeQuestions = new Question[]{
            new Question("Scenario 1: Your office is getting renovated, but you have a lot of things laying around on your table "
                    + "\nthat can't be used for anything (batteries, pens etc). "
                    + "\nHow do you want to get rid of this stuff? "
                    + "\n(a) You choose to throw it all in the trashcan "
                    + "\n(b) You go to the recycling station with the things that you want to get rid off. "
                    + "\nHere you sort the waste, so the right things get to the right places. "
                    + "\n(c) You go to the recycling station with the things. "
                    + "\nHere you choose to throw all the office supplies in the same container.", 
                    "b", 
                    "Correct! - It is important to sort waste, as there is a big difference in the types of waste. "
                            + "\nWhen waste is mixed, it all ends up getting mixed together in incineration. "
                            + "\n If, on the other hand, you choose to sort in the waste, "
                            + "\nit could be possible to recycle the materials, which is better for the environment.", 
                    "Wrong! - It is important to sort waste, as there is a big difference in the types of waste. "
                            + "\nWhen waste is mixed, all ends up getting mixed together in incineration. "
                            + "\nIf, on the other hand, you choose to sort in the waste, "
                            + "\nit could be possible to recycle the materials, which is better for the environment."), 
            new Question("Scenario 2: You have a lamp where the bulb is broken. "
                    + "\nWhat kind of bulb should you replace your current bulb with? "
                    + "\n(a) Incandescent bulb "
                    + "\n(b) Halogen bulb "
                    + "\n(c) LED bulb", 
                    "c", 
                    "Correct! - LED bulbs are the best bulb in the current market in terms of unnecessary waste production. "
                            + "\nThey don't use much electricity, but they last a long time.", 
                    "Wrong! - LED bulbs are the best bulb in the current market. They don't use much electricity, "
                            + "\nbut they last a long time. That is the best option.")};
        Quiz officeQuiz = new Quiz(officeQuestions);
        office.setRoomQuiz(officeQuiz);
        
        //livingroom-quiz created
        Question[] livingroomQuestions = new Question[]{
            new Question("Scenario 1: Your television is broken, and you have a lot of old wires laying around "
                    + "\nthat just need to be thrown out. How do you dispose of all this? "
                    + "\n(a) You throw it in the trash. The TV and the wires can easily be in it, and this is the easiest. "
                    + "\n(b) You throw out the wires and take the TV to the recycling-center.  "
                    + "\n(c) You drive to the recycling-center, and sort the TV and wires, into the right containers. ", 
                    "c", 
                    "Correct! - Sorting the TV and cords in to there own containers is the right way", 
                    "Wrong! -  It is especially important to bring the TV and cords to the recycling station, "
                            + "\nand sort it in the right containers, because electrical-waste needs to be disposed of in the right way"), 
            new Question("Scenario 2: You have hosted a party, and the next morning there are a lot of bottles and cans left over. "
                    + "\nWhat is the best way to get rid of it in terms of reducing the pressure on the environment and your waste production? "
                    + "\n(a) You throw out all the cans and bottles that don't have a deposit, "
                    + "\nand go to the vending machine with the remaining cans and bottles that do have a deposit. "
                    + "\n(b) You throw all the cans and bottles out in the trash. "
                    + "\n(c) You go to the bottle refund machine with deposit cans and bottles, "
                    + "\nand drive to the recycling station with the remaining bottles and cans "
                    + "\nwhere you divide them into the right containers.", 
                    "c", 
                    "Correct! - The return percentage for bottles and cans in Denmark is 90%, "
                            + "\nbut at the same time it is also important to get rid of the remaining cans and bottles without a deposit in the correct way. "
                            + "\nAt the recycling stations, there are specific containers for both cans and bottles, "
                            + "\nas they will then be recycled in the they are supposed to. "
                            + "\nTherefore, do not mix bottles and cans with your other garbage as it will not be recycled.", 
                    "Wrong! - It is important to sort bottles and cans, and bottles that cannot be returned must be taken to the recycling-center")};
        Quiz livingroomQuiz = new Quiz(livingroomQuestions);
        livingRoom.setRoomQuiz(livingroomQuiz);
        
        //bedroomquiz created
        Question[] bedroomQuestions = new Question[]{
            new Question("Scenario 1: Your old pants no longer fit you, so you want to get rid of them and get a new pair. "
                    + "\nHow do you handle this situation with waste reduction in mind? "
                    + "\n(a) You take them to the recycling-center, and then buy a new pair from a store."
                    + "\n(b) You buy some new trousers and leave the old pair in the wardrobe. "
                    + "\n(c) You take your old clothes to the Red Cross organisation so that someone else can use them, "
                    + "\nand buy a new pair at a secondhand store.", 
                    "c", 
                    "Correct! - The textile industry is the second most polluting industry in the world. "
                            + "\nOnly surpassed by the oil and gas industry. "
                            + "\nTherefore it is important that you think about what you do with your clothes and how to get something new. "
                            + "\nHere it is essential that you recycle your clothes and that you do not feel the need to always have something new.", 
                    "Wrong! -  Wrong! - The textile industry is the second most polluting industry in the world. "
                            + "\nOnly surpassed by the oil and gas industry. "
                            + "\nTherefore it is important that you think about what you do with your clothes and how to get something new. "
                            + "\nHere it is essential that you recycle your clothes and that you do not feel the need to always have something new."), 
            new Question("Scenario 2: You like to read before going to bed in the evening, and you've just finished your latest book."
                    + "\nYou need something new to read. "
                    + "\nWhat would be a the right way to acquire more reading material? "
                    + "\n(a) You'll buy cheap book at the supermarket. "
                    + "\n(b) You'll buy books at the bookstore "
                    + "\n(c) You'll buy e-books.", 
                    "c", 
                    "Correct! - To avoid the waste of paper that is left behind in the production of books, "
                            + "\nit is far more environmentally conscious to buy an e-book.", 
                    "Wrong! - To avoid the waste of paper that is left behind in the production of books, "
                            + "\nit is far more environmentally conscious to buy an e-book.")};
        Quiz bedroomQuiz = new Quiz(bedroomQuestions);
        bedroom.setRoomQuiz(bedroomQuiz);
        
        //supermarket-quiz created
        Question[] supermarketQuestions = new Question[]{
            new Question("Scenario 1: You have now arrived at the supermarket. You have to buy your groceries. "
                    + "\nWhat is the most important thing to keep in mind when shopping?  "
                    + "\n(a) You only buy what you need, ensuring that it it'll be used up while at the same time ensuring that nothing is wasted."
                    + "\n(b) You only think about what you want to eat. Therefore, you end up buying redundant quantities. "
                    + "\n(c) You think about the price. You buy cheap and you don't care if you buy unnecessary quantities.", 
                    "a", 
                    "Correct! - It is important to think about the amount of food and drink you buy when you shop, "
                            + "\nas food waste impacts the environment and costs billions in lost money.", 
                    "Wrong! - It is important to think about the amount of food and drink you buy when you shop, "
                            + "\nas food waste impacts the environment and costs billions in lost money."), 
            new Question("Scenario 2: You have to pay for your groceries and you realize that you haven't brought a bag to carry them home in. "
                    + "\nWhat do you do? "
                    + "\n(a) Buy a plastic bag for 1 DKK. "
                    + "\n(b) Buy a paper bag for 2.5 DKK. "
                    + "\n(c) Buy a fabric bag for 10 DKK.", 
                    "c", 
                    "Correct! - The fabric bag can be reused a lot more than its alternatives and thereby you reduce the ammount of waste you produce." 
                            + "\n On the other hand, the plastic and/or paper bags are only good for a few times and then they have to be thrown out.",
                    "Wrong! - The fabric bag is the way to go. These types of bags can be reused a lot more than its alternatives. "
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
        System.out.println("Thank you for playing TrashWorld" + player.getName() + ".  Good bye.");
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
