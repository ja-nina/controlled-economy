package com.mycompany.controlledeconomy;

import com.mycompany.controlledeconomy.classes.Historian;
import com.mycompany.controlledeconomy.classes.Asset;
import com.mycompany.controlledeconomy.classes.User;
import com.mycompany.controlledeconomy.guiclassses.AssetCellFactory;
import java.io.IOException;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.net.URL;
import java.security.Provider.Service;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChartController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    public User user;
    
    private ScheduledExecutorService scheduledExecutorService;
    private LineChartUpdater updater;
    public volatile Set<Asset> displayedAssets = new HashSet<Asset>();
    int numberPointsDisplayed = 150;
    
    @FXML
    Button homeButton;
    
    @FXML
    Button addButton;
    
    @FXML
    ListView<Asset> assetList;
    
    @FXML
    volatile LineChart<Long, Double> lineChart;

    @FXML
    NumberAxis x;

    @FXML
    NumberAxis y;
    
    @FXML
    Label assetText;
    
    Asset currentAsset;
    
    Thread lineChartUpdateThread;
    
    
    public ChartController(){
        
        //this.user = user;
        //myMarket = market;
        //marketName.setText(myMarket.getID());
        updater = new LineChartUpdater();
        displayedAssets = new HashSet<Asset>();
        lineChartUpdateThread = new Thread(updater);
        
        
    }

    //need for some communication btw controllers
    public void setAsset(Asset asset){
        currentAsset = asset;
        assetText.setText("Name :\t" + asset.getName());
    }
    
//    public void setUser(User user){
//        this.user = user;
//    }

    

    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        //----------LINE CHAR
        x.setAutoRanging(false);
        x.setTickMarkVisible(false);
        y.setTickMarkVisible(false);
        currentAsset = Historian.summonHistorian().getCurrentAsset();
        currentAsset.displayed.setName(currentAsset.getName());
        //System.out.println(currentAsset.history);
        addButton.setDisable(true);
        try{
        User.getInstance().addAssetToDisplayed(currentAsset.getName());
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
        //lineChart.getData().add(currentAsset.history);
            int howManyIndexesThereAre = currentAsset.getHistorySize();
            int lastIndex = howManyIndexesThereAre - 5;
            int startingIndex = lastIndex - numberPointsDisplayed;
            int currentIndex = max(8, startingIndex);
            System.out.println("last index: " + lastIndex + " starting: " + startingIndex + " how many indices there are: " + howManyIndexesThereAre );
            while(currentIndex < max(lastIndex, 0)){
                currentAsset.displayed.getData().add(new XYChart.Data<>( currentAsset.history.getData().get(currentIndex).getXValue(),
                                                    currentAsset.history.getData().get(currentIndex).getYValue()));
                System.out.println("XVALUE: " + currentAsset.history.getData().get(currentIndex).getXValue());
             currentIndex++;
            }
            x.setLowerBound((long)System.currentTimeMillis() - 300*numberPointsDisplayed);
            x.setUpperBound((long)System.currentTimeMillis() - 300*9);
              
            
            lineChartUpdateThread.start();
            updater.increaseNumberDisplayed(currentAsset);
            addButton.setDisable(true);
        
        lineChart.setCreateSymbols(false);
        //--------------------
        user = User.getInstance();
        assetList.getItems().addAll(User.getInstance().listOfAssets);
        assetList.setCellFactory(new AssetCellFactory());
        assetList.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            System.out.println("setting asset!!!!!");
            setAsset(assetList.getSelectionModel().getSelectedItem());
            System.out.println("clicked on "+ currentAsset.getName()+ "number of displayed: " + displayedAssets.size());
            if(updater.isDisplayed(currentAsset)){
            addButton.setDisable(true);
            }else{
                addButton.setDisable(false);
            }
        }
        });

    }
    
    @FXML
    public void switchHome(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        System.out.println("USER:" + user);
        PrimaryController controller = new PrimaryController(User.getInstance());
        loader.setController(controller);
        Parent root2 = loader.load();
        updater.stop = true;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene2 = new Scene(root2);
        scene2.getStylesheets().add("liststyling.css");
        stage.setScene(scene2);
        stage.show();
        
    }
    
    @FXML
    public void addLine(ActionEvent event) throws IOException, InterruptedException{
        if(!updater.isDisplayed(currentAsset)){
            // how much it is safe to take - i want to have a 2 second dalay soo... 8 values before the last one
            int howManyIndexesThereAre = currentAsset.getHistorySize();
            int lastIndex = howManyIndexesThereAre - 8;
            int startingIndex = lastIndex - 8 - numberPointsDisplayed;
            
            int currentIndex = max(8, startingIndex);
            System.out.println(lastIndex + " starting: " + startingIndex + " " + howManyIndexesThereAre );
            while(currentIndex < min(max(lastIndex, 0), max(howManyIndexesThereAre - 8, 0))){
              
                currentAsset.displayed.getData().add(new XYChart.Data<>( currentAsset.history.getData().get(currentIndex).getXValue(),
                                                    currentAsset.history.getData().get(currentIndex).getYValue()));
                 System.out.println("XVALUE: " + currentAsset.history.getData().get(currentIndex).getXValue());
                currentIndex++;
            }
            currentAsset.displayed.setName(currentAsset.getName());
            
            updater.increaseNumberDisplayed(currentAsset);
            
            addButton.setDisable(true);
        }
        else{
            addButton.setDisable(false);
        }
        
    }
    
    
        
    
    
    class LineChartUpdater implements Runnable{
        public volatile Boolean stop = false;
        long start; long end;
        int numberOfDisplayed = 0;
        @Override
        public void run() {
            while(!stop){
                start = System.currentTimeMillis();
                Platform.runLater(() -> {

                    iterateAndAddPoints();
                });
                end = System.currentTimeMillis();
                try {
                    Thread.sleep(max(300 - (int) (end - start),0));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("KILLED !!!");
            
        
        }
        public synchronized void increaseNumberDisplayed(Asset asset){
            System.out.println("UPDATING");
            numberOfDisplayed +=1;
            displayedAssets.add(asset);
            lineChart.getData().add(asset.displayed);
        }
        
        
        public synchronized Boolean isDisplayed(Asset asset){
            return displayedAssets.contains(asset);
        }
        
        
        public synchronized void iterateAndAddPoints(){
            for(Asset asset: displayedAssets){
                int howManyIndexesThereAre = asset.getHistorySize();
                int lastIndex = max(howManyIndexesThereAre - 5, 0);
                int currentIndex = lastIndex;
                asset.displayed.getData().add(new XYChart.Data<>( asset.history.getData().get(currentIndex).getXValue(),
                                                    asset.history.getData().get(currentIndex).getYValue()));
              
                while(asset.displayed.getData().size() > numberPointsDisplayed*2){
                   asset.displayed.getData().remove(0);
                }
                
            }
            x.setLowerBound((long)System.currentTimeMillis() - 300*numberPointsDisplayed);
            x.setUpperBound((long)System.currentTimeMillis() - 300*9);
        }
    }
    }


