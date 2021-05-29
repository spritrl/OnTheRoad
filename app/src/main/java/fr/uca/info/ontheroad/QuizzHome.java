package fr.uca.info.ontheroad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuizzHome extends AppCompatActivity {

    ArrayList<Question> questionArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_home);
        Intent intent = getIntent();
        Section section = intent.getParcelableExtra("Section");
        System.out.println("SECTION RECEIVED ::: " + section);

        questionArrayList = section.getQuestions();
        System.out.println(questionArrayList);

        TextView sectionNameText = (TextView) findViewById(R.id.sectionName);
        TextView sectionDescriptionText = (TextView) findViewById(R.id.sectionDescription);

        sectionNameText.setText("" + section.getNom());
        sectionDescriptionText.setText("" + section.getDescription());

        Button button = (Button) findViewById(R.id.btnStartQuizz);
        button.setOnClickListener(v -> {
            Intent myIntent = new Intent(QuizzHome.this,
                    MainActivity.class);
            myIntent.putExtra("Questions", questionArrayList);
            System.out.println("INTENT ::: " + myIntent.getExtras());
            QuizzHome.this.startActivity(myIntent);
        });
    }
}
