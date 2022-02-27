package com.mycompany.controlledeconomy;

import com.mycompany.controlledeconomy.classes.CommodityMarket;
import com.mycompany.controlledeconomy.classes.Market;
import com.mycompany.controlledeconomy.classes.User;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SecondaryController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    public User user;
    public Label marketName;
    
    
    @FXML
    Button assetButton;
    
    @FXML
    Button investorButton;
    
    @FXML
    Button homeButton;
    
    Market myMarket;
    
    
    public SecondaryController(){
        //this.user = user;
        //myMarket = market;
        //marketName.setText(myMarket.getID());
    }

    //need for some communication btw controllers
    public void setMarket(Market market){
        myMarket = market;
        marketName.setText(myMarket.getID());
    }
    
//    public void setUser(User user){
//        this.user = user;
//    }

    
    public void switchToHome(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        System.out.println("USER:" + user);
        PrimaryController controller = new PrimaryController(User.getInstance());
        loader.setController(controller);
        Parent root2 = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene2 = new Scene(root2);
        scene2.getStylesheets().add("liststyling.css");
        stage.setScene(scene2);
        stage.show();
        
    }
    
}