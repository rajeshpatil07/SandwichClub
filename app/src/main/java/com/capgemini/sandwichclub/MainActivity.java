package com.capgemini.sandwichclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CustomAdapter.AdapterCallback {

    ArrayList<String> mSandwichNames = new ArrayList<>();
    ArrayList<String> mDescription = new ArrayList<>();
    ArrayList<String> mFileName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {

            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONArray userArray = obj.getJSONArray("users");

            for (int i = 0; i < userArray.length(); i++) {

                JSONObject userDetail = userArray.getJSONObject(i);

                mSandwichNames.add(userDetail.getString("name"));
                mDescription.add(userDetail.getString("description"));
                mFileName.add(userDetail.getString("filepath"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, mSandwichNames, mDescription, mFileName);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("sandwich_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @Override
    public void onMethodCallback(String name,String description, String imageName) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("description", description);
        bundle.putString("imagename", imageName);
        intent.putExtras(bundle);
        MainActivity.this.startActivity(intent);
    }
}