package fr.uca.info.ontheroad;

import android.os.Parcel;
import android.os.Parcelable;

public class Reponse implements Parcelable {
    private int id;
    private String answerText;

    public Reponse(int id_, String answerText_){
        this.id = id_;
        this.answerText = answerText_;
    }

    protected Reponse(Parcel in) {
        id = in.readInt();
        answerText = in.readString();
    }

    public static final Creator<Reponse> CREATOR = new Creator<Reponse>() {
        @Override
        public Reponse createFromParcel(Parcel in) {
            return new Reponse(in);
        }

        @Override
        public Reponse[] newArray(int size) {
            return new Reponse[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(answerText);
    }
}
