package com.example.reto_convertidor_de_moneda;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class HelloController {

    @FXML
    private TextField fromCurrencyField;  // Campo para ingresar la moneda de origen

    @FXML
    private TextField toCurrencyField;    // Campo para ingresar la moneda de destino

    @FXML
    private Label resultLabel;            // Etiqueta para mostrar el resultado

    // Método para validar si la moneda es válida
    private boolean isValidCurrency(String currencyCode) {
        // Lista básica de monedas válidas (puedes hacerla más extensa según tus necesidades)
        List<String> validCurrencies = Arrays.asList(
                "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM",
                "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", "BTN",
                "BWP", "BYN", "BZD", "CAD", "CDF", "CHF", "CLP", "CNY", "COP", "CRC", "CUP",
                "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD",
                "FKP", "FOK", "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD",
                "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR",
                "ISK", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KID", "KMF", "KRW",
                "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL",
                "MGA", "MKD", "MMK", "MNT", "MOP", "MRU", "MUR", "MVR", "MWK", "MXN", "MYR",
                "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK",
                "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD",
                "SCR", "SDG", "SEK", "SGD", "SHP", "SLE", "SLL", "SOS", "SRD", "SSP", "STN",
                "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TVD", "TWD",
                "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VES", "VND", "VUV", "WST", "XAF",
                "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMW", "ZWL"
        );
        return validCurrencies.contains(currencyCode);
    }

    @FXML
    protected void convertCurrency() {
        try {
            // Obtener valores de los campos de texto
            String fromCurrency = fromCurrencyField.getText().toUpperCase();
            String toCurrency = toCurrencyField.getText().toUpperCase();

            // Validar las monedas ingresadas
            if (!isValidCurrency(fromCurrency)) {
                resultLabel.setText("Invalid 'From' currency.");
                return;
            }
            if (!isValidCurrency(toCurrency)) {
                resultLabel.setText("Invalid 'To' currency.");
                return;
            }

            // Obtener los datos de la API de tipos de cambio
            String apiData = getApiData();

            // Procesar el JSON recibido
            JSONObject jsonObject = new JSONObject(apiData);
            JSONObject rates = jsonObject.getJSONObject("rates");

            // Verificar si las monedas están disponibles en los datos de la API
            if (rates.has(fromCurrency) && rates.has(toCurrency)) {
                double fromRate = rates.getDouble(fromCurrency);  // Obtener el valor de la moneda de origen
                double toRate = rates.getDouble(toCurrency);      // Obtener el valor de la moneda de destino
                double conversionRate = toRate / fromRate;        // Calcular la tasa de conversión

                resultLabel.setText("Conversion Rate: " + conversionRate);  // Mostrar el resultado en la etiqueta
            } else {
                resultLabel.setText("Currency not found.");  // Mostrar mensaje de error si no se encuentran las monedas
            }
        } catch (Exception e) {
            resultLabel.setText("Error during conversion.");  // Mostrar mensaje de error si ocurre una excepción
        }
    }

    // Método para obtener los datos de la API de tipos de cambio
    private String getApiData() throws Exception {
        String apiUrl = "https://api.exchangerate-api.com/v4/latest/USD"; // Cambia si usas otra API
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Leer la respuesta de la API
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        // Cerrar los streams y la conexión
        in.close();
        connection.disconnect();

        // Imprimir la respuesta de la API para depuración
        System.out.println("API Response: " + content.toString());

        // Devolver los datos de la API como un String
        return content.toString();
    }
}

