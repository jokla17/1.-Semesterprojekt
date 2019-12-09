/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tworld;

/**
 *
 * @author svane
 */
public class Quiz {
    public Question[] questions;
    
    //checks if the quiz is completed or not
    public boolean isDone = false;
    
    public boolean started = false;

    public Quiz(Question[] questions) {
        this.questions = questions;
    }

    
   
}
