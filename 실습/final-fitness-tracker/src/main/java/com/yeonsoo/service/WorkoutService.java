package com.yeonsoo.service;

import com.yeonsoo.domain.ExerciseType;
import com.yeonsoo.domain.Workout;
import com.yeonsoo.storage.WorkoutRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class WorkoutService {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private WorkoutRepository workoutRepository;


    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> findAllWorkouts() {
        return workoutRepository.selectAllWorkouts();
    }

    public Workout findWorkoutByDate(String date) {
        return workoutRepository.selectWorkoutByDate(date);
    }

    public Workout findWorkoutByNo(int no) {
        return workoutRepository.selectWorkoutByNo(no);
    }

    public void registerWorkout(Workout workout) {
        if (isDuplicateWorkout(workout.getDate(), workout.getExerciseType())) {
            throw new IllegalArgumentException("등록 실패: 같은 날짜에 이미 같은 운동이 기록되어 있습니다.");
        }

        try {
            Date workoutDate = sdf.parse(workout.getDate()); // 입력한 날짜 가져오기
            Date todayDate = sdf.parse(sdf.format(new Date())); // 현재 날짜 가져오기

            if (workoutDate.after(todayDate)) {
                throw new IllegalArgumentException("등록 실패: 오늘 이후 날짜는 입력할 수 없습니다.");
            }

            workoutRepository.insertWorkout(workout);

        } catch (ParseException e) {
            throw new IllegalArgumentException("날짜 형식이 올바르지 않습니다. yyyy-MM-dd 형식으로 입력해주세요.");
        }
    }

    private boolean isDuplicateWorkout(String date, ExerciseType exerciseType) {
        return workoutRepository.selectAllWorkouts()
                .stream()
                .anyMatch(w -> w.getDate().equals(date) && w.getExerciseType().equals(exerciseType));
    }

    public void removeWorkout(int no) {
        Workout existingWorkout = workoutRepository.selectWorkoutByNo(no);
        if (existingWorkout == null) {
            throw new IllegalArgumentException("삭제 실패: 운동을 찾을 수 없습니다.");
        }

        workoutRepository.deleteWorkout(no);
    }

    public void modifyWorkout(Workout updateWorkout) {
        Workout existingWorkout = workoutRepository.selectWorkoutByNo(updateWorkout.getNo());
        if (existingWorkout == null) {
            throw new IllegalArgumentException("수정 실패: 해당 날짜의 운동 기록이 존재하지 않습니다.");
        }

        // 운동 시간은 0분 이상이어야함
        if (updateWorkout.getDuration() <= 0) {
            throw new IllegalArgumentException("수정 실패: 운동 시간은 0보다 커야 합니다.");
        }

        // 오늘 이후 날짜로 수정 금지
        if (isFutureDate(updateWorkout.getDate())) {
            throw new IllegalArgumentException("수정 실패: 미래 날짜의 운동 기록은 수정할 수 없습니다.");
        }

        workoutRepository.updateWorkout(updateWorkout);
    }

    private boolean isFutureDate(String dateStr) {
        LocalDate today = LocalDate.now();
        LocalDate workoutDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return workoutDate.isAfter(today);
    }

}

