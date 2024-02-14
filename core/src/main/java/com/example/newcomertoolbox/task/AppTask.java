package com.example.newcomertoolbox.task;

import java.util.ArrayList;
import java.util.HashMap;

public class AppTask {
    public enum TaskEnum {
        START, END, ERROR
    }
    private static final HashMap<TaskEnum, ArrayList<Runnable>> tasks = new HashMap<>();

    public static void add(TaskEnum taskEnum, Runnable task) {
        if (tasks.containsKey(taskEnum)) {
            tasks.get(taskEnum).add(task);
        } else {
            ArrayList<Runnable> arrayList = new ArrayList<>();
            arrayList.add(task);
            tasks.put(taskEnum, arrayList);
        }
    }

    public static void run(TaskEnum taskEnum) {
        if (tasks.containsKey(taskEnum)) {
            for (Runnable task : tasks.get(taskEnum)) {
                task.run();
            }
        }
    }
}
