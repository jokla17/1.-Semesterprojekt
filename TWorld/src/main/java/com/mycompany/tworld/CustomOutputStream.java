/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tworld;

import java.io.IOException;
import java.io.OutputStream;
import javafx.scene.control.TextArea;

/**
 *
 * @author svane
 */
public class CustomOutputStream extends OutputStream{
    private TextArea textArea;
    
    public CustomOutputStream(TextArea textArea){
        this.textArea = textArea;
    }
    
    @Override 
    public void write(int b) throws IOException {
            textArea.appendText(String.valueOf((char) b));
    }
    
}
