package com.example.aircraftwar2024.record;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Record implements Serializable {
    private String name;
    private int score;
    private String time;

    public Record(String username, int score){
        this.name = username;
        this.score = score;
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public Record(String recordString){
        String[] fields = recordString.split(",");
        name = fields[0];
        time = fields[2];
        try{
            score = Integer.parseInt(fields[1]);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
        }

    }

    public String getTime() {
        return time;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return name + "," + score + "," + time;
    }
}
