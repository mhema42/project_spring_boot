package com.example.project_spring_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.project_spring_boot.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

/* 
    @Query("""  
            SELECT i FROM Item i WHERE i.user.id  = :id
                    """)
    List<Item> findByUser(@Param("id") Long id);
*/
}
