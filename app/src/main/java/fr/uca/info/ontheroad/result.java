package fr.uca.info.ontheroad;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        HashMap<String, Boolean> hashMap = (HashMap<String, Boolean>)intent.getSerializableExtra("map");
        System.out.println("HashMap<String, Boolean> hashMap");
        System.out.println(hashMap.size());
        System.out.println(hashMap);
    }
}
