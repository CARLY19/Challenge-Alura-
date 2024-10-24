module com.example.reto_convertidor_de_moneda {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls; // Esto es para validatorfx y otras características de ControlsFX
    requires org.json;

    opens com.example.reto_convertidor_de_moneda to javafx.fxml;
    exports com.example.reto_convertidor_de_moneda;
}
