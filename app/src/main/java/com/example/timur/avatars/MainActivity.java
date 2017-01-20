package com.example.timur.avatars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private static int imagesCount = 100;

    private static int[] createRandomNumber() {
        int[] randomNumbers = new int[imagesCount];
        Random rand = new Random();
        for (int i = 0; i < imagesCount; i++) {
            int mRandom = rand.nextInt(10000);
            randomNumbers[i] = mRandom;
        }
        return randomNumbers;
    }


    private static String buildUrl(int randomNumb){
        final  String AVATAR_BASE_URL = "https://api.adorable.io/avatars/300/";
        return AVATAR_BASE_URL + Integer.toString(randomNumb);
    }

    private static String[] createURLs(int[] numbers) {
        String[] randomURLs = new String[imagesCount];
        for (int i = 0; i < imagesCount; i++) {
            randomURLs[i] = buildUrl(numbers[i]);
        }
        return randomURLs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList androidVersions = prepareData();
        DataAdapter adapter = new DataAdapter(getApplicationContext(),androidVersions);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList prepareData(){
        String[] android_image_urls = createURLs(createRandomNumber());
        ArrayList android_version = new ArrayList<>();
        for(int i=0; i < imagesCount; i++){
            AndroidVersion androidVersion = new AndroidVersion();
            androidVersion.setAndroid_image_url(android_image_urls[i]);
            android_version.add(androidVersion);
        }
        return android_version;
    }
}
