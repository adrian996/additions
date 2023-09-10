package com.adrian.assignment.repo;

import com.adrian.assignment.model.AdditionObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionObjectRepo extends JpaRepository<AdditionObject, Long> {
    @Query("SELECT ao FROM AdditionObject ao WHERE ao.addable1 = :value OR ao.addable2 = :value OR ao.sum = :value ORDER BY CASE WHEN :ascending = true THEN ao.sum ELSE ao.sum * -1 END")
    List<AdditionObject> findByValue(@Param("value") Integer value, @Param("ascending") Boolean ascending);

    @Query("SELECT ao FROM AdditionObject ao ORDER BY CASE WHEN :ascending = true THEN ao.sum ELSE ao.sum * -1 END")
    List<AdditionObject> findAll(Boolean ascending);
}
