package com.mycompany.tworld;

import java.io.IOException;
import java.io.PrintStream;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController {

    //instantiates all necessary game objects
    Game game = new Game(); //creates instance of Game
    ObservableList<String> inventoryItems = observableArrayList(Player.inventoryToString()); //instantiates inventory as an observableArrayList
    ObservableList<String> roomItems = observableArrayList(game.currentRoom.getRoomItemsList()); //instantiates an observableArrayList for items in the rooms

    //creates image objects for the map
    Image gardenMap = new Image("file:\\C:\\Users\\svane\\Documents\\NetBeansProjects\\TWorld\\src\\assets\\img\\garden.png");
    Image officeMap = new Image("file:\\C:\\Users\\svane\\Documents\\NetBeansProjects\\TWorld\\src\\assets\\img\\office.png");
    Image kitchenMap = new Image("file:\\C:\\Users\\svane\\Documents\\NetBeansProjects\\TWorld\\src\\assets\\img\\kitchen.png");
    Image livingroomMap = new Image("file:\\C:\\Users\\svane\\Documents\\NetBeansProjects\\TWorld\\src\\assets\\img\\livingroom.png");
    Image bedroomMap = new Image("file:\\C:\\Users\\svane\\Documents\\NetBeansProjects\\TWorld\\src\\assets\\img\\bedroom.png");
    Image supermarketMap = new Image("file:\\C:\\Users\\svane\\Documents\\NetBeansProjects\\TWorld\\src\\assets\\img\\supermarket.png");

    //defines all the GUI controls
    @FXML
    Button playButton;

    @FXML
    Button quitButton;

    @FXML
    Button startQuiz;

    @FXML
    Button rightArrow;

    @FXML
    Button leftArrow;

    @FXML
    Button upArrow;

    @FXML
    Button downArrow;

    @FXML
    Button answerAButton;

    @FXML
    Button answerBButton;

    @FXML
    Button answerCButton;

    @FXML
    Window primaryStage;

    @FXML
    TextField nameField;

    @FXML
    TextField pointsField;

    @FXML
    public TextArea console;

    @FXML
    ListView itemsInRoom;

    @FXML
    ListView inventory;

    @FXML
    ImageView map;

    //redefines system.out to print to the console inside the GUI instead ot the Command Line / Standard output.
    @FXML
    public void consoleUpdater() {
        PrintStream printStream = new PrintStream(new CustomOutputStream(console)); //creates new PrintStream that points to console
        System.setOut(printStream); //sets System.out to the new printstream
        System.setErr(printStream); //sets System.err to the new printstream
    }

    //method for picking up items
    @FXML
    private void takeAction() throws IOException {
        if (itemsInRoom.getSelectionModel().isEmpty()) { //checks if the itemlist in the room is empty
            System.out.println("---------------------------------------------------------");
            System.out.println("There are no more items in the room!");
        } else {
            String itemName = itemsInRoom.getSelectionModel().getSelectedItem().toString(); //creates a string with the name of the selected item
            Item itemToBeTaken = game.currentRoom.getItems().get(itemName); //creates an item object that corresponds with the string above

            System.out.println("---------------------------------------------------------");
            System.out.println("You picked up: " + game.currentRoom.getItems().get(itemName).getName() + ". You gained 1 point!"); //prints the item to screen
            System.out.println(game.currentRoom.getItems().get(itemName).getDescription()); //prints the description of that item

            Player.inventory.add(itemToBeTaken); //adds item to player inventory
            inventoryItems.add(itemName); //adds item to observableArrayList inventory
            game.currentRoom.removeItem(itemName); //removes item from itempool in room
            roomItems.remove(itemName); //removes item from the observableArrayList that shows the rooms current items
            itemsInRoom.setItems(roomItems); //updates the listView with the updated observableArrayList
            Player.points++; //adds 1 point to the players score
            pointsField.setText(Integer.toString(Player.points)); //updates the textField that show the players score
        }
    }

    @FXML
    //runs the help command
    private void helpAction() throws IOException {
        System.out.println("---------------------------------------------------------");
        game.printHelp();
    }

    //closes the window and thereby quits the game
    @FXML
    private void quitAction() throws IOException {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    //method for the play-button - opens a dialog-box where the player can enter their name before the game starts
    @FXML
    private void playGame() throws IOException {
        consoleUpdater(); //runs the method that redirects system.out
        final Stage dialog = new Stage(); //create a stage for the dialog-box

        dialog.initModality(Modality.NONE); //defines that the dialog-box should NOT block the rest of the GUI
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);

        //sets text for the text label
        dialogVbox.getChildren().add(new Text("Please enter your name:"));
        TextField nameBox = new TextField();
        dialogVbox.getChildren().add(nameBox);

        //sets text for the button
        Button okBtn = new Button("OK");
        dialogVbox.getChildren().add(okBtn);

        //defines size of the dialog box
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);

        //shows the dialog box
        dialog.show();

        //sets the eventhandler for the button
        okBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String name = nameBox.getText();
                nameField.setText(name); //transfers the name that the player has entered to the texfield behind the dialog box
                Player.setName(name); //sets the name for the player in the player-class as well
                pointsField.setText(Integer.toString(Player.points)); //defines where the points textField gets its data from
                dialog.close(); //closes the dialogbox
                game.printWelcome();
                inventory.setItems(inventoryItems); //sets the players inventory to show any inventoryItems there might be from the beginning
                itemsInRoom.setItems(roomItems); //is here so the room in which the player spawns also shows a list of items
                playButton.setDisable(true);
            }
        });
    }

    //one of the commands to go in a specific direction
    @FXML
    public void goWest() {
        String direction = "west";
        Room nextRoom = game.currentRoom.getExit(direction); //defines a variable of type: Room to point to the room which the player wants to go to
        System.out.println("---------------------------------------------------------");
        if (nextRoom == null) { //if the direction in which the player wants to go does not lead to a room
            System.out.println("There is no door!");
        } else if (nextRoom.isUnlockable() || nextRoom == game.garden) { //if the direction in which the player wants to go DOES lead to a room, and that room is unlockable
            game.currentRoom = nextRoom; //moves the player by redefining the currentRoom variable to instead be the value of nextRoom
            System.out.println(game.currentRoom.getLongDescription()); //prints the description from the new room
            System.out.println(game.currentRoom.printItems(game.currentRoom.getItems())); //prints the items that currently are in the room
            roomItems = observableArrayList(game.currentRoom.getRoomItemsList()); //updates the ListView to contain the new rooms items
            itemsInRoom.setItems(roomItems); //updates the observableList so the user can see new the items
            mapUpdater(); //updates the map so it reflects the new location
        } else if (!nextRoom.isUnlockable()) { //if the room is locked
            System.out.println("That door is locked! You may need to complete another quiz to get the key for this door.");
        }
    }

    //does the same as goWest, but in another direction
    @FXML
    public void goEast() {
        String direction = "east";
        Room nextRoom = game.currentRoom.getExit(direction);
        System.out.println("---------------------------------------------------------");
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else if (nextRoom.isUnlockable() || nextRoom == game.garden) {
            game.currentRoom = nextRoom;
            System.out.println(game.currentRoom.getLongDescription());
            System.out.println(game.currentRoom.printItems(game.currentRoom.getItems()));
            roomItems = observableArrayList(game.currentRoom.getRoomItemsList());
            itemsInRoom.setItems(roomItems);
            mapUpdater();
        } else if (!nextRoom.isUnlockable()) {
            System.out.println("That door is locked! You may need to complete another quiz to get the key for this door.");
        }
    }

    //does the same as goWest, but in another direction
    @FXML
    public void goNorth() {
        String direction = "north";
        Room nextRoom = game.currentRoom.getExit(direction);
        System.out.println("---------------------------------------------------------");
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else if (nextRoom.isUnlockable() || nextRoom == game.garden) {
            game.currentRoom = nextRoom;
            System.out.println(game.currentRoom.getLongDescription());
            System.out.println(game.currentRoom.printItems(game.currentRoom.getItems()));
            roomItems = observableArrayList(game.currentRoom.getRoomItemsList());
            itemsInRoom.setItems(roomItems);
            mapUpdater();
        } else if (!nextRoom.isUnlockable()) {
            System.out.println("That door is locked! You may need to complete another quiz to get the key for this door.");
        }
    }
    
    //does the same as goWest, but in another direction
    @FXML
    public void goSouth() {
        String direction = "south";
        Room nextRoom = game.currentRoom.getExit(direction);
        System.out.println("---------------------------------------------------------");
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else if (nextRoom.isUnlockable() || nextRoom == game.garden) {
            game.currentRoom = nextRoom;
            System.out.println(game.currentRoom.getLongDescription());
            System.out.println(game.currentRoom.printItems(game.currentRoom.getItems()));
            roomItems = observableArrayList(game.currentRoom.getRoomItemsList());
            itemsInRoom.setItems(roomItems);
            mapUpdater();
        } else if (!nextRoom.isUnlockable()) {
            System.out.println("That door is locked! You may need to complete another quiz to get the key for this door.");
        }
    }

    //prints the correct text for the quiz that corresponds with the room in which the player is located
    public void startQuizPrompt() {
        System.out.println("---------------------------------------------------------");
        if (game.currentRoom.getRoomQuiz().isDone == false) { //if the quiz in the currentRoom has not been completed
            game.currentRoom.getRoomQuiz().started = true; //sets a variable that checks whether the quiz has been started or not to true
            if (game.currentRoom == game.garden) { //checks what room the player is in
                System.out.println("You start the quiz for garden.");
            } else if (game.currentRoom == game.office) {
                System.out.println("You start the quiz for office");
            } else if (game.currentRoom == game.kitchen) {
                System.out.println("You start the quiz for kitchen");
            } else if (game.currentRoom == game.supermarket) {
                System.out.println("You start the quiz for supermarket");
            } else if (game.currentRoom == game.bedroom) {
                System.out.println("You start the quiz for bedroom");
            } else if (game.currentRoom == game.livingRoom) {
                System.out.println("You start the quiz for living room");
            }
            System.out.println("Consider the following: ");
            System.out.println(game.currentRoom.getRoomQuiz().questions[0].getPrompt()); //prints the prompt for the first question in the quiz
        } else {
            System.out.println("You have already completed this quiz");
        }
    }

    //prints the correct question for the player depending on how many questions they have answered
    public void questionPrompt() {
        if (game.currentRoom.getRoomQuiz().questions.length > 2) {
            if (!game.currentRoom.getRoomQuiz().questions[0].isFinished()) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Consider the following: ");
                System.out.println(game.currentRoom.getRoomQuiz().questions[0].getPrompt());
            } else if (!game.currentRoom.getRoomQuiz().questions[1].isFinished()) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Consider the following: ");
                System.out.println(game.currentRoom.getRoomQuiz().questions[1].getPrompt());
            } else if (!game.currentRoom.getRoomQuiz().questions[2].isFinished()) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Consider the following: ");
                System.out.println(game.currentRoom.getRoomQuiz().questions[2].getPrompt());
            } else {
                game.currentRoom.getRoomQuiz().isDone = true; //if the quiz has been completed
                System.out.println("You have completed the quiz in this room!");
                game.currentRoom.getRoomQuiz().started = false; //the quiz is no longer active
                dropKey(); //drops the key that corresponds with the next room the player should go to
            }
        } else {
            if (!game.currentRoom.getRoomQuiz().questions[0].isFinished()) { //this branch is there in case there are quizzes with only 2 questions
                System.out.println("---------------------------------------------------------");
                System.out.println("Consider the following: ");
                System.out.println(game.currentRoom.getRoomQuiz().questions[0].getPrompt());
            } else if (!game.currentRoom.getRoomQuiz().questions[1].isFinished()) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Consider the following: ");
                System.out.println(game.currentRoom.getRoomQuiz().questions[1].getPrompt());
            } else {
                game.currentRoom.getRoomQuiz().isDone = true;
                System.out.println("You have now completed the quiz in this room!");
                game.currentRoom.getRoomQuiz().started = false;
                dropKey();
            }
        }
    }

    //fetches the first question in the array that has not been answered
    public Question getCurrentQuestion() {
        Question currentQuestion = null;
        for (Question question : game.currentRoom.getRoomQuiz().questions) {
            if (!question.isFinished()) {
                currentQuestion = question;
                break;
            }
        }
        return currentQuestion;
    }

    //checks if A is the correct answer to the question and prints the next question in line
    @FXML
    public void checkAnswerA() {
        if (!game.currentRoom.getRoomQuiz().isDone && game.currentRoom.getRoomQuiz().started) { //if the quiz has not been completed and the quiz has been started
            if (getCurrentQuestion().getAnswer().equals("a")) { //if the correct answer is a
                System.out.println("---------------------------------------------------------");
                Player.points++;
                pointsField.setText(Integer.toString(Player.points)); //updates the points textField
                System.out.println(getCurrentQuestion().getCorrectResponse()); //prints the games response to a correct answer
                System.out.println("You gained 1 point!");
            } else {
                System.out.println("---------------------------------------------------------");
                System.out.println(getCurrentQuestion().getIncorrectResponse()); //prints the games response to an incorrect answer
                System.out.println("You gained no points for the incorrect answer.");
            }
            getCurrentQuestion().setFinished(true); //sets the currently active question as a finished question
            questionPrompt(); //prints the next question
        } else { //in case the player hasn't started a quiz yet
            System.out.println("---------------------------------------------------------");
            System.out.println("You can't answer anything because you haven't started a quiz! ");
        }
    }

    //does the same as checkAnswerA, but with answer b instead
    @FXML
    public void checkAnswerB() {
        if (!game.currentRoom.getRoomQuiz().isDone && game.currentRoom.getRoomQuiz().started) {
            if (getCurrentQuestion().getAnswer().equals("b")) {
                System.out.println("---------------------------------------------------------");
                Player.points++;
                pointsField.setText(Integer.toString(Player.points));
                System.out.println(getCurrentQuestion().getCorrectResponse());
                System.out.println("You gained 1 point!");
            } else {
                System.out.println("---------------------------------------------------------");
                System.out.println(getCurrentQuestion().getIncorrectResponse());
                System.out.println("You gained no points for the incorrect answer.");
            }
            getCurrentQuestion().setFinished(true);
            questionPrompt();
        } else {
            System.out.println("---------------------------------------------------------");
            System.out.println("You can't answer anything because you haven't started a quiz!");
        }
    }

    //does the same as checkAnswerA, but with answer b instead
    @FXML
    public void checkAnswerC() {
        if (!game.currentRoom.getRoomQuiz().isDone && game.currentRoom.getRoomQuiz().started) {
            if (getCurrentQuestion().getAnswer().equals("c")) {
                System.out.println("---------------------------------------------------------");
                Player.points++;
                pointsField.setText(Integer.toString(Player.points));
                System.out.println(getCurrentQuestion().getCorrectResponse());
                System.out.println("You gained 1 point!");
            } else {
                System.out.println("---------------------------------------------------------");
                System.out.println(getCurrentQuestion().getIncorrectResponse());
                System.out.println("You gained no points for the incorrect answer.");
            }
            getCurrentQuestion().setFinished(true);
            questionPrompt();
        } else {
            System.out.println("---------------------------------------------------------");
            System.out.println("You can't answer anything because you haven't started a quiz!");
        }
    }

    //drops the key that is dropped after each completed quiz, takes the players current location into consideration
    public void dropKey() {
        if (game.currentRoom == game.garden) { //if the player is in garden
            Item officeKey = new Key(game.office, "Key(office)", ""); //creates a new item-object that unlocks the office
            game.currentRoom.addItem("Key(office)", officeKey); //adds item to the rooms HashMap of items
            roomItems.add("Key(office)"); //ads the item to the ListView
            itemsInRoom.setItems(roomItems); //updates the observable list so that the new item shows up
            System.out.println("Key(office) has been dropped don't forget to pick it up!");

        } else if (game.currentRoom == game.office) {
            Item livingRoomKey = new Key(game.livingRoom, "Key(livingroom)", "");
            game.currentRoom.addItem("Key(livingroom)", livingRoomKey);
            roomItems.add("Key(livingroom)");
            itemsInRoom.setItems(roomItems);
            System.out.println("Key(livingroom) has been dropped, don't forget to pick it up!");

        } else if (game.currentRoom == game.kitchen) {
            Item bedroomKey = new Key(game.bedroom, "Key(bedroom)", "");
            game.currentRoom.addItem("Key(bedroom)", bedroomKey);
            roomItems.add("Key(bedroom)");
            itemsInRoom.setItems(roomItems);
            System.out.println("Key(bedroom) has been dropped don't forget to pick it up!");

        } else if (game.currentRoom == game.bedroom) {
            Item supermarketKey = new Key(game.supermarket, "Key(supermarket)", "");
            game.currentRoom.addItem("Key(supermarket)", supermarketKey);
            roomItems.add("Key(supermarket)");
            itemsInRoom.setItems(roomItems);
            System.out.println("Key(supermarket) has been dropped don't forget to pick it up!");

        } else if (game.currentRoom == game.livingRoom) {
            Item kitchenKey = new Key(game.kitchen, "Key(kitchen)", "");
            game.currentRoom.addItem("Key(kitchen)", kitchenKey);
            roomItems.add("Key(kitchen)");
            itemsInRoom.setItems(roomItems);
            System.out.println("Key(kitchen) has been dropped don't forget to pick it up!");
        }
    }

    //updates the map so that it reflects the players current location
    public void mapUpdater() {
        if (game.currentRoom == game.garden) { //if the player is currently in garden
            map.setImage(gardenMap); //change the image that map shows to the image called gardenMap
        } else if (game.currentRoom == game.office) {
            map.setImage(officeMap);
        } else if (game.currentRoom == game.livingRoom) {
            map.setImage(livingroomMap);
        } else if (game.currentRoom == game.kitchen) {
            map.setImage(kitchenMap);
        } else if (game.currentRoom == game.bedroom) {
            map.setImage(bedroomMap);
        } else if (game.currentRoom == game.supermarket) {
            map.setImage(supermarketMap);
        }
    }
}
