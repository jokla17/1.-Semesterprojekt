/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trashworld;

import java.util.ArrayList;

/**
 *
 * @author svane
 */
public class Quiz {
    public Question[] questions;
    
    //checks if the quiz is completed or not
    public static boolean isDone = false;

    public Quiz(Question[] questions) {
        this.questions = questions;
    }

    
   
}
