package com.adrian.assignment.service;

import com.adrian.assignment.dto.AdditionObjectResponseDTO;
import com.adrian.assignment.model.AdditionObject;
import com.adrian.assignment.repo.AdditionObjectRepo;
import com.adrian.assignment.validator.AdditionObjectValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdditionObjectServiceTest {

    @InjectMocks
    private AdditionObjectService additionObjectService;
    @Mock
    private AdditionObjectRepo additionObjectRepo;
    @Mock
    private AdditionObjectValidator additionObjectValidator;

    @Test
    public void testAddValuesValid() {
        when(additionObjectValidator.validateAddables(2, 3)).thenReturn(true);

        AdditionObject result = additionObjectService.addValues(2, 3);

        assertThat(result).isNotNull();
        assertThat(result.getAddable1()).isEqualTo(2);
        assertThat(result.getAddable2()).isEqualTo(3);
        assertThat(result.getSum()).isEqualTo(5);
        verify(additionObjectRepo, times(1)).save(any(AdditionObject.class));
    }

    @Test
    public void testAddValuesInvalid() {
        when(additionObjectValidator.validateAddables(101, -1)).thenReturn(false);

        AdditionObject result = additionObjectService.addValues(101, -1);

        assertThat(result).isNull();
        verify(additionObjectRepo, never()).save(any(AdditionObject.class));
    }

    @Test
    public void testFindPreviousAdditionsWithValueAndAscending() {
        Integer value = 5;
        Boolean ascending = true;
        List<AdditionObject> additionObjects = Arrays.asList(
                new AdditionObject(1L, 1, 2, 3),
                new AdditionObject(2L, 4, 1, 5)
        );
        when(additionObjectRepo.findByValue(value, ascending)).thenReturn(additionObjects);

        AdditionObjectResponseDTO result = additionObjectService.findPreviousAdditions(value, ascending);

        assertThat(result).isNotNull();
        assertThat(result.getAdditionObjects()).isEqualTo(additionObjects);
        assertThat(result.getListSize()).isEqualTo(2);
    }
}
