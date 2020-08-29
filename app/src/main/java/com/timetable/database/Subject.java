package com.timetable.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity (tableName = "subjects")
public class Subject {
    @PrimaryKey (autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private boolean differentOnEvenWeeks;
    private ArrayList<SubjectComponent> components;

    public Subject(String name, String description, boolean differentOnEvenWeeks, ArrayList<SubjectComponent> components) {
        this.name = name;
        this.description = description;
        this.differentOnEvenWeeks = differentOnEvenWeeks;
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
    public boolean isDifferentOnEvenWeeks() {
        return differentOnEvenWeeks;
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
    public void setDifferentOnEvenWeeks(boolean differentOnEvenWeeks) {
        this.differentOnEvenWeeks = differentOnEvenWeeks;
    }
    public void setComponents(ArrayList<SubjectComponent> components) {
        this.components = components;
    }

    public void addComponent(SubjectComponent component) {
        this.components.add(component);
    }
}
