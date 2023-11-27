package com.example.CostControl.Util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Component
public class GenerateRandomValue {
    public long generateRandomUniqueNumber(List<Long> listOfId) {
        long newUniqueUserId = new Random().nextLong() % 1001;
        if (newUniqueUserId < 0) {
            newUniqueUserId = -newUniqueUserId;
        }
        if (listOfId.contains(newUniqueUserId)) {
            generateRandomUniqueNumber(listOfId);
        }
        return newUniqueUserId;
    }
}
