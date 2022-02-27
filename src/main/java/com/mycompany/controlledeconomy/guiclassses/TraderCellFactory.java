/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.guiclassses;

import com.mycompany.controlledeconomy.classes.Market;
import com.mycompany.controlledeconomy.classes.Traider;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author Windows 10
 */
public class TraderCellFactory implements Callback<ListView<Traider>, ListCell<Traider>>
{
    @Override
    public ListCell<Traider> call(ListView<Traider> listview) 
    {
        return new TraderCell();
    }
}