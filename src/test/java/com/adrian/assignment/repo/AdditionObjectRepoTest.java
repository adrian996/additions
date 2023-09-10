package com.adrian.assignment.repo;

import com.adrian.assignment.model.AdditionObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AdditionObjectRepoTest {

    @Autowired
    private AdditionObjectRepo additionObjectRepo;

    @BeforeEach
    public void setUp() {
        AdditionObject additionObject1 = new AdditionObject(1L, 1, 2, 3);
        AdditionObject additionObject2 = new AdditionObject(2L, 3, 2, 5);
        AdditionObject additionObject3 = new AdditionObject(3L, 30, 20, 50);
        additionObjectRepo.save(additionObject1);
        additionObjectRepo.save(additionObject2);
        additionObjectRepo.save(additionObject3);
    }

    @Test
    public void testFindByValueAscending() {
        List<AdditionObject> result = additionObjectRepo.findByValue(3, true);
        assertThat(result.get(0).getSum()).isEqualTo(3);
        assertThat(result.get(1).getSum()).isEqualTo(5);
    }

    @Test
    public void testFindByValueDescending() {
        List<AdditionObject> result = additionObjectRepo.findByValue(3, false);
        assertThat(result.get(0).getSum()).isEqualTo(5);
        assertThat(result.get(1).getSum()).isEqualTo(3);
    }

    @Test
    public void testFindAllAscending() {
        List<AdditionObject> result = additionObjectRepo.findAll(true);
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getSum()).isEqualTo(3);
        assertThat(result.get(1).getSum()).isEqualTo(5);
        assertThat(result.get(2).getSum()).isEqualTo(50);
    }

    @Test
    public void testFindAllDescending() {
        List<AdditionObject> result = additionObjectRepo.findAll(false);
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(2).getSum()).isEqualTo(3);
        assertThat(result.get(1).getSum()).isEqualTo(5);
        assertThat(result.get(0).getSum()).isEqualTo(50);
    }
}
