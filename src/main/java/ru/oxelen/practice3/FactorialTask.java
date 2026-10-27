package ru.oxelen.practice3;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {
    private final int start, end;

    public FactorialTask(int n) {
        this.start = 1;
        this.end = n;
    }

    public FactorialTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int range = end - start;
        if (range == 0) {
            return start;
        } else if (range == 1) {
            return start * end;
        }
        int mid = start + range / 2;
        FactorialTask leftTask = new FactorialTask(start, mid);
        leftTask.fork();

        return new FactorialTask(mid + 1, end).compute() * leftTask.join();
    }
}
