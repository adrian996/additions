package com.adrian.assignment.dto;

import com.adrian.assignment.model.AdditionObject;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AdditionObjectResponseDTO {
    private List<AdditionObject> additionObjects;
    private Integer listSize;
}
