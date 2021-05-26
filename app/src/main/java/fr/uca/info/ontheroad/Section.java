package fr.uca.info.ontheroad;

import android.os.Parcel;
import android.os.Parcelable;

public class Section implements Parcelable {

    private String nom;
    private int id;

    public Section(String nom, int id) {
        this.nom = nom;
        this.id = id;
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


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString(){
        return
                "nom='" + nom + '\'' +
                        ", id=" + id;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }
}

