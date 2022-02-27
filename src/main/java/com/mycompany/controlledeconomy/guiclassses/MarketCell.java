/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.guiclassses;

/**
 *
 * @author Windows 10
 */

import com.mycompany.controlledeconomy.classes.CommodityMarket;
import com.mycompany.controlledeconomy.classes.Market;
import javafx.scene.control.ListCell;
 
public class MarketCell  extends ListCell<Market>
{
    @Override
    public void updateItem(Market item, boolean empty) 
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
            name = item.getID() + "\n" +
            item.getName();
            //this.setStyle("-fx-background-color: #1e3745");
        }
         
        this.setText(name);
        
        setGraphic(null);
    }
}
