package com.akira.firstAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akira.firstAPI.models.Task;
import com.akira.firstAPI.models.User;
import com.akira.firstAPI.repositories.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private UserService userService;
     
    @Autowired
    private TaskRepository taskRepository;


    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(()-> new RuntimeException(
            "Tarefa nao encontrada, Id:"+id+"Tipo: "+Task.class.getName()
            ));
    }


    public List<Task> findAllByUserId(Long userId){
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }
    

    @Transactional
    public Task createrTask(Task objTask){
        User user = this.userService.findById(objTask.getUser().getId());
        objTask.setId(null);
        objTask.setUser(user);
        objTask = this.taskRepository.save(objTask);
        return objTask;
    }

    public Task updateTask(Task objTask){
        Task newObj = findById(objTask.getId());
        newObj.setDescription(objTask.getDescription());
        return this.taskRepository.save(newObj);

    }


    public void delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel deletar essa tarefa");
        }
    }

}
