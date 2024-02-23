package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class robotLift {
    public DcMotorEx liftMotor;
    public ServoImplEx outtakeServo;

    private static final double START_LIFT_POSITION = 0;
    private static final double SCORING_LIFT_POSITION = 1000;
    private static final double PID_Coefficient_lift = 15;

    private static final double LIFT_VELOCITY = 1.0; // Adjust as needed
    private static final double LIFT_POWER = 1.0; // Adjust as needed

    private static final double SCORING_SERVO_POSITION = 0.4;
    private static final double SHOOTER_SERVO_POSITION = 0.1;
    private static final double TRANSFER_SERVO_POSITION = 0.00;
    private static final double START_SERVO_POSITION = 0;


    private enum OuttakeState {
        START,
        SCORE,
        TRANSFER,
        SHOOT
    }

    private enum LiftState {
        START,
        SCORE
    }

    public LiftState currentState = LiftState.START;
    public OuttakeState currentOuttakeState = OuttakeState.START;

    public robotLift(HardwareMap hardwareMap) {
        liftMotor = hardwareMap.get(DcMotorEx.class, "lift_motor");
        liftMotor.setPositionPIDFCoefficients(PID_Coefficient_lift);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        outtakeServo = hardwareMap.get(ServoImplEx.class, "outtake_servo");
    }

    public void setStateShoot() {
        currentState = LiftState.SCORE;
        currentOuttakeState = OuttakeState.SHOOT;
    }

    public void setStateScore() {
        currentState = LiftState.SCORE;
        currentOuttakeState = OuttakeState.SCORE;
    }

    public void setStateStart() {
        currentState = LiftState.START;
        currentOuttakeState = OuttakeState.START;
    }

    public void setStateTransfer() {
        currentState = LiftState.START;
        currentOuttakeState = OuttakeState.TRANSFER;
    }
    public void setLiftPosition(double position) {
        liftMotor.setTargetPosition((int) position);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setPower(LIFT_POWER);
    }

    public void setOuttakePosition(double position) {
        outtakeServo.setPosition(position);
    }

    public void updateState() {
        switch (currentState) {
            case START:
                setLiftStartPosition();
                break;
            case SCORE:
                setLiftScoringPosition();
                break;
        }

        switch (currentOuttakeState) {
            case START:
                setOuttakePosition(START_SERVO_POSITION);
                break;
            case SCORE:
                setOuttakePosition(SCORING_SERVO_POSITION);
                break;
            case TRANSFER:
                setOuttakePosition(TRANSFER_SERVO_POSITION);
                break;
            case SHOOT:
                setOuttakePosition(SHOOTER_SERVO_POSITION);
                break;
        }
    }

    public void setLiftPower(double power) {
        liftMotor.setPower(power);
    }

    public void setLiftStartPosition() {
        setLiftPosition(START_LIFT_POSITION);
    }

    public void setLiftScoringPosition() {
        setLiftPosition(SCORING_LIFT_POSITION);
    }

    public int getLiftPosition() {
        return liftMotor.getCurrentPosition();
    }

    public void telemetryLiftState(TelemetryPacket packet) {
        packet.put("Lift State", currentState);
    }

    public void telemetryOuttakeState(TelemetryPacket packet) {
        packet.put("Outtake State", currentOuttakeState);
    }
}

