
package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.json.JSONObject;

import javax.swing.*;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

//    @FXML
//    private Text wind;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private Text pressure;

    @FXML
    private Text temp_feels;

    @FXML
    private Text humidity;


    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if (!getUserCity.equals("")) {
                String output =  getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=70e8378e2c0529045a4fdb55ffa99c7c&units=metric");

                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    temp_info.setText("TEMPERATURE: = " + obj.getJSONObject("main").getDouble("temp"));

                    temp_feels.setText("FEELS: = " + obj.getJSONObject("main").getDouble("feels_like"));

                    temp_max.setText("MAXIMUM:  = " + obj.getJSONObject("main").getDouble("temp_max"));

                    temp_min.setText("MINIMUM: = " + obj.getJSONObject("main").getDouble("temp_min"));

                    pressure.setText("PRESSURE: = " + obj.getJSONObject("main").getDouble("pressure"));

                    humidity.setText("HUMIDITY: = " + obj.getJSONObject("main").getDouble("humidity") + " %");

//                    wind.setText("WIND: = " + obj.getJSONObject("wind").getDouble("speed") + " m/s");
                }
            }


        });
    }

    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try{
            URL url = new URL (urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "It isn't city!");
//            System.out.println("It isn't city!");
        }
        return content.toString();
    }


}


