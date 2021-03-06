package fr.uca.info.ontheroad;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListSection listSection = new ListSection();
    ArrayList<String> SectionName = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        listSection.construireListe();
        addToList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.home_list_layout, R.id.sectionText, SectionName) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView sectionName = (TextView) view.findViewById(R.id.sectionText);
                ImageView sectionIcon = (ImageView) view.findViewById(R.id.sectionIcon);

                Resources resources = getApplication().getResources();
                final int resourcesImage = resources.getIdentifier(listSection.getSection(position).getIcon(), "drawable",
                        getApplication().getPackageName());

                sectionIcon.setImageResource(resourcesImage);

                GradientDrawable border = new GradientDrawable();
                border.setColor(Color.parseColor(listSection.getSection(position).getColor()));
                border.setStroke(2, Color.parseColor(listSection.getSection(position).getColor()));
                border.setCornerRadius(20);

                LinearLayout mainLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
                mainLayout.setBackground(border);

                sectionName.setText("" + listSection.getSection(position).getNom());

                return view;
            }
        };
        ListView sectionList = (ListView)findViewById(R.id.listView);
        sectionList.setAdapter(arrayAdapter);

        try{
            readJSON(getApplicationContext());
        }catch (JSONException e){
            e.printStackTrace();
        }

        ImageView homeImgBtn = (ImageView) findViewById(R.id.homeBtn);
        homeImgBtn.setOnClickListener(v -> {
            Intent intentToProfilActivity = new Intent(MainActivity.this, UserProfil.class);
            MainActivity.this.startActivity(intentToProfilActivity);
        });

        sectionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long id) {
                System.out.println("SECTION CLICKED ::: " + listSection.getSection(position));
                Intent myIntent = new Intent(MainActivity.this,
                        QuizzHome.class);
                myIntent.putExtra("Section", listSection.getSection(position));
                System.out.println("INTENT ::: " + myIntent.getExtras());
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    public void addToList() {
        for (int i = 0; i < listSection.getList().size(); i++) {
            SectionName.add(listSection.getSection(i).getNom());
        }
    }

    public void readJSON(Context context) throws JSONException {
        String stringJson = null;
        try{
            InputStream is = context.getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            stringJson = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(stringJson);

            for(int sections = 0; sections < listSection.size(); sections++){
                for(int questions = 0; questions < jsonArray.getJSONObject(sections).getJSONArray("QA_section").length(); questions++) {
                    Question question = new Question(
                            jsonArray.getJSONObject(sections).getJSONArray("QA_section").getJSONObject(questions).getInt("id_question"),
                            jsonArray.getJSONObject(sections).getJSONArray("QA_section").getJSONObject(questions).getString("question_text"),
                            jsonArray.getJSONObject(sections).getJSONArray("QA_section").getJSONObject(questions).getInt("correct_answer_id"),
                            jsonArray.getJSONObject(sections).getJSONArray("QA_section").getJSONObject(questions).getString("img_id")
                    );
                    for(int reponses = 0; reponses < jsonArray.getJSONObject(sections).getJSONArray("QA_section").getJSONObject(questions).getJSONArray("reponses_text").length(); reponses++){
                        Reponse reponse = new Reponse(
                                jsonArray.getJSONObject(sections).getJSONArray("QA_section").getJSONObject(questions).getJSONArray("reponses_text").getJSONObject(reponses).getInt("id"),
                                jsonArray.getJSONObject(sections).getJSONArray("QA_section").getJSONObject(questions).getJSONArray("reponses_text").getJSONObject(reponses).getString("text")
                        );
                        question.addAnswer(reponse);
                    }
                    listSection.getSection(sections).addQuestion(question);
                }
                System.out.println(listSection.getSection(sections));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
