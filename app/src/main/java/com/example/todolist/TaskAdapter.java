// TaskAdapter.java
package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private final Context context;
    private final List<TaskModel> taskList;
    private final OnTaskClickListener listener;

    public TaskAdapter(Context context, List<TaskModel> taskList, OnTaskClickListener listener) {
        this.context = context;
        this.taskList = taskList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskModel task = taskList.get(position);
        holder.taskNameTextView.setText(task.getName());

        holder.itemView.setOnClickListener(view -> {
            int clickedPosition = holder.getAdapterPosition();
            if (clickedPosition != RecyclerView.NO_POSITION) {
                listener.onTaskClick(taskList.get(clickedPosition));
            }
        });

        holder.itemView.setOnLongClickListener(view -> {
            int clickedPosition = holder.getAdapterPosition();
            if (clickedPosition != RecyclerView.NO_POSITION) {
                listener.onDeleteClick(clickedPosition);
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public interface OnTaskClickListener {
        void onUpdateClick(int position);
        void onDeleteClick(int position);
        void onTaskClick(TaskModel task);
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskNameTextView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskNameTextView = itemView.findViewById(R.id.taskNameTextView);
        }
    }
}
