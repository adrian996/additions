package com.adrian.assignment.service;

import com.adrian.assignment.dto.AdditionObjectResponseDTO;
import com.adrian.assignment.model.AdditionObject;
import com.adrian.assignment.repo.AdditionObjectRepo;
import com.adrian.assignment.validator.AdditionObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionObjectService {
    private final AdditionObjectRepo additionObjectRepo;
    private final AdditionObjectValidator additionObjectValidator;

    public AdditionObject addValues(Integer addable1, Integer addable2) {

        if (additionObjectValidator.validateAddables(addable1, addable2)){
            AdditionObject additionObject = AdditionObject.builder()
                    .addable1(addable1)
                    .addable2(addable2)
                    .sum(addable1 + addable2)
                    .build();
            additionObjectRepo.save(additionObject);
            return additionObject;

        }
        return null;
    }

    public AdditionObjectResponseDTO findPreviousAdditions(Integer value, Boolean ascending) {
        List<AdditionObject> additionObjects;

        if (value != null) {
            additionObjects = additionObjectRepo.findByValue(value, ascending);
        } else {
            additionObjects = additionObjectRepo.findAll(ascending);
        }

        return AdditionObjectResponseDTO.builder()
                .additionObjects(additionObjects)
                .listSize(additionObjects.size())
                .build();
    }
}
