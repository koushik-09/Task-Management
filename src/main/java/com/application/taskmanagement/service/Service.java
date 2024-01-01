package com.application.taskmanagement.service;

import com.application.taskmanagement.entity.Task;

import java.util.List;

/*
Service interface it acts as a mediator between rest controller and Data Access Object(DAO) classes

All the methods that are defined in all the DAO classes are also defined in service class
 */
public interface Service {

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
