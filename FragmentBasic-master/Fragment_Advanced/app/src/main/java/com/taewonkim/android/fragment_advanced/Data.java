package com.taewonkim.android.fragment_advanced;

import java.util.ArrayList;

/**
 * Created by 태원 on 2016-10-07.
 */
public class Data {

    ArrayList<Observer> observers = new ArrayList<>();
    ArrayList<MusicData> database = new ArrayList<>();
    int position = -1;

    public interface Observer {
        public void update();
    }

    public int getPosition(){
        return position;
    }
    public void setPosition(int i){
        position = i;
        notifyChanged();
    }

    private void notifyChanged(){

    }

    public void attach(Observer o) {
        observers.add(o);
    }

}

class MusicData{

    String title;
    String artist;
}
