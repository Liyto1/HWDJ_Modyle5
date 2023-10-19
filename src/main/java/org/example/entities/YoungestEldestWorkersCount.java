package org.example.entities;

public class YoungestEldestWorkersCount {
    private String type;
    private String name;
    private String birthday;

    public YoungestEldestWorkersCount(String type, String name, String birthday) {
        this.type = type;
        this.name = name;
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }
}
