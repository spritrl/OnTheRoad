package fr.uca.info.ontheroad;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class QuizzResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_results);

        Intent intent = getIntent();
        HashMap<String, Boolean> reponsesList = (HashMap<String, Boolean>)intent.getSerializableExtra("Map");

        ListView answersList = (ListView) findViewById(R.id.resultsListView);
        TextView resultsText = (TextView) findViewById(R.id.resultsText);

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
    }
}
