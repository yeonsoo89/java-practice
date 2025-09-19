package com.yeonsoo.storage;

import com.yeonsoo.domain.Workout;

import java.util.List;

public interface WorkOutStorage {
    void saveWorkouts(List<Workout> workouts);
    List<Workout> loadWorkouts();
}
