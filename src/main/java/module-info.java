module com.mycompany.controlledeconomy {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens com.mycompany.controlledeconomy to javafx.fxml;
    exports com.mycompany.controlledeconomy;
}
