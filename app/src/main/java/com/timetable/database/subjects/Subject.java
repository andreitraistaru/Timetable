package com.timetable.database.subjects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity (tableName = "subjects")
public class Subject {
    @PrimaryKey (autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private ArrayList<SubjectComponent> components;

    public Subject(String name, String description, ArrayList<SubjectComponent> components) {
        this.name = name;
        this.description = description;
        this.components = components;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public ArrayList<SubjectComponent> getComponents() {
        return components;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setComponents(ArrayList<SubjectComponent> components) {
        this.components = components;
    }

    public void addComponent(SubjectComponent component) {
        this.components.add(component);
    }
}
