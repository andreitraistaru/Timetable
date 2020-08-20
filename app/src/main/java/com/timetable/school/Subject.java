package com.timetable.school;

import java.util.ArrayList;

public class Subject {
    private String name;
    private String description;
    private boolean differentOnEvenWeeks;
    private ArrayList<SubjectComponent> components;

    public Subject(String name, String description, boolean differentOnEvenWeeks) {
        this.name = name;
        this.description = description;
        this.differentOnEvenWeeks = differentOnEvenWeeks;
        this.components = new ArrayList<>();
    }

    public boolean isDifferentOnEvenWeeks() {
        return differentOnEvenWeeks;
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

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDifferentOnEvenWeeks(boolean differentOnEvenWeeks) {
        this.differentOnEvenWeeks = differentOnEvenWeeks;
    }

    public void addComponent(SubjectComponent component) {
        this.components.add(component);
    }
}
