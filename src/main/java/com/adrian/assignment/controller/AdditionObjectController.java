package com.adrian.assignment.controller;

import com.adrian.assignment.dto.AdditionObjectResponseDTO;
import com.adrian.assignment.model.AdditionObject;
import com.adrian.assignment.service.AdditionObjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j

public class AdditionObjectController {
    private final AdditionObjectService additionObjectService;

    @GetMapping("/addition")
    public ResponseEntity<?> addValues(@RequestParam(name = "addable1") String addable1Str,
                                       @RequestParam(name = "addable2") String addable2Str) {
        Integer addable1;
        Integer addable2;

        try {
            addable1 = Integer.parseInt(addable1Str);
            addable2 = Integer.parseInt(addable2Str);
        } catch (NumberFormatException e) {
            String errorMessage = "Parameetriteks peavad olema numbrilised täisarvud.";
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(errorMessage);
        }

        AdditionObject additionObject = additionObjectService.addValues(addable1, addable2);

        if (additionObject == null) {
            String errorMessage = "Otsitavad argumendid peavad olema > 0 ja < 100";
            return ResponseEntity.badRequest().body(errorMessage);
        }

        return ResponseEntity.ok(additionObject);
    }

    @GetMapping("/history")
    public ResponseEntity<?> findPreviousAdditions(
            @RequestParam(name = "value", required = false) String valueStr,
            @RequestParam(name = "ascending") String ascendingStr) {

        String errorMessage = "Parameetriteks peavad olema numbrilised täisarvud ning ascending - 'true' või 'false";
        Integer value = null;
        Boolean ascending = null;

        try {
            if (valueStr != null) {
                value = Integer.parseInt(valueStr);
            }
            ascending = Boolean.parseBoolean(ascendingStr);
            System.out.println(ascending);
        } catch (NumberFormatException e) {
            log.error("Validation error: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        AdditionObjectResponseDTO additionObject = additionObjectService.findPreviousAdditions(value, ascending);
        return ResponseEntity.ok(additionObject);
    }
}
