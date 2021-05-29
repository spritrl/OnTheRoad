package fr.uca.info.ontheroad;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class Quizz extends AppCompatActivity
        implements View.OnClickListener {
    private Button answer1;
    private Button answer2;
    private Button answer3;

    private ImageView Image;
    private TextView questionText;

    private int correct = 0;
    private int currentQuestionIndex = 0;

    private HashMap<String, Boolean> answerList = new HashMap<String, Boolean>();

    private Question[] questionBank = new Question[] {
            new Question("Il fait beau?", "Oui",  "Oui", "Non", "Peut etre", R.drawable.f1),
            new Question("Il pleut demain?", "Oui",  "Oui", "Non", "Peut etre", R.drawable.f1),
            new Question("Steve est bg?", "Oui",  "Oui", "Non", "Peut etre", R.drawable.f1),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);

        answer1.setText(questionBank[currentQuestionIndex].getAnswer(1));
        answer2.setText(questionBank[currentQuestionIndex].getAnswer(2));
        answer3.setText(questionBank[currentQuestionIndex].getAnswer(3));

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);

        questionText
                = findViewById(R.id.questionText);

        questionText.setText(questionBank[0].getQuestion());

        Image = findViewById(R.id.questionImage);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.answer1:
                checkAnswer(questionBank[currentQuestionIndex].getAnswer(1));
                lastQuestion();
                break;

            case R.id.answer2:
                checkAnswer(questionBank[currentQuestionIndex].getAnswer(2));
                lastQuestion();
                break;

            case R.id.answer3:
                checkAnswer(questionBank[currentQuestionIndex].getAnswer(3));
                lastQuestion();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateQuestion()
    {
        questionText.setText(
                questionBank[currentQuestionIndex].getQuestion());
        Image.setImageResource(questionBank[currentQuestionIndex].getImage());
    }

    private void checkAnswer(String answer)
    {
        String correctAnswer
                = questionBank[currentQuestionIndex]
                .getCorrectAnswer();
        System.out.println("Main checkAnswer start = "+answerList);
        if (answer.equals(correctAnswer)) {
            correct++;
            answerList.put(questionBank[currentQuestionIndex].getQuestion(), true);
            System.out.println(correct);
        } else {
            answerList.put(questionBank[currentQuestionIndex].getQuestion(), false);
        }
        System.out.println("Main checkAnswer end = "+answerList);
    }

    private void lastQuestion()
    {
        if (currentQuestionIndex < questionBank.length) {
            currentQuestionIndex
                    = currentQuestionIndex + 1;
            if (currentQuestionIndex == questionBank.length) {
                Intent myIntent = new Intent(this, result.class);
                myIntent.putExtra("map", answerList);
                startActivity(myIntent);
            }
            else {
                updateQuestion();
            }
        }
    }
}
