package com.yeonsoo.storage;

import com.yeonsoo.domain.Workout;

import java.util.ArrayList;
import java.util.List;

/* 운동 데이터 저장 및 조회 */
public class WorkoutRepository {
    private final WorkOutStorage workoutStorage;
    private final List<Workout> workoutList;

    public WorkoutRepository(WorkOutStorage workoutStorage) {
        this.workoutStorage = workoutStorage;
        this.workoutList = workoutStorage.loadWorkouts();
    }

    public List<Workout> selectAllWorkouts() { return new ArrayList<>(workoutList); }

    // 날짜로 운동 찾기
    public Workout selectWorkoutByDate(String Date) {
        return workoutList.stream()
                .filter(w -> w.getDate().equals(Date))
                .findFirst().orElse(null);
    }

    // 운동 번호로 운동 찾기
    public Workout selectWorkoutByNo(int no) {
        return workoutList.stream()
                .filter(w -> w.getNo() == no)
                .findFirst().orElse(null);
    }

    public void insertWorkout(Workout workout) {
        workoutList.add(workout);
        workoutStorage.saveWorkouts(workoutList);
    }

    public void deleteWorkout(int no) {
        workoutList.removeIf(w -> w.getNo() == no);
        workoutStorage.saveWorkouts(workoutList);
    }

    public void updateWorkout(Workout updatedWorkout) {
        for (int i = 0; i < workoutList.size(); i++) {
            if (workoutList.get(i).getNo() == updatedWorkout.getNo()) {
                workoutList.set(i, updatedWorkout);
                workoutStorage.saveWorkouts(workoutList);
                break;
            }
        }
    }


}
