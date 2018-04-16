package com.example.apurva.sqlitedatabase;

public class CriminalRecord {
    int id;
    String name,desp;

    public CriminalRecord(int id, String name, String desp) {
        this.id = id;
        this.name = name;
        this.desp = desp;
    }

    public CriminalRecord(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }
}
