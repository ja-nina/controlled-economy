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

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
 
public class AssetCellFactory implements Callback<ListView<Asset>, ListCell<Asset>>
{
    @Override
    public ListCell<Asset> call(ListView<Asset> listview) 
    {
        return new AssetCell();
    }
}