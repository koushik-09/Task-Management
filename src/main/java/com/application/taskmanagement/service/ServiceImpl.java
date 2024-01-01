package com.application.taskmanagement.service;

import com.application.taskmanagement.dao.TaskDAO;
import com.application.taskmanagement.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
The methods defined in service interface are implemented in service implementation class

The DAO class constructors are injected into service class
 */
@org.springframework.stereotype.Service
public class ServiceImpl implements Service{

    //Constructor Injection

    //define fields
    @Autowired
    private TaskDAO taskDAO;

    public ServiceImpl(){

    }

    //constructor injection
    public ServiceImpl(TaskDAO theTaskDAO){
        taskDAO = theTaskDAO;
    }

    //Display all tasks
    @Override
    public List<Task> allTasks() {
        return taskDAO.allTasks();
    }

    //Display task by id
    @Override
    public Task findById(int id) {
        return taskDAO.findById(id);
    }

    //Add a new task
    @Transactional
    @Override
    public Task newTask(Task task) {
        return taskDAO.newTask(task);
    }

    //update status of a task
    @Transactional
    @Override
    public Task completeTask(int id) {
        return taskDAO.completeTask(id);
    }

    //Update details of a task
    @Transactional
    @Override
    public Task updateTask(Task task) {
        return taskDAO.updateTask(task);
    }

    //Delete a task
    @Transactional
    @Override
    public void deleteTask(int id) {
        taskDAO.deleteTask(id);
    }

    @Override
    public List<Task> completedTasks() {
        return taskDAO.completedTasks();
    }

    @Override
    public List<Task> pendingTasks() {
        return taskDAO.pendingTasks();
    }
}
