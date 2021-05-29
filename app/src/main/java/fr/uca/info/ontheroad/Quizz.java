package fr.uca.info.ontheroad;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
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

    ArrayList<QuizzQuestion> questionBank = new ArrayList<QuizzQuestion>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz);
        Intent intent = getIntent();
        ArrayList<Question> jsonFromHomeQuizz = (ArrayList<Question>)intent.getSerializableExtra("Questions");

        for(int i = 0; i < jsonFromHomeQuizz.size(); i++) {
            jsonFromHomeQuizz.get(i).getQuestionText();
            String stringGoodAnswer = null;
            for(int y = 0; y < jsonFromHomeQuizz.get(i).getAnswersList().size(); y++) {
                if (jsonFromHomeQuizz.get(i).getAnswersList().get(y).getId() == jsonFromHomeQuizz.get(i).getGoodAnswer()) {
                    stringGoodAnswer = jsonFromHomeQuizz.get(i).getAnswersList().get(y).getAnswerText();
                }
            }
            QuizzQuestion newQuestion =
                    new QuizzQuestion(
                            jsonFromHomeQuizz.get(i).getQuestionText(),
                            stringGoodAnswer,
                            jsonFromHomeQuizz.get(i).getAnswersList().get(0).getAnswerText(),
                            jsonFromHomeQuizz.get(i).getAnswersList().get(1).getAnswerText(),
                            jsonFromHomeQuizz.get(i).getAnswersList().get(2).getAnswerText(),
                            jsonFromHomeQuizz.get(i).getImage());
            questionBank.add(newQuestion);

        }
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);

        answer1.setText(questionBank.get(currentQuestionIndex).getAnswer(1));
        answer2.setText(questionBank.get(currentQuestionIndex).getAnswer(2));
        answer3.setText(questionBank.get(currentQuestionIndex).getAnswer(3));

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);

        questionText
                = findViewById(R.id.questionText);

        questionText.setText(questionBank.get(0).getQuestion());

        Image = findViewById(R.id.questionImage);
        Resources resources = this.getResources();
        final int resourcesImage = resources.getIdentifier(questionBank.get(currentQuestionIndex).getImage(), "drawable",
                this.getPackageName());
        Image.setImageResource(resourcesImage);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.answer1:
                checkAnswer(questionBank.get(currentQuestionIndex).getAnswer(1));
                lastQuestion();
                break;

            case R.id.answer2:
                checkAnswer(questionBank.get(currentQuestionIndex).getAnswer(2));
                lastQuestion();
                break;

            case R.id.answer3:
                checkAnswer(questionBank.get(currentQuestionIndex).getAnswer(3));
                lastQuestion();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateQuestion()
    {
        questionText.setText(
                questionBank.get(currentQuestionIndex).getQuestion());
        Resources resources = this.getResources();
        final int resourcesImage = resources.getIdentifier(questionBank.get(currentQuestionIndex).getImage(), "drawable",
                this.getPackageName());
        Image.setImageResource(resourcesImage);
    }

    private void checkAnswer(String answer)
    {
        String correctAnswer
                = questionBank.get(currentQuestionIndex)
                .getCorrectAnswer();
        System.out.println("Main checkAnswer start = "+answerList);
        if (answer.equals(correctAnswer)) {
            correct++;
            answerList.put(questionBank.get(currentQuestionIndex).getQuestion(), true);
            System.out.println(correct);
        } else {
            answerList.put(questionBank.get(currentQuestionIndex).getQuestion(), false);
        }
        System.out.println("Main checkAnswer end = "+answerList);
    }

    private void lastQuestion()
    {
        if (currentQuestionIndex < questionBank.size()) {
            currentQuestionIndex
                    = currentQuestionIndex + 1;
            if (currentQuestionIndex == questionBank.size()) {
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
