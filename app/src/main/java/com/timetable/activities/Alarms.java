package com.timetable.activities;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.timetable.R;
import com.timetable.database.reminders.Reminder;
import com.timetable.database.reminders.ReminderDatabase;

import java.util.Calendar;
import java.util.Date;

public class Alarms extends BroadcastReceiver {
    private static final String notificationIdBundleKey = "notificationId";

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getExtras() == null) {
            return;
        }

        final int notificationId = intent.getExtras().getInt(notificationIdBundleKey);

        NotificationChannel notificationChannel = new NotificationChannel(context.getString(R.string.reminders_notification_channel_id), context.getString(R.string.reminders_notification_channel_name), NotificationManager.IMPORTANCE_HIGH);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);

        Intent notificationIntent = new Intent(context, Alarms.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        final PendingIntent notificationPendingIntent = PendingIntent.getActivity(context, notificationId, notificationIntent, 0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Reminder reminder = ReminderDatabase.getDatabase(context).getRemainderDao().getReminderWithNotificationId(notificationId);
                Calendar calendar = Calendar.getInstance();

                calendar.setTime(reminder.getDeadline());

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, context.getString(R.string.reminders_notification_channel_id))
                        .setSmallIcon(R.drawable.launcher_icon)
                        .setContentTitle(context.getString(R.string.reminders_notification_title, reminder.getTitle()))
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(context.getString(R.string.reminders_notification_content, calendar.get(Calendar.DAY_OF_MONTH),
                                        calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                                        reminder.getDetails().isEmpty() ? "-" : reminder.getDetails())))
                        .setContentIntent(notificationPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(notificationId, builder.build());
            }
        }).start();
    }

    public static void createAlarm(Reminder reminder, Context context) {
        Date notificationTime = reminder.computeNotificationTime();

        if (notificationTime == null) {
            return;
        }

        Intent intent = new Intent(context, Alarms.class);
        intent.putExtra(notificationIdBundleKey, reminder.getNotificationId());
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, reminder.getNotificationId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(notificationTime.getTime(), null);

        if(alarmManager != null) {
            // alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);

            alarmManager.setAlarmClock(alarmClockInfo, pendingIntent);
        }
    }

    public static void removeAlarm(Reminder reminder, Context context) {
        Date notificationTime = reminder.computeNotificationTime();

        if (notificationTime == null) {
            return;
        }

        Intent intent = new Intent(context, Alarms.class);
        intent.putExtra(notificationIdBundleKey, reminder.getNotificationId());
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, reminder.getNotificationId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if(alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }
}
