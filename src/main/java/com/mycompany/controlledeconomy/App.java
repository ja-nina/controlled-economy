/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy;

/**
 *
 * @author Windows 10
 */


import com.mycompany.controlledeconomy.classes.Historian;
import com.mycompany.controlledeconomy.classes.User;
import com.mycompany.controlledeconomy.classes.World;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public User user;
    public World worldInfluence;
    public Historian historian;
    public Thread historicThread;
    public Thread worldThread;

    @Override
    public void start(Stage stage) throws IOException {
        user = User.getInstance();
        historian = Historian.summonHistorian();
        historicThread = new Thread(historian);
        historicThread.start();
        
        worldInfluence = new World();
        worldThread = new Thread(worldInfluence);
        worldThread.start();
        
        System.out.println("historian should have started");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        PrimaryController controller = new PrimaryController(user);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add("liststyling.css");
        stage.setScene(scene);
        stage.setTitle("DEMARKETIZATION");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        controller.setStage(stage);
        
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}