package com.example.listycity;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    String cityToBeDeleted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        Button addBtn = findViewById(R.id.addBtn);
        Button delBtn = findViewById(R.id.delBtn);
        Button confirmBtn = findViewById(R.id.confirmBtn);
        EditText textInput = findViewById(R.id.textInput);

        /*
        setOnclickListener boiler plate code is taken from a video from Zeeshawn academy.
        Taken by: Hanss Rivera
        Taken on: January 16 2025
        Refer to README.md for more in depth information about the citation
        */
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                The following conditions are inspiration from a stackoverflow post.
                Taken by: Hanss Rivera
                Taken on: January 16 2025
                Refer to README.md for more in depth information about the citation
                */
                if (textInput.getVisibility() == VISIBLE){
                    textInput.setVisibility(INVISIBLE);
                    confirmBtn.setVisibility(INVISIBLE);
                }
                else{
                    textInput.setVisibility(VISIBLE);
                    confirmBtn.setVisibility(VISIBLE);
                }
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cityToBeDeleted != null){
                    dataList.remove(cityToBeDeleted);
                    /*
                    Taken from chatgpt LLM. Helped me discover notifyDataSetChanged() method
                    Taken by: Hanss Rivera
                    Taken on: January 16 2025
                    Refer to README.md for more in depth information about the citation
                    */
                    cityAdapter.notifyDataSetChanged();
                }
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addedCity = textInput.getText().toString(); // studytonight.com code, Taken by: Hanss Rivera, Taken on: January 16 2025, refer to README.md for more information about the citation
                dataList.add(addedCity);
                cityAdapter.notifyDataSetChanged();
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityToBeDeleted = dataList.get(position);
            }
        });

    }
}