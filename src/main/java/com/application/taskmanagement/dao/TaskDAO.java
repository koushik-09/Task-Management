package com.application.taskmanagement.dao;

import com.application.taskmanagement.entity.Task;

import java.util.List;

/*
Data Access Object class
 */
public interface TaskDAO {

    //display all tasks
    List<Task> allTasks();

    //find task by id
    Task findById(int id);

    //create a new task
    Task newTask(Task task);

    //change a status of task(complete or incomplete)
    Task completeTask(int id);

    //make changes in task
    Task updateTask(Task task);

    //remove a task
    void deleteTask(int id);

    //completed tasks
    List<Task> completedTasks();

    //pending tasks
    List<Task> pendingTasks();
}
