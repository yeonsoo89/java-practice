package com.yeonsoo.domain;

import java.io.Serializable;

public class Workout implements Serializable {

    private int no;
    private String date; // yyyy-MM-dd
    private ExerciseType exerciseType;
    private int duration;
    private int calories;

    public Workout(int no, String date, ExerciseType exerciseType, int duration, int calories) {
        this.no = no;
        this.date = date;
        this.exerciseType = exerciseType;
        this.duration = duration;
        this.calories = calories;
    }

    public Workout update(String date, ExerciseType exerciseType, int duration, int calories) {
        return new Workout(this.no, date, exerciseType, duration, calories);
    }

    public int getNo() { return no; }
    public String getDate() { return date; }
    public ExerciseType getExerciseType() { return exerciseType; }
    public int getDuration() { return duration; }
    public int getCalories() { return calories; }

    @Override
    public String toString() {
        return "Workout{" +
                "no=" + no +
                ", date='" + date + '\'' +
                ", exerciseType=" + exerciseType +
                ", duration=" + duration +
                ", calories=" + calories +
                '}';
    }
}