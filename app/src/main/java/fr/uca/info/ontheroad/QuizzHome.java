package fr.uca.info.ontheroad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizzHome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_home);
        Intent intent = getIntent();
        Section section = intent.getParcelableExtra("Section");
        TextView sectionNameText = (TextView) findViewById(R.id.sectionName);
        sectionNameText.setText("" + section.getNom() + " ; " + section.getDescription() + " ; " + section.getIcon() + " ; " + section.getColor());
    }
}
