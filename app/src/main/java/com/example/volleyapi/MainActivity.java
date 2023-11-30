package com.example.volleyapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String API_KEY = "e11cf1f4deea966736d6f00d46764dc0";
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.weather_txt);

        //Instantiate the Request Queue
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.openweathermap.org/data/2.5/weather?q=London,uk&units=metric&appid=e11cf1f4deea966736d6f00d46764dc0";

        //Request a string response from url
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main = response.getJSONObject("main");
                    String temp = String.valueOf(main.getDouble("temp"));
                    txtView.setText("Temp: " + temp + "C");
                    Toast.makeText(MainActivity.this, main.toString(), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
}