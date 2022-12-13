package com.example.project_spring_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project_spring_boot.entity.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {


    @Query("""  
            SELECT i FROM Item i WHERE i.owner.id  = :id
                    """)
    List<Item> findByUser(@Param("id") Long id);
}
