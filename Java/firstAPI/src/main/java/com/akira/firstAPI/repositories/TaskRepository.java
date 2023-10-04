package com.akira.firstAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akira.firstAPI.models.Task;
import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    
    
   
    List<Task> findByUser_Id(Long id) ;



}
