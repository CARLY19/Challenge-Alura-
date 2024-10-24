package com.example.reto_convertidor_de_moneda;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Cargar el archivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // Configurar la escena con un tamaño fijo de 400x300 (puedes ajustar si es necesario)
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);

        // Configuración del título de la ventana
        stage.setTitle("Currency Converter");

        // Añadir la escena al escenario y mostrarlo
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Iniciar la aplicación JavaFX
        launch();
    }
}
