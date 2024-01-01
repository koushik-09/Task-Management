package com.application.taskmanagement.rest;

import com.application.taskmanagement.entity.Task;
import com.application.taskmanagement.exceptionhandling.GlobalException;
import com.application.taskmanagement.service.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@RestController annotation is a convenience annotation that is itself annotated with
    -@Controller and @ResponseBody
 */
@Controller
public class RestController {

    //inject the service layer(constructor injection)
    private Service service;
    public RestController(Service theService){
        service = theService;
    }

    //exposing the endpoints

    @GetMapping("/")
    public String homePage(Model theModel){
        List<Task> list = service.allTasks();
        theModel.addAttribute("tasks",list);
        return "Home";
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage(){
        return "fancy-login";
    }

    @GetMapping("/add-task")
    public String addTask(Model theModel){
        Task theTask = new Task();
        theModel.addAttribute("task",theTask);
        return "AddTask";
    }
    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") Task task){
        service.newTask(task);
        return "redirect:/";
    }
    @GetMapping("/display-all")
    public String displayTasks(Model theModel){
        List<Task> list = service.allTasks();
        if(list.isEmpty()){
            throw new GlobalException("No Tasks to fetch");
        }
        theModel.addAttribute("tasks",list);
        return "Home";
    }
    @GetMapping("/updateTask")
    public String updateTask(@RequestParam("id") int theId, Model theModel){
        Task newTask = service.findById(theId);
        theModel.addAttribute("task",newTask);
        return "/AddTask";
    }

    @GetMapping("/changeStatus")
    public String changeStatus(@RequestParam("id") int theId,Model theModel){
        Task newTask = service.findById(theId);
        if(newTask.getStatus()){
            newTask.setStatus(false);
        }
        else{
            newTask.setStatus(true);
        }
        service.newTask(newTask);
        return "redirect:/";
    }

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam("id") int theId,Model theModel){
        service.deleteTask(theId);
        return "redirect:/";
    }

    @GetMapping("/completedTasks")
    public String completedTasks(Model theModel){
        List<Task> theTask = service.completedTasks();
        theModel.addAttribute("tasks",theTask);
        return "Home";
    }

    @GetMapping("/pendingTasks")
    public String pendingTasks(Model theModel){
        List<Task> theTask = service.pendingTasks();
        theModel.addAttribute("tasks",theTask);
        return "Home";
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

}
