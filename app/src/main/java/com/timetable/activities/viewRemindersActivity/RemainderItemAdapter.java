package com.timetable.activities.viewRemindersActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rtugeek.android.colorseekbar.ColorSeekBar;
import com.timetable.R;
import com.timetable.database.reminders.Reminder;
import com.timetable.database.reminders.ReminderDatabase;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class RemainderItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class RemainderItemViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView deadline;
        private TextView details;
        private CardView cardView;
        private View view;

        public RemainderItemViewHolder(@NonNull View itemView) {
            super(itemView);

            this.title = itemView.findViewById(R.id.title_item_reminder);
            this.deadline = itemView.findViewById(R.id.deadline_item_reminder);
            this.details = itemView.findViewById(R.id.details_item_reminder);
            this.cardView = itemView.findViewById(R.id.cardView_item_reminder);
            this.view = itemView;
        }

        public CardView getCardView() {
            return cardView;
        }
        public View getView() {
            return view;
        }

        public void setTitle(String data) {
            this.title.setText(data);
        }
        public void setDeadline(String data) {
            this.deadline.setText(data);
        }
        public void setDetails(String data) {
            this.details.setText(data);
        }
        public void setColor (int color) {
            this.cardView.setCardBackgroundColor(color);
        }
    }

    private List<Reminder> reminders;
    private Context context;

    public RemainderItemAdapter(Context context) {
        this.reminders = null;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RemainderItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reminder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final Reminder reminder = reminders.get(position);

        ((RemainderItemViewHolder) holder).setTitle(reminder.getTitle());

        if (reminder.getDeadline() != null) {
            Calendar deadline = Calendar.getInstance();
            deadline.setTime(reminder.getDeadline());

            ((RemainderItemViewHolder) holder).setDeadline(context.getString(R.string.deadline_item_reminder,
                    deadline.get(Calendar.DAY_OF_MONTH), deadline.get(Calendar.MONTH) + 1,
                    deadline.get(Calendar.YEAR), deadline.get(Calendar.HOUR_OF_DAY), deadline.get(Calendar.MINUTE)));
        } else {
            ((RemainderItemViewHolder) holder).setDeadline(context.getString(R.string.no_deadline_item_reminder));
        }

        ((RemainderItemViewHolder) holder).setDetails(reminder.getDetails());
        ((RemainderItemViewHolder) holder).setColor(reminder.getColor());

        ((RemainderItemViewHolder) holder).getCardView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Context popupContext = new ContextThemeWrapper(context, R.style.popupMenu);
                PopupMenu popupMenu = new PopupMenu(popupContext, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_reminders_list, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.delete_popup_reminders_option:
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ReminderDatabase.getDatabase(context).getRemainderDao().deleteReminder(reminder);
                                    }
                                }).start();

                                reminders.remove(reminder);
                                notifyDataSetChanged();

                                break;
                            case R.id.edit_popup_reminders_option:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                ViewGroup viewGroup = ((RemainderItemViewHolder) holder).getView().findViewById(android.R.id.content);
                                final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_reminder, viewGroup, false);

                                builder.setView(dialogView);
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                                ((TextView) dialogView.findViewById(R.id.title_dialog_add_reminder)).setText(reminder.getTitle());
                                ((ColorSeekBar) dialogView.findViewById(R.id.color_dialog_add_reminder)).setColor(reminder.getColor());
                                ((TextView) dialogView.findViewById(R.id.details_dialog_add_reminder)).setText(reminder.getDetails());

                                final Calendar deadline = Calendar.getInstance();

                                if (reminder.getDeadline() == null) {
                                    ((CheckBox) dialogView.findViewById(R.id.deadlineInfo_dialog_add_reminder)).setChecked(false);
                                    dialogView.findViewById(R.id.deadline_dialog_add_reminder).setVisibility(View.GONE);
                                    dialogView.findViewById(R.id.changeDeadline_dialog_add_reminder).setVisibility(View.GONE);
                                    dialogView.findViewById(R.id.notificationInfo_dialog_add_reminder).setVisibility(View.GONE);
                                    dialogView.findViewById(R.id.notification_dialog_add_reminder).setVisibility(View.GONE);

                                    deadline.setTimeInMillis(0);

                                    ((TextView) dialogView.findViewById(R.id.deadline_dialog_add_reminder)).setText(context.getString(R.string.deadline_default_dialog_add_reminder));
                                } else {
                                    ((CheckBox) dialogView.findViewById(R.id.deadlineInfo_dialog_add_reminder)).setChecked(true);
                                    dialogView.findViewById(R.id.deadline_dialog_add_reminder).setVisibility(View.VISIBLE);
                                    dialogView.findViewById(R.id.changeDeadline_dialog_add_reminder).setVisibility(View.VISIBLE);
                                    dialogView.findViewById(R.id.notificationInfo_dialog_add_reminder).setVisibility(View.VISIBLE);
                                    dialogView.findViewById(R.id.notification_dialog_add_reminder).setVisibility(View.VISIBLE);

                                    ((Spinner) dialogView.findViewById(R.id.notification_dialog_add_reminder)).setSelection(reminder.getNotificationTime());

                                    deadline.setTime(reminder.getDeadline());

                                    ((TextView) dialogView.findViewById(R.id.deadline_dialog_add_reminder)).setText(context.getString(R.string.deadline_dialog_add_reminder,
                                            deadline.get(Calendar.DAY_OF_MONTH), deadline.get(Calendar.MONTH) + 1, deadline.get(Calendar.YEAR), deadline.get(Calendar.HOUR_OF_DAY), deadline.get(Calendar.MINUTE)));
                                }

                                (dialogView.findViewById(R.id.deadlineInfo_dialog_add_reminder)).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        CheckBox checkBox = (CheckBox) view;

                                        if (checkBox.isChecked()) {
                                            dialogView.findViewById(R.id.deadline_dialog_add_reminder).setVisibility(View.VISIBLE);
                                            dialogView.findViewById(R.id.changeDeadline_dialog_add_reminder).setVisibility(View.VISIBLE);
                                            dialogView.findViewById(R.id.notificationInfo_dialog_add_reminder).setVisibility(View.VISIBLE);
                                            dialogView.findViewById(R.id.notification_dialog_add_reminder).setVisibility(View.VISIBLE);
                                        } else {
                                            dialogView.findViewById(R.id.deadline_dialog_add_reminder).setVisibility(View.GONE);
                                            dialogView.findViewById(R.id.changeDeadline_dialog_add_reminder).setVisibility(View.GONE);
                                            dialogView.findViewById(R.id.notificationInfo_dialog_add_reminder).setVisibility(View.GONE);
                                            dialogView.findViewById(R.id.notification_dialog_add_reminder).setVisibility(View.GONE);
                                        }
                                    }
                                });

                                dialogView.findViewById(R.id.changeDeadline_dialog_add_reminder).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                        ViewGroup viewGroup = ((RemainderItemViewHolder) holder).getView().findViewById(android.R.id.content);
                                        final View dateTimeDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_date_time, viewGroup, false);

                                        builder.setView(dateTimeDialogView);
                                        final AlertDialog alertDialog = builder.create();
                                        alertDialog.show();

                                        final Calendar calendar = Calendar.getInstance();
                                        calendar.setTimeInMillis(((CalendarView) dateTimeDialogView.findViewById(R.id.calendarView_dialog_date_time)).getDate());
                                        calendar.set(Calendar.HOUR_OF_DAY, ((TimePicker) dateTimeDialogView.findViewById(R.id.timePicker_dialog_date_time)).getHour());
                                        calendar.set(Calendar.MINUTE, ((TimePicker) dateTimeDialogView.findViewById(R.id.timePicker_dialog_date_time)).getMinute());

                                        ((CalendarView) dateTimeDialogView.findViewById(R.id.calendarView_dialog_date_time)).setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                            @Override
                                            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                                                calendar.set(Calendar.YEAR, year);
                                                calendar.set(Calendar.MONTH, month);
                                                calendar.set(Calendar.DAY_OF_MONTH, day);
                                            }
                                        });

                                        ((TimePicker) dateTimeDialogView.findViewById(R.id.timePicker_dialog_date_time)).setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                                            @Override
                                            public void onTimeChanged(TimePicker timePicker, int hour, int minutes) {
                                                calendar.set(Calendar.HOUR_OF_DAY, hour);
                                                calendar.set(Calendar.MINUTE, minutes);
                                            }
                                        });

                                        dateTimeDialogView.findViewById(R.id.choose_dialog_date_time).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                deadline.setTime(calendar.getTime());

                                                ((TextView) dialogView.findViewById(R.id.deadline_dialog_add_reminder)).setText(context.getString(R.string.deadline_dialog_add_reminder, calendar.get(Calendar.DAY_OF_MONTH),
                                                        calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)));

                                                alertDialog.dismiss();
                                            }
                                        });
                                    }
                                });

                                (dialogView.findViewById(R.id.save_dialog_add_reminder)).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String title = ((TextView) dialogView.findViewById(R.id.title_dialog_add_reminder)).getText().toString();

                                        if (title.isEmpty()) {
                                            ((TextView) dialogView.findViewById(R.id.title_dialog_add_reminder)).setError(context.getString(R.string.no_title_error_dialog_add_reminder));
                                            dialogView.findViewById(R.id.title_dialog_add_reminder).requestFocus();

                                            return;
                                        }

                                        if (((CheckBox) dialogView.findViewById(R.id.deadlineInfo_dialog_add_reminder)).isChecked()) {
                                            if (deadline.getTimeInMillis() == 0) {
                                                Toast.makeText(context, context.getString(R.string.no_deadline_error_dialog_add_reminder), Toast.LENGTH_SHORT).show();

                                                return;
                                            }

                                            reminder.setDeadline(deadline.getTime());
                                            reminder.setNotificationTime(((Spinner) dialogView.findViewById(R.id.notification_dialog_add_reminder)).getSelectedItemPosition());
                                        } else {
                                            reminder.setDeadline(null);
                                            reminder.setNotificationTime(-1);
                                        }

                                        reminder.setTitle(title);
                                        reminder.setColor(((ColorSeekBar) dialogView.findViewById(R.id.color_dialog_add_reminder)).getColor());
                                        reminder.setDetails(((TextView) dialogView.findViewById(R.id.details_dialog_add_reminder)).getText().toString());

                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ReminderDatabase.getDatabase(context.getApplicationContext()).getRemainderDao().updateReminder(reminder);
                                            }
                                        }).start();

                                        alertDialog.dismiss();
                                    }
                                });

                                break;
                            default:
                        }

                        return true;
                    }
                });

                popupMenu.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (reminders == null) {
            return 0;
        }

        return reminders.size();
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;

        Collections.sort(reminders);

        notifyDataSetChanged();
    }
}
