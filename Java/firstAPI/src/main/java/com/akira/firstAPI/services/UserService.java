package com.akira.firstAPI.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akira.firstAPI.models.User;
import com.akira.firstAPI.repositories.TaskRepository;
import com.akira.firstAPI.repositories.UserRepository;

@Service 
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TaskRepository  taskRepository;



    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
            "Usuairo Nao encontrado! Id: " +id+ ", Tipo: "+ User.class.getName()
        ));
    }
  

    
    @Transactional
    public User creatUser(User obj){
        obj.setId(null);
     obj = this.userRepository.save(obj);
     this. taskRepository.saveAll(obj.getTasks());
     return obj;
    }

    @Transactional
    public User updateUser(User obj){
        User newObjUser = findById(obj.getId());
        newObjUser.setPassword(obj.getPassword());

        return this.userRepository.save(newObjUser);
    }


    public void delete(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir, pois há entidades relacionadas!");
        }
    }
}
