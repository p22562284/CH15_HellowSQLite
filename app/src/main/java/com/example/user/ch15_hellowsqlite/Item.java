package com.example.user.ch15_hellowsqlite;

/**
 * Created by USER on 2016/3/12.
 */
public class Item implements java.io.Serializable{

    //編號、日期、醫生、醫院、專長、已選擇
    private long id;
    private long datetime;
    private String name;
    private String hospital;
    private String specialty;
    private boolean selected;

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Item(long id, long datetime, String name, String hospital, String specialty){
        this.id = id;
        this.datetime = datetime;
        this.name = name;
        this.hospital = hospital;
        this.specialty = specialty;
    }
    public Item() {

    }
}
