/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.guiclassses;


import com.mycompany.controlledeconomy.classes.Traider;
import javafx.scene.control.ListCell;

/**
 *
 * @author Windows 10
 */
public class TraderCell  extends ListCell<Traider>
{
    @Override
    public void updateItem(Traider item, boolean empty) 
    {
        super.updateItem(item, empty);
 
        //int index = this.getIndex();
        String name = null;
 
        // Format name
        if (item == null || empty) 
        {
            //this.setStyle("-fx-background-color: #172e3b");
        } 
        else
        {
            name = item.getName() + "\n" + "lol";
            //this.setStyle("-fx-background-color: #1e3745");
        }
         
        this.setText(name);
        
        setGraphic(null);
    }
}