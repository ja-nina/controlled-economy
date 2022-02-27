package com.mycompany.controlledeconomy;


import com.mycompany.controlledeconomy.classes.Asset;
import com.mycompany.controlledeconomy.classes.CommodityMarket;
import com.mycompany.controlledeconomy.classes.CurrencyMarket;
import com.mycompany.controlledeconomy.classes.FactoryAsset;
import com.mycompany.controlledeconomy.classes.FactoryMarket;
import com.mycompany.controlledeconomy.classes.Market;
import com.mycompany.controlledeconomy.classes.StockMarket;
import com.mycompany.controlledeconomy.classes.Traider;
import com.mycompany.controlledeconomy.classes.User;
import com.mycompany.controlledeconomy.guiclassses.WalletCellFactory;
import com.mycompany.controlledeconomy.guiclassses.TraderCellFactory;
import javafx.scene.input.MouseButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

public class InvestorsController implements Initializable{
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    public User user;
    private Traider currentTrader;
    private Set<Pair<Asset,Integer>> pom;
    
    
    @FXML
    private Button homeButton;
    
    @FXML
    private Button tradersButton;
    
    @FXML
    private Button buttonUpdateAllTraders;
    
    @FXML
    private Button buttonUpdateCurrentTrader;
    
    @FXML
    private ListView<Traider> traderListView;
    
    @FXML
    private ListView<Pair<Asset, Integer>> listViewWallet;
    
    @FXML
    private Label description;
    
    @FXML
    private Label selectedTrader;
    
    
    public InvestorsController(){
        System.out.println("BRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
        System.out.println("HI!");
}
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = User.getInstance();
        listViewWallet.getItems().addAll();
        listViewWallet.setCellFactory(new WalletCellFactory());
        listViewWallet.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            Pair<Asset,Integer> currentAsset = listViewWallet.getSelectionModel().getSelectedItem();
            System.out.println("clicked on "+ currentAsset.getKey().getName());
            
        }
        });
        traderListView.getItems().addAll(User.getInstance().listOfTraders);
        System.out.println("NUMBER OF TRADERS IN LIST OF USER: " + Integer.toString(user.listOfTraders.size()));
        traderListView.setCellFactory(new TraderCellFactory());
        traderListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            currentTrader = traderListView.getSelectionModel().getSelectedItem();
            String TraderName = currentTrader.getName();
            System.out.println("clicked on " + TraderName);
            
            //if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
            //try {
                //switchToSceneMarket(event, market);
                //set description and list 
                //System.out.println("CLICKED TWO TIMES BASTARD,WHY THO?");
            //} catch (IOException ex) {
            //    ex.printStackTrace();
            //}
            //}else{
                selectedTrader.setText(TraderName);
                setWalletList(currentTrader);
                System.out.println("WALLET IS SET");
            //}
        }
        });
        

    }
    
    
    
    public void switchToHome(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        PrimaryController controller = new PrimaryController(user);
        loader.setController(controller);
        Parent root2 = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene2 = new Scene(root2);
        scene2.getStylesheets().add("liststyling.css");
        stage.setScene(scene2);
        stage.show();
        
    }
    private void setWalletList(Traider currentTrader){
        listViewWallet.getItems().clear();
        System.out.println("CURRENT WALLET: " + Integer.toString(currentTrader.getWallet().size()) + " of currentTrader :" + currentTrader.getName());
        //
        for(Map.Entry<Asset, Integer> entry : currentTrader.getWallet().entrySet()) {
        listViewWallet.getItems().add(new Pair<Asset, Integer>(entry.getKey(), entry.getValue()));
        }
        //System.out.println("NUMBER OF ELEMENTS IN Wallet: " + Integer.toString(user.listOfMarkets.size()));
        
    }
    
    
    

    
}
