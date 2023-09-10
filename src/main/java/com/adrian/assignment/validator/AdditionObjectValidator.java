package com.adrian.assignment.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AdditionObjectValidator {

    public boolean validateAddables(Integer... addables) {
        if (addables == null) {
            log.info("Liidetavad peavad olema väärtustatud.");
            return false;
        }

        for (Integer addable : addables) {
            if (addable == null || addable < 0 || addable > 100) {
                log.info("Liidetava väärtus väljaspool lubatud ala.");
                return false;
            }
        }

        return true;
    }

}
