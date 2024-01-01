package com.application.taskmanagement.dao;

import com.application.taskmanagement.entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//Implementation class for Task Interface
@Repository
public class TaskDAOImpl implements TaskDAO{

    //define entitymanager
    //used to handle database operations
    private EntityManager entityManager;

    //constructor injection
    @Autowired
    public TaskDAOImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<Task> allTasks() {
        //create a sql query
        TypedQuery<Task> theQuery = entityManager.createQuery("from Task",Task.class);

        //execute the query
        List<Task> tasks = theQuery.getResultList();

        //return the results
        return tasks;
    }

    @Override
    public Task findById(int id) {
        //create a sql query to find consultant by id
        Task task = entityManager.find(Task.class,id);
        return task;
    }

    @Override
    public Task newTask(Task task) {
        Task newTask = entityManager.merge(task);
        return newTask;
    }

    @Override
    public Task completeTask(int id) {
        Task temp = findById(id);
        temp.setStatus(true);
        Task tempTask = entityManager.merge(temp);
        return tempTask;
    }

    @Override
    public Task updateTask(Task task) {
        Task temp = entityManager.merge(task);
        return temp;
    }

    @Override
    public void deleteTask(int id) {
        Task task = findById(id);
        if(task==null){
            return;
        }
        entityManager.remove(task);
    }

    @Override
    public List<Task> completedTasks() {
        TypedQuery<Task> theQuery = entityManager.createQuery("from Task where status=:theData",Task.class);
        theQuery.setParameter("theData",true);
        List<Task> list = theQuery.getResultList();
        return list;
    }

    @Override
    public List<Task> pendingTasks() {
        TypedQuery<Task> theQuery = entityManager.createQuery("from Task where status=:theData",Task.class);
        theQuery.setParameter("theData",false);
        List<Task> list = theQuery.getResultList();
        return list;
    }
}
