package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class drone {

    private enum droneState {

        CHARGED,
        NOT_CHARGED,
    }

    private double CHARGED_POSITION = 0.5;
    private double NOT_CHARGED_POSITION = 1.0;
    public droneState currentState = droneState.CHARGED;
    public Servo droneServo;

    public drone(HardwareMap hardwareMap) {
        droneServo = hardwareMap.get(Servo.class, "drone_servo");
    }

    public void setStateCharged() {
        currentState = droneState.CHARGED;
    }

    public void setStateNotCharged() {
        currentState = droneState.NOT_CHARGED;
    }

    public void updateState() {
        switch (currentState) {
            case CHARGED:
                droneServo.setPosition(CHARGED_POSITION);
                break;
            case NOT_CHARGED:
                droneServo.setPosition(NOT_CHARGED_POSITION);
                break;
        }
    }

    public void displayDroneState(Telemetry telemetry) {
        telemetry.addData("Drone State", currentState);
    }

    public void displayDroneServoPosition(Telemetry telemetry) {
        telemetry.addData("Drone Servo Position", droneServo.getPosition());
    }

    public void displayDroneServoDirection(Telemetry telemetry) {
        telemetry.addData("Drone Servo Direction", droneServo.getDirection());
    }




}
