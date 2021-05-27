package fr.uca.info.ontheroad;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Section implements Parcelable {

    private String nom;
    private int id;
    private Color color;
    private String icon;
    private String description;
    private ArrayList<Question> questionList;

    public Section(String nom, int id, Color color, String icon, String description) {
        this.nom = nom;
        this.id = id;
        this.color = color;
        this.icon = icon;
        this.description = description;
        this.questionList = new ArrayList<>();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeInt(id);
    }

    protected Section(Parcel in) {
        nom = in.readString();
        id = in.readInt();;
    }

    public Section() {}
    public static final Creator<Section> CREATOR = new Creator<Section>() {
        @Override
        public Section createFromParcel(Parcel in) {
            return new Section(in);
        }

        @Override
        public Section[] newArray(int size) {
            return new Section[size];
        }
    };

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Question> getQuestions(){
        return this.questionList;
    }

    public void addQuestion(Question q){
        this.questionList.add(q);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Section{" +
                "nom='" + nom + '\'' +
                ", id=" + id +
                ", color=" + color +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", questionList=" + questionList +
                '}';
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

