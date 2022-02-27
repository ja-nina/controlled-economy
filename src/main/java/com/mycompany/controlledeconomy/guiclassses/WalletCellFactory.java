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
import javafx.util.Pair;
 
public class WalletCellFactory implements Callback<ListView<Pair<Asset, Integer>>, ListCell<Pair<Asset, Integer>>>
{
    @Override
    public ListCell<Pair<Asset, Integer>> call(ListView<Pair<Asset, Integer>> listview) 
    {
        return new WalletCell();
    }
}