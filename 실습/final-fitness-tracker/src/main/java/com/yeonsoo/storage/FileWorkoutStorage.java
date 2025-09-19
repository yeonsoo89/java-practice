package com.yeonsoo.storage;

import com.yeonsoo.domain.Workout;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWorkoutStorage implements WorkOutStorage {
    private static final String FILE_PATH = "src/main/java/com/yeonsoo/db/workoutDB.dat";

    @Override
    public void saveWorkouts(List<Workout> workouts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(workouts);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류 발생", e);
        }
    }

    @Override
    public List<Workout> loadWorkouts() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Workout>) ois.readObject();
        } catch (EOFException e) {
            System.out.println("운동 정보를 모두 로딩하였습니다.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("파일 로딩 중 오류 발생", e);
        }
    }
}
