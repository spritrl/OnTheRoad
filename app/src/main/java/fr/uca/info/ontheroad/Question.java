package fr.uca.info.ontheroad;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Question implements Parcelable {
    private int id;
    private String questionText;
    private String imgId;
    private ArrayList<Reponse> answersList;
    private int goodAnswer;

    public Question(int id_, String questionText_, int goodAnswer_, String imgId_){
        this.id = id_;
        this.questionText = questionText_;
        this.goodAnswer = goodAnswer_;
        this.answersList = new ArrayList<>();
        this.imgId = imgId_;
    }

    protected Question(Parcel in) {
        id = in.readInt();
        questionText = in.readString();
        answersList = in.createTypedArrayList(Reponse.CREATOR);
        goodAnswer = in.readInt();
        imgId = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getGoodAnswer() {
        return this.goodAnswer;
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
                ", imgId=" + imgId +
                ", answersList=" + answersList +
                ", goodAnswer=" + goodAnswer +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(questionText);
        dest.writeTypedList(answersList);
        dest.writeInt(goodAnswer);
        dest.writeString(imgId);
    }

    public String getImage() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }
}
