package fr.uca.info.ontheroad;

public class QuizzQuestion {
    private String question;
    private String goodAnswer;
    private String answer1;
    private String answer2;
    private String answer3;
    private String img;

    //Il fait beau?", "Oui",  "Oui", "Non", "Peut etre", R.drawable.f1
    public QuizzQuestion(String question,
                         String goodAnswer,
                         String answer1,
                         String answer2,
                         String answer3,
                         String img){
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer1;
        this.answer3 = answer1;
        this.img = img;
    }

    public String getAnswer(int i) {
        switch (i) {
            case 1 :
                return this.answer1;
            case 2 :
                return this.answer2;
            case 3 :
                return this.answer3;
        }
        return this.answer1;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getImage() {
        return this.img;
    }

    public String getCorrectAnswer() {
        return this.goodAnswer;
    }
}
