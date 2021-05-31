package fr.uca.info.ontheroad;

import android.graphics.Color;

import java.util.ArrayList;

import fr.uca.info.ontheroad.Section;

public class ListSection {

    ArrayList<Section> list;

    public ListSection(){
        list = new ArrayList<Section>();
    }

    public int size(){
        return list.size();
    }

    public Section getSection(int pos){
        return list.get(pos);
    }

    public ArrayList<Section> getList(){
        return list;
    }

    public ArrayList<Section> setList(){
        return list;
    }

    public void construireListe(){
        list.add(new Section("Les Panneaux", 1, "#8F98FF", "warning", "Les questions portent sur le thème de ..."));
        list.add(new Section("La Route", 2, "#FF7648", "worktools", "Les questions portent sur le thème de ..."));
        list.add(new Section("Mécanique & Équipement", 3, "#4DC591", "support", "Les questions portent sur le thème de ..."));
        list.add(new Section("Premiers Secours", 4, "#65A4DA", "heartbeat", "Les questions portent sur le thème de ..."));
        list.add(new Section("Autres Usagers", 5, "#EB5D76", "group", "Les questions portent sur le thème de ..."));
    }
}


