package sample.weather;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text temp_info;

    @FXML
    private TextField city;

    @FXML
    private Text feels_like;

    @FXML
    private Button getData;

    @FXML
    private Text pressure;

    @FXML
    private Text temp;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    protected void onHelloButtonClick() {
        String getUserCity = city.getText().trim();
        String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity+ "&appid=2106b450ca58b756165c5c3f2e0deb62&units=metric");
        System.out.println(output);
        // Обработка полученных данных и вывод на интерфейс
        if(!output.isEmpty()){
            JSONObject obj = new JSONObject(output);
            temp_info.setText("Температура: " + obj.getJSONObject("main").getDouble("temp"));
            feels_like.setText("Ощущается как: " + obj.getJSONObject("main").getDouble("feels_like"));
            temp_max.setText("Максимум: " + obj.getJSONObject("main").getDouble("temp_max"));
            temp_min.setText("Минимум: " + obj.getJSONObject("main").getDouble("temp_min"));
            pressure.setText("Давление: " + obj.getJSONObject("main").getDouble("pressure"));
        }
    }

    private static String getUrlContent(String urlAddress) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(urlAddress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();

        } catch (Exception e) {
            System.out.println("Такой город не найден!");
            e.printStackTrace();
            // Возможно, следует бросить исключение или вернуть какой-то флаг для обработки ошибки
        }
        return content.toString();
    }
}
