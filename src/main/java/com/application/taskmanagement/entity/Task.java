package com.application.taskmanagement.entity;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Task")
public class Task {

    //define fields
    @jakarta.persistence.Id //defines primary key of table
    @GeneratedValue(strategy = GenerationType.IDENTITY) //value of id is generated automatically
    @Column(name="Id")
    private int Id;

    @Column(name = "task_name")
    private String name;

    @Column(name = "task_desc")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_created")
    private Date date = new Date(System.currentTimeMillis());

    @Column(name = "status")
    private boolean status;

    //define constructors
    public Task(){

    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//
//        this.date = sdf.format(date);
//        this.status = false; //Initially every task is incomplete
    }

    //getters and setters

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
