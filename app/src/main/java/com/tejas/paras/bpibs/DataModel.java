package com.tejas.paras.bpibs;

/**
 * Created by paras on 2/4/2017.
 */
public class DataModel {

    String roll;
    String name;
    String id;



    public DataModel(String name1, String id1, String roll1) {
        this.name=name1;
        this.id=id1;
        this.roll=roll1;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public String getRoll() {
        return roll;
    }


}