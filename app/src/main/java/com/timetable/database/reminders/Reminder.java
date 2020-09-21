package com.timetable.database.reminders;

import android.text.format.DateUtils;

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
    private int notificationTime;
    private String details;
    private int notificationId;

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
    public int getNotificationTime() {
        return notificationTime;
    }
    public String getDetails() {
        return details;
    }
    public int getNotificationId() {
        return notificationId;
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
    public void setNotificationTime(int notificationTime) {
        this.notificationTime = notificationTime;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public Date computeNotificationTime() {
        if (deadline == null) {
            return null;
        }

        switch (notificationTime) {
            case 1:
                return new Date(deadline.getTime());
            case 2:
                return new Date(deadline.getTime() - 15 * DateUtils.MINUTE_IN_MILLIS);
            case 3:
                return new Date(deadline.getTime() - DateUtils.HOUR_IN_MILLIS);
            case 4:
                return new Date(deadline.getTime() - DateUtils.DAY_IN_MILLIS);
            case 5:
                return new Date(deadline.getTime() - 3 * DateUtils.DAY_IN_MILLIS);
            case 6:
                return new Date(deadline.getTime() - DateUtils.WEEK_IN_MILLIS);
            default:
                return null;
        }
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
