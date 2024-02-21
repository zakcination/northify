package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class robotArm {

    private enum ArmState {
        INTAKE,
        TRANSFER,
        HOME
    }

    private boolean isIntakeReversed = false;
    private ArmState currentState = ArmState.INTAKE;

    public DcMotorEx shoulderMotor;
    private Servo[] elbowServos;
    public DcMotorEx intakeMotor;

    private static final double SHOULDER_VELOCITY = 0.7; // Adjust as needed
    private static final double ELBOW_VELOCITY = 1000; // Adjust as needed
    private static final double INTAKE_VELOCITY = 1.0; // Adjust as needed
    private static final double INTAKE_POWER = 1.0; // Adjust as needed

    private static final double HOME_SHOULDER_POSITION = 0;
    private static final double[] HOME_ELBOW_POSITIONS = {-0.5, 0.5};

    private static final double INTAKE_SHOULDER_POSITION = 35; // Adjust as needed
    private static final double[] INTAKE_ELBOW_POSITIONS = {0, 1}; // Adjust as needed

    private static final double TRANSFER_SHOULDER_POSITION = 26;
    private static final double[] TRANSFER_ELBOW_POSITIONS = {1, 0};

    private static final double PID_Coefficient_shoulder = 99;

    public robotArm(HardwareMap hardwareMap) {
        shoulderMotor = hardwareMap.get(DcMotorEx.class, "shoulder_motor");
        elbowServos = new Servo[2];
        elbowServos[0] = hardwareMap.get(Servo.class, "elbow_servo_left");
        elbowServos[1] = hardwareMap.get(Servo.class, "elbow_servo_right");
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake_motor");


        //intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        shoulderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shoulderMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shoulderMotor.setPositionPIDFCoefficients(PID_Coefficient_shoulder);
    }
    public void setStateIntake() {
        currentState = ArmState.INTAKE;
    }
    public void setStateTransfer() {
        currentState = ArmState.TRANSFER;
    }
    public void setStateHome(){
        currentState = ArmState.HOME;
    }
    public void updateState() {
        switch (currentState) {
            case INTAKE:
                setShoulderPosition(INTAKE_SHOULDER_POSITION);
                setElbowPositions(INTAKE_ELBOW_POSITIONS);
                break;
            case TRANSFER:
                setShoulderPosition(TRANSFER_SHOULDER_POSITION);
                setElbowPositions(TRANSFER_ELBOW_POSITIONS);
                break;
            case HOME:
                setShoulderPosition(HOME_SHOULDER_POSITION);
                setElbowPositions(HOME_ELBOW_POSITIONS);
                break;
        }
    }

    public void setShoulderPosition(double position) {
        shoulderMotor.setTargetPosition((int) position);
        shoulderMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        shoulderMotor.setPower(0.7);
    }
    private void setElbowPositions(double[] positions) {
        for (int i = 0; i < elbowServos.length; i++) {
            elbowServos[i].setPosition(positions[i]);
        }
    }
    public void setElbowsIntakePosition(){
        for (int i = 0; i < elbowServos.length; i++) {
            elbowServos[i].setPosition(INTAKE_ELBOW_POSITIONS[i]);
        }
    }
    public void setElbowsTransferPosition(){
        for (int i = 0; i < elbowServos.length; i++) {
            elbowServos[i].setPosition(TRANSFER_ELBOW_POSITIONS[i]);
        }
    }
    public void setElbowHomePosition(){
        for (int i = 0; i < elbowServos.length; i++){
            elbowServos[i].setPosition(HOME_ELBOW_POSITIONS[i]);
        }
    }

    public void setShoulderIntakePosition(){
        setShoulderPosition(INTAKE_SHOULDER_POSITION);
    }
    public void setShoulderHomePosition(){
        setShoulderPosition(HOME_SHOULDER_POSITION);
    }

    public void setShoulderTransferPosition() {
        setShoulderPosition(TRANSFER_SHOULDER_POSITION);
    }
    public void stopIntake() {
        intakeMotor.setVelocity(0);
    }

    public void intake() {
        // Implement intake logic as needed
        intakeMotor.setPower(INTAKE_POWER);
        isIntakeReversed = false;
    }

    public void reverseIntake() {
        // Implement reverse intake logic as needed
        intakeMotor.setPower(-INTAKE_POWER);
        isIntakeReversed = true;
    }

    public double getShoulderPosition() {
        return shoulderMotor.getCurrentPosition();
    }

    public double[] getElbowsPositions() {
        double[] positions = new double[elbowServos.length];
        for (int i = 0; i < elbowServos.length; i++) {
            positions[i] = elbowServos[i].getPosition();
        }
        return positions;
    }

    public boolean isIntakeReversed() {
        return isIntakeReversed;
    }
    public void telemetryElbowPositions(Telemetry telemetry) {
        double[] elbowPositions = getElbowsPositions();

        for (int i = 0; i < elbowPositions.length; i++) {
            telemetry.addData("Elbow " + i + " Position", elbowPositions[i]);
        }
    }

    public void telemetryIntakeState(Telemetry telemetry) {
        telemetry.addData("Intake State", isIntakeReversed ? "REVERSED" : "NORMAL");
    }
    public void telemetryArmState(Telemetry telemetry) {
        telemetry.addData("Arm State", currentState.toString());
    }

    //    TODO: if needed change name of the void
    //          head it is intake
    public void headAndShoulders_INTAKE(){
        setShoulderIntakePosition();
        setStateIntake();
        setElbowsIntakePosition();
        intake();
    }
    public void headAndShoulders_TRANSFER(){
        setStateTransfer();
        setShoulderTransferPosition();
        setElbowsTransferPosition();
    }

    // Set position methods for intake and transfer positions
}
