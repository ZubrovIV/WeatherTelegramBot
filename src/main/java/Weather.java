

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;


public class Weather {

    //f3520fcfe62ea41896d2a1250c8069aa
    public static String getWeather (String message,Model model) throws IOException {

        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+message+"&units=metric&appid=f3520fcfe62ea41896d2a1250c8069aa");

        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";
        while (scanner.hasNext()){
            result += scanner.nextLine();
        }


    JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

    JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

    JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
        JSONObject obj = getArray.getJSONObject(i);
        model.setMain((String) obj.get("main"));
    }

        return "City: " + model.getName() + "\n" +
                "Temperature: " + model.getTemp() + "C" + "\n" +
                "Humidity:" + model.getHumidity() + "%" + "\n" +
                "Main: " + model.getMain() + "\n";
}
}
