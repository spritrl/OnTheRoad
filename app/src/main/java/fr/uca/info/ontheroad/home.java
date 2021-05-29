package fr.uca.info.ontheroad;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class home extends AppCompatActivity {

    ListSection listSection = new ListSection();
    ArrayList<String> SectionName = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        listSection.construireListe();
        addToList();
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,
                        R.layout.home, R.id.sectionText, SectionName);
        ListView sectionList = (ListView)findViewById(R.id.listView);
        sectionList.setAdapter(arrayAdapter);


        sectionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long id) {
                Intent myIntent = new Intent(home.this,
                        QuizzHome.class);
                myIntent.putExtra("Section", listSection.getSection(position));
                home.this.startActivity(myIntent);
            }
        });
    }

    public void addToList() {
        for (int i = 0; i < listSection.getList().size(); i++) {
            SectionName.add(listSection.getSection(i).getNom());
        }
    }
}