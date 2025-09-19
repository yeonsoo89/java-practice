package com.yeonsoo.domain;

public enum ExerciseType {
    Shoulder, Chest, Leg, Back, Arm, Run;

    public static ExerciseType fromString(String type) {
        return switch (type.toUpperCase()) {
            case "S" -> Shoulder;
            case "C" -> Chest;
            case "L" -> Leg;
            case "B" -> Back;
            case "A" -> Arm;
            case "R" -> Run;
            default -> throw new IllegalArgumentException("Invalid ExerciseType: " + type);
        };
    }
}
