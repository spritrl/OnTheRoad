package fr.uca.info.ontheroad;

import java.util.ArrayList;

public class ListQuestion {
    ArrayList<Question> list;

    public ListQuestion(){
        list = new ArrayList<Question>();
    }

    public int size(){
        return list.size();
    }

    public Question getQuestion(int pos){
        return list.get(pos);
    }

    public ArrayList<Question> getList(){
        return list;
    }

    public ArrayList<Question> setList(){
        return list;
    }

    public void construireListe(){
        //list.add(new Question("Il fait beau?", "Oui", "Non", "Peut etre"));
        //list.add(new Question("Il pleut?", "Oui", "Non", "Peut etre"));
    }
}
