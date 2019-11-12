/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trashworld;

/**
 *
 * @author svane
 */
public class Question {
    private String prompt;
    private String answer;
    private String correctResponse;
    private String incorrectResponse;
    public static String playerInput;

    //constructor for creating questions
    //questions consist of:
    // a prompt that prints the question and its answers to the user,
    //a correct answer
    //a response that is printed if the user answers correctly
    //a response that is printed if the user answers incorrectly
    public Question(String prompt, String answer, String correctResponse, String incorrectResponse) {
        this.prompt = prompt;
        this.answer = answer;
        this.correctResponse = correctResponse;
        this.incorrectResponse = incorrectResponse;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCorrectResponse() {
        return correctResponse;
    }

    public String getIncorrectResponse() {
        return incorrectResponse;
    }

    public String getPlayerInput() {
        return playerInput;
    }

    public void setPlayerInput(String playerInput) {
        this.playerInput = playerInput;
    }
    
    public void checkAnswer(){
        if (playerInput == answer){
            System.out.println(correctResponse);
            System.out.println("You received 1 point");
            Player.points ++;
        }else{
            System.out.println(incorrectResponse);
            System.out.println("You answered incorrectly");
        }
    }
   
}
