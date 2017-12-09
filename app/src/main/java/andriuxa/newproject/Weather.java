package andriuxa.newproject;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import andriuxa.newproject.WeatherActivity;

import static andriuxa.newproject.WeatherActivity.status;

/**
 * Created by Dell on 08/12/2017.
 */

public class Weather extends AsyncTask<String,Void,String> {

    String result;

    @Override
    protected String doInBackground(String... urls) {
        result = "";
        URL link;
        HttpURLConnection myconnection = null;

        try {
            link = new URL(urls[0]);
            myconnection = (HttpURLConnection)link.openConnection();
            InputStream in = myconnection.getInputStream();
            InputStreamReader myStreamReader = new InputStreamReader(in);
            int data = myStreamReader.read();
            while(data!= -1){
                char current = (char)data;
                result+= current;
                data = myStreamReader.read();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject myObject = new JSONObject(result);
            JSONObject main = new JSONObject(myObject.getString("main"));
            double tempDoub = Double.parseDouble(main.getString("temp"));
            int tempInt=(int)(tempDoub-273);
            String placeName = myObject.getString("name");
            WeatherActivity.place.setText(placeName);
            WeatherActivity.temp.setText(""+tempInt+" \u2103");
            if (tempInt >= 10 && tempInt < 35){
                status.setBackgroundResource(R.drawable.hot);
            }
            if (tempInt < 10 && tempInt > -5){
                status.setBackgroundResource(R.drawable.freezing);
            }
            else status.setBackgroundResource(R.drawable.death);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}