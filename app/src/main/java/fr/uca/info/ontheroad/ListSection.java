package fr.uca.info.ontheroad;

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
        list.add(new Section("Les Panneaux", 1));
        list.add(new Section("La Route", 2));
        list.add(new Section("Mécanique & Équipement", 3));
        list.add(new Section("Premiers Secours", 4));
        list.add(new Section("Autres Usagers", 5));
    }


}


