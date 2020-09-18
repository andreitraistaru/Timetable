package com.timetable.activities.viewRemindersActivity;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.timetable.R;
import com.timetable.database.reminders.Reminder;
import com.timetable.database.reminders.ReminderDatabase;

import java.util.Calendar;
import java.util.List;

public class RemainderItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class RemainderItemViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView deadline;
        private TextView details;
        private CardView cardView;

        public RemainderItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public CardView getCardView() {
            return cardView;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Reminder reminder = reminders.get(position);
        Calendar deadline = Calendar.getInstance();
        deadline.setTime(reminder.getDeadline());

        ((RemainderItemViewHolder) holder).setTitle(reminder.getTitle());
        ((RemainderItemViewHolder) holder).setDeadline(context.getString(R.string.deadline_item_reminder,
                deadline.get(Calendar.DAY_OF_MONTH), deadline.get(Calendar.MONTH) + 1,
                deadline.get(Calendar.YEAR), deadline.get(Calendar.HOUR_OF_DAY), deadline.get(Calendar.MINUTE)));
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
                        if (menuItem.getItemId() == R.id.delete_popup_reminders_option) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    ReminderDatabase.getDatabase(context).getRemainderDao().deleteReminder(reminder);
                                }
                            }).start();

                            reminders.remove(reminder);
                            notifyDataSetChanged();
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

        notifyDataSetChanged();
    }
}
