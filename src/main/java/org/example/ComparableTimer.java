package org.example;

import java.util.Timer;

public class ComparableTimer {

    private long startTime;
    private long endTime;
    private boolean isTimerStarted = false;

    public void startTimer() {
        if (!isTimerStarted) {
            startTime = System.currentTimeMillis();
        }
        isTimerStarted = true;
    }

    public long stopTimerAndReturnResult() {
        endTime = System.currentTimeMillis();
        isTimerStarted = false;
        return endTime - startTime;
    }
}
