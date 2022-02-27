package com.mycompany.controlledeconomy;


import com.mycompany.controlledeconomy.classes.Asset;
import com.mycompany.controlledeconomy.classes.CommodityMarket;
import com.mycompany.controlledeconomy.classes.CurrencyMarket;
import com.mycompany.controlledeconomy.classes.FactoryAsset;
import com.mycompany.controlledeconomy.classes.FactoryMarket;
import com.mycompany.controlledeconomy.classes.Historian;
import com.mycompany.controlledeconomy.classes.Market;
import com.mycompany.controlledeconomy.classes.StockMarket;
import com.mycompany.controlledeconomy.classes.User;
import com.mycompany.controlledeconomy.guiclassses.AssetCellFactory;
import com.mycompany.controlledeconomy.guiclassses.MarketCellFactory;
import com.mycompany.controlledeconomy.guiclassses.MarketSetupFactory;
import javafx.scene.input.MouseButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrimaryController implements Initializable{
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    public User user;
    static FactoryMarket<Market> myMarketFactory;
    static FactoryAsset<Asset> myAssetFactory;
    private String currentMarket;
    private String currentAsset;
    private Market market;
    private Asset asset;
    
    @FXML
    private Button assetButton;
    
    @FXML
    private Button investorButton;
    
    @FXML
    private Button CurrencyMarketButton;
    
    @FXML
    private Button CommodityMarketButton;
    
    @FXML
    private Button StockMarketButton;
    
    @FXML
    private Label counterLabel;
    
    @FXML
    private Label selectedMarket;
    
    @FXML
    private ListView<Market> listView;
    
    @FXML
    public ListView<Asset> assetListView;
    
    @FXML
    private Slider sliderBBratio;
    
    @FXML
    private Slider sliderTPM;
    
    /// FOR THE LINE CHARTS
    
    @FXML 
    private CategoryAxis xAxis;
    
    @FXML
    private NumberAxis yAxis;
    
    @FXML
    private LineChart lineChart;
    
    
    public PrimaryController(User user){
        this.user = user;
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
         
    
    @FXML
    private void commodityButtonClicked() throws IOException{
        CommodityMarket market = (CommodityMarket) myMarketFactory.createMarket(2);
        listView.getItems().add(market);
        user.listOfMarkets.add(market);
        user.createCommodityMarket();
    }
    
    @FXML
    private void stockButtonClicked() throws IOException{
        StockMarket market = (StockMarket) myMarketFactory.createMarket(3);
        listView.getItems().add(market);
        user.listOfMarkets.add(market);
        user.createStockMarket();
    }
    
    @FXML
    private void currencyButtonClicked() throws IOException{
        CurrencyMarket market = (CurrencyMarket) myMarketFactory.createMarket(1);
        listView.getItems().add(market);
        user.listOfMarkets.add(market);
        user.createCurrencyMarket();
    }
    
    @FXML
    private void createAsset() throws IOException, InterruptedException{
        System.out.println("CREATE ASSET");
        if(market != null){
            Asset asset = FactoryAsset.createAsset(market);
            assetListView.getItems().add(asset);
            user.addAsset(asset);
            

        }
    }
    
    @FXML
    private void switchToInvestors() throws IOException{
        System.out.println("SWITCHING TO INVESTORS");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trader.fxml"));     
        Parent root2 = (Parent)fxmlLoader.load();          
        InvestorsController controller = fxmlLoader.<InvestorsController>getController();
        //controller.setUser(user);
        System.out.println("LOL");
        Scene scene2 = new Scene(root2);
        scene2.getStylesheets().add("liststyling.css");
        stage.setScene(scene2);
        stage.show();
    }
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        myMarketFactory = new FactoryMarket<Market>(user.getCommodityMarketCount(),
                                                    user.getCurrencyMarketCount(),
                                                    user.getStockMarketCount());
        listView.getItems().addAll(user.listOfMarkets);
        System.out.println("NUMBER OF ELEMENTS IN LIST OF USER: " + Integer.toString(user.listOfMarkets.size()));
        listView.setCellFactory(new MarketCellFactory());
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            market = listView.getSelectionModel().getSelectedItem();
            currentMarket = market.getName();
            System.out.println("clicked on " + currentMarket);
            
            if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
            try {
                switchToSceneMarket(event, market);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            }else{
                assetButton.setText(MarketSetupFactory.getAssetButtonName(market));
            }
        }
        });
        
        //----------------------------------------------------------------------

        assetListView.getItems().addAll(user.listOfAssets);
        System.out.println("NUMBER OF ELEMENTS IN ASSET LIST OF USER: " + Integer.toString(user.listOfAssets.size()));
        assetListView.setCellFactory(new AssetCellFactory());
        assetListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            asset = assetListView.getSelectionModel().getSelectedItem();
            currentAsset = asset.getName();
            Historian.summonHistorian().setCurrentAsset(asset);
            System.out.println("clicked on " + currentAsset);
           
            if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                try{
                System.out.println("DOUBLE CLICKED " + currentAsset);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chart.fxml"));
                System.out.println("in primary execuot 0");
                Parent root2 = (Parent)fxmlLoader.load();          
                //ChartController controller = fxmlLoader.<ChartController>getController();
                System.out.println("stop 1");
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                //ChartController.start(stage);
                Scene scene2 = new Scene(root2);
                scene2.getStylesheets().add("liststyling.css");
                stage.setScene(scene2);
                System.out.println("stop 2");
                stage.show();
                } catch (IOException ex) {
                ex.printStackTrace();
            }
            }else{
                System.out.println("CLICKED " + currentAsset);
            
                //assetButton.setText(MarketSetupFactory.getAssetButtonName(market));
            }
        }
        });
        
        //--------------------------------------------------------------
        //SLIDER
        sliderBBratio.setValue(user.BBratio.getValue());
        
        sliderBBratio.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                user.BBratio.setValue((int)sliderBBratio.getValue());
            }
        
        });
        sliderTPM.setValue(user.TPM.getValue());
        sliderTPM.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                user.TPM.setValue((int)sliderTPM.getValue());
            }
        
        });
    }
    
    
    public void switchToSceneMarket(MouseEvent event, Market market) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("market.fxml"));     
        Parent root2 = (Parent)fxmlLoader.load();          
        SecondaryController controller = fxmlLoader.<SecondaryController>getController();
        //controller.setUser(User.getInstance());
        controller.setMarket(market);
        String lol = MarketSetupFactory.getAssetButtonName(market);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene2 = new Scene(root2);
        scene2.getStylesheets().add("liststyling.css");
        stage.setScene(scene2);
        stage.show();
        
    }
    
    @FXML
    public void switchToTraders() throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trader.fxml"));     
        System.out.println("LOL0");
        Parent root2 = (Parent)fxmlLoader.load();   
        System.out.println("LOL1");
        InvestorsController controller = fxmlLoader.<InvestorsController>getController();
        System.out.println("LOL2");
        //controller.setUser(user);
        stage = (Stage)(Stage) assetButton.getScene().getWindow();
        Scene scene2 = new Scene(root2);
        scene2.getStylesheets().add("liststyling.css");
        stage.setScene(scene2);
        stage.show();
        
    }
    
    public void drawchart(Asset Asset){
        lineChart = new LineChart(xAxis, yAxis);
        
        XYChart.Series dataSeries = new XYChart.Series();
        
    }
    
    
}
