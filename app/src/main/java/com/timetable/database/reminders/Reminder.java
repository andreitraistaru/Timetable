package com.timetable.database.reminders;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "reminders")
public class Reminder implements Comparable<Reminder> {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private int color;
    private Date deadline;
    private String details;

    public Reminder() {}

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public int getColor() {
        return color;
    }
    public Date getDeadline() {
        return deadline;
    }
    public String getDetails() {
        return details;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public int compareTo(Reminder reminder) {
        if (deadline == null) {
            return 1;
        } else if (reminder.getDeadline() == null) {
            return -1;
        }

        return Long.compare(deadline.getTime(), reminder.getDeadline().getTime());
    }
}
