package com.example.onpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class colleges extends AppCompatActivity {

    Spinner spinnerColleges;
    ArrayList<String> collegesList = new ArrayList<>();
    ArrayAdapter<String> collegesAdapter;
    RequestQueue requestQueue;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colleges);
        requestQueue = Volley.newRequestQueue(this);
        spinnerColleges = findViewById(R.id.spinnercolleges);
        String url = "http://10.0.2.2:8889/loginregister/colleges.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("colleges");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.optString("name");
                        collegesList.add(name);
                        collegesAdapter = new ArrayAdapter<>(colleges.this, android.R.layout.simple_list_item_1,collegesList);
                        collegesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerColleges.setAdapter(collegesAdapter);

                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
            requestQueue.add(jsonObjectRequest);
    }
}