package fr.uca.info.ontheroad;

public class Reponse {
    private int id;
    private String answerText;

    public Reponse(int id_, String answerText_){
        this.id = id_;
        this.answerText = answerText_;
    }

    public int getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "id=" + id +
                ", answerText='" + answerText + '\'' +
                '}';
    }
}
