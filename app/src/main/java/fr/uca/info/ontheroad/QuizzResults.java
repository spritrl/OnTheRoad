package fr.uca.info.ontheroad;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuizzResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_results);

        Intent intent = getIntent();
        HashMap<String, Boolean> reponsesList = (HashMap<String, Boolean>)intent.getSerializableExtra("map");
        ArrayList<String> sectionData = (ArrayList<String>)intent.getSerializableExtra("SectionData");

        System.out.println(sectionData);

        System.out.println(reponsesList);

        ListView answersList = (ListView) findViewById(R.id.resultsListView);
        TextView resultsText = (TextView) findViewById(R.id.resultsText);
        Button btnBackHome = (Button) findViewById(R.id.button);

        String[] results = new String[reponsesList.size()];

        int i = 0;
        int notation = 0;
        for(Map.Entry e : reponsesList.entrySet()){
            if((boolean)e.getValue()) notation++;
            results[i] = e.getKey() + " : " + ((boolean)e.getValue() ? "Vrai" : " Faux");
            System.out.println(results[i]);
            i++;
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.results_list_layout, R.id.result, results);
        answersList.setAdapter(adapter);

        resultsText.setText("" + notation + "/" + results.length);

        btnBackHome.setOnClickListener(v -> {
            /*
            try{
                JSONObject jsonObject = (JSONObject) new FileReader("/history.json");
            }catch (IOException e){

            }*/


            Intent activity2Intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(activity2Intent);
        });
    }
}
