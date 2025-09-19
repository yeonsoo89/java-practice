package com.yeonsoo.ui;

import com.yeonsoo.domain.ExerciseType;
import com.yeonsoo.domain.Workout;
import com.yeonsoo.service.WorkoutService;
import com.yeonsoo.storage.FileWorkoutStorage;
import com.yeonsoo.storage.WorkoutRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Application {
    private final WorkoutService workoutService;
    private final Scanner scanner;
    private ExerciseType ExerciseType;

    public Application() {
        WorkoutRepository workoutRepository = new WorkoutRepository(new FileWorkoutStorage());
        this.workoutService = new WorkoutService(workoutRepository);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n===== FITNESS TRACKER | 운동 관리 프로그램 =====");
            System.out.println("1. 모든 운동 기록 조회");
            System.out.println("2. 운동 기록 검색(운동명/날짜)");
            System.out.println("3. 운동 기록 추가");
            System.out.println("4. 운동 기록 삭제");
            System.out.println("5. 운동 기록 수정");
            System.out.println("6. 운동 통계 조회 (날짜별)");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> showAllWorkouts();
                    case 2 -> findWorkoutByDate();
                    case 3 -> registerWorkout();
                    case 4 -> removeWorkout();
                    case 5 -> modifyWorkout();
                    case 6 -> showWorkoutStatisticsByDate();
                    case 9 -> {
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                }
            } catch (Exception e) {
                System.out.println("오류 : " + e.getMessage());
            }
        }
    }

    public void showAllWorkouts() {
        workoutService.findAllWorkouts().forEach(System.out::println);
    }

    public void findWorkoutByDate() {
        System.out.print("조회할 운동 날짜 입력 (yyyy-MM-dd) : ");
        String date = scanner.next();
        scanner.nextLine();

        Workout workout = workoutService.findWorkoutByDate(date);
        if (workout != null) {
            System.out.println(workout);
        } else {
            System.out.println("해당 날짜의 운동을 찾을 수 없습니다.");
        }
    }

    public void registerWorkout() {
        try {
            System.out.print("날짜를 입력하세요. (yyyy-MM-dd) : ");
            String date = scanner.nextLine();

            System.out.print("운동 유형 입력하세요. (Shoulder -> S, Chest -> C, Leg -> L, Back -> B, Arm -> A, Run -> R) : ");
            ExerciseType exerciseType = ExerciseType.fromString(scanner.nextLine());

            System.out.print("운동 시간을 입력하세요. : ");
            int duration = scanner.nextInt();

            System.out.print("칼로리를 입력하세요. : ");
            int calories = scanner.nextInt();

            int nextWorkoutNo = workoutService.findAllWorkouts().size() + 1;
            Workout newWorkout = new Workout(nextWorkoutNo, date, exerciseType, duration, calories);

            workoutService.registerWorkout(newWorkout);
            System.out.println("운동 등록 성공!" + date + ", " + exerciseType + ", " + duration + "분, " + calories + "kcal");

        } catch (IllegalArgumentException e) {
            System.out.println("운동 등록 실패 : " + e.getMessage());
        }

    }

    public void removeWorkout() {
        try {
            System.out.print("삭제할 운동 번호 입력: ");
            int no = scanner.nextInt();
            scanner.nextLine();

            workoutService.removeWorkout(no);
            System.out.println("운동 삭제 완료)");

        } catch (IllegalArgumentException e) {
            System.out.println("운동 삭제 실패: " + e.getMessage());
        }
    }

    private void modifyWorkout() {
        try {
            // 1. 기존 운동 찾기
            System.out.print("수정할 운동 번호 입력: ");
            int no = scanner.nextInt();
            scanner.nextLine(); // nextInt 후 엔터 처리

            Workout existingWorkout = workoutService.findWorkoutByNo(no);
            if (existingWorkout == null) {
                System.out.println("해당 번호의 운동을 찾을 수 없습니다.");
                return;
            }

            System.out.println("수정할 정보를 입력하세요 (변경하지 않으려면 Enter 입력)");

            // 2. 날짜 수정
            System.out.print("새로운 날짜 (" + existingWorkout.getDate() + "): ");
            String date = scanner.nextLine();
            if (date.isEmpty()) date = existingWorkout.getDate();

            // 3. 운동 유형 수정
            System.out.print("새로운 운동 유형 (" + existingWorkout.getExerciseType() + "): ");
            String typeInput = scanner.nextLine();
            ExerciseType exerciseType = typeInput.isEmpty() ? existingWorkout.getExerciseType() : ExerciseType.fromString(typeInput);

            // 4. 운동 시간 수정
            System.out.print("새로운 운동 시간 (" + existingWorkout.getDuration() + "분): ");
            String durationInput = scanner.nextLine();
            int duration = durationInput.isEmpty() ? existingWorkout.getDuration() : Integer.parseInt(durationInput);

            // 5. 칼로리 수정
            System.out.print("새로운 칼로리 (" + existingWorkout.getCalories() + "kcal): ");
            String caloriesInput = scanner.nextLine();
            int calories = caloriesInput.isEmpty() ? existingWorkout.getCalories() : Integer.parseInt(caloriesInput);


            Workout updatedWorkout = new Workout(no, date, exerciseType, duration, calories);
            workoutService.modifyWorkout(updatedWorkout);
            System.out.println("운동 기록 수정 완료: " + updatedWorkout);

        } catch (IllegalArgumentException e) {
            System.out.println("운동 기록 수정 실패: " + e.getMessage());
        }
    }

    public void showWorkoutStatisticsByDate() {
        try {
            System.out.print("조회할 날짜를 입력하세요. (yyyy-MM-dd) : ");
            String date = scanner.nextLine();

            List<Workout> workouts = workoutService.findAllWorkouts()
                    .stream()
                    .filter(w -> w.getDate().equals(date))
                    .toList();

            if (workouts.isEmpty()) {
                System.out.println("해당 날짜에 운동 기록이 없습니다.");
                return;
            }

            int totalDuration = workouts.stream().mapToInt(Workout::getDuration).sum();
            int totalCalories = workouts.stream().mapToInt(Workout::getCalories).sum();

            System.out.println("\n===== 운동 통계 (" + date + ") =====");
            System.out.println("총 운동 시간: " + totalDuration + "분");
            System.out.println("총 칼로리 소모: " + totalCalories + "kcal");

        } catch (IllegalArgumentException e) {
            System.out.println("운동 조회 실패 : " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        new Application().run();
    }

}
