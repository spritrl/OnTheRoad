package fr.uca.info.ontheroad;

import java.util.ArrayList;

public class Question {
    private int id;
    private String questionText;
    private ArrayList<Reponse> answersList;
    private int goodAnswer;

    public Question(int id_, String questionText_, int goodAnswer_){
        this.id = id_;
        this.questionText = questionText_;
        this.goodAnswer = goodAnswer_;
        this.answersList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void addAnswer(Reponse reponse){
        this.answersList.add(reponse);
    }

    public ArrayList<Reponse> getAnswersList() {
        return answersList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", answersList=" + answersList +
                ", goodAnswer=" + goodAnswer +
                '}';
    }
}