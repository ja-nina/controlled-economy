/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.guiclassses;

/**
 *
 * @author Windows 10
 */

import com.mycompany.controlledeconomy.classes.Asset;
import com.mycompany.controlledeconomy.classes.CommodityMarket;
import com.mycompany.controlledeconomy.classes.Market;
import javafx.scene.control.ListCell;
 
public class AssetCell  extends ListCell<Asset>
{
    @Override
    public void updateItem(Asset item, boolean empty) 
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
            name = item.getName() + "\n" + "   ";
            //this.setStyle("-fx-background-color: #1e3745");
        }
         
        this.setText(name);
        
        setGraphic(null);
    }
}
