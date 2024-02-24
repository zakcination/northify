package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class robotArm {

    private enum ArmState {
        INTAKE,
        TRANSFER,
        START,
        IDLE
    }

    private ArmState currentState = ArmState.IDLE;
    private ArmState prevState = ArmState.IDLE;

    public DcMotorEx shoulderMotor;
    private final Servo[] elbowServos;


    private static final double SHOULDER_VELOCITY = 0.7; // Adjust as needed
    private static final double ELBOW_VELOCITY = 1000; // Adjust as needed

    private static final double START_SHOULDER_POSITION = 0;
    private static final double[] START_ELBOW_POSITIONS = {-1, -1};
    private static final double INTAKE_SHOULDER_POSITION = 95; // Adjust as needed
    private static final double[] INTAKE_ELBOW_POSITIONS = {0.6, 0.6}; // Adjust as needed

    private static final double TRANSFER_SHOULDER_POSITION = 25;
    private static final double[] TRANSFER_ELBOW_POSITIONS = {1, 1};

    private static final double PID_Coefficient_shoulder = 90;

    public robotArm(HardwareMap hardwareMap) {
        shoulderMotor = hardwareMap.get(DcMotorEx.class, "shoulder_motor");
        elbowServos = new Servo[2];
        elbowServos[0] = hardwareMap.get(Servo.class, "elbow_servo_left");
        elbowServos[1] = hardwareMap.get(Servo.class, "elbow_servo_right");
        elbowServos[1].setDirection(Servo.Direction.REVERSE);

        shoulderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shoulderMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shoulderMotor.setPositionPIDFCoefficients(PID_Coefficient_shoulder);
        int armShutdownThreshold = 1;
        boolean manualMode = false;
//        if (!manualMode &&
//                shoulderMotor.getMode() == DcMotor.RunMode.RUN_TO_POSITION &&
//                shoulderMotor.getTargetPosition() <= armShutdownThreshold &&
//                shoulderMotor.getCurrentPosition() <= armShutdownThreshold
//        ) {
//            shoulderMotor.setPower(0);
//            shoulderMotor.setPower(0);
//            shoulderMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            shoulderMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        }
    }
    public void setStateIntake() {

        currentState = ArmState.INTAKE;
    }
    public void setStateTransfer() {
        prevState = currentState;
        currentState = ArmState.TRANSFER;
    }
    public void setStateStart(){

        prevState = currentState;
        currentState = ArmState.START;
    }
    public void setStateIdle(){

        prevState = currentState;
        currentState = ArmState.IDLE;
    }
    public void updateState() {
        switch (currentState) {
            case INTAKE:
                setElbowPositions(INTAKE_ELBOW_POSITIONS);
                setShoulderPosition(INTAKE_SHOULDER_POSITION, 0.4);
                break;
            case TRANSFER:
                setElbowPositions(TRANSFER_ELBOW_POSITIONS);
                setShoulderPosition(TRANSFER_SHOULDER_POSITION, 0.7);
                break;
            case START:
                setElbowPositions(START_ELBOW_POSITIONS);
                setShoulderPosition(START_SHOULDER_POSITION, 0.7);
                break;
                case IDLE:
                shoulderMotor.setPower(0);
                break;
        }
    }

    public void setStateIntakePosition(){
        setElbowPositions(INTAKE_ELBOW_POSITIONS);

    }

    public void setShoulderPosition(double position) {
        shoulderMotor.setTargetPosition((int) position);
        shoulderMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        shoulderMotor.setPower(0.7);
    }

    public void setShoulderPosition(double position, double power) {
        shoulderMotor.setTargetPosition((int) position);
        shoulderMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        shoulderMotor.setPower(power);
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
    public void setElbowStartPosition(){
        for (int i = 0; i < elbowServos.length; i++){
            elbowServos[i].setPosition(START_ELBOW_POSITIONS[i]);
        }
    }

    public void setShoulderIntakePosition(){
        setShoulderPosition(INTAKE_SHOULDER_POSITION);
    }
    public void setShoulderStartPosition(){
        setShoulderPosition(START_SHOULDER_POSITION);
    }

    public void setShoulderTransferPosition() {
        setShoulderPosition(TRANSFER_SHOULDER_POSITION);
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


    public void telemetryElbowPositions(Telemetry telemetry) {
        double[] elbowPositions = getElbowsPositions();

        for (int i = 0; i < elbowPositions.length; i++) {
            telemetry.addData("Elbow " + i + " Position", elbowPositions[i]);
        }
    }
    public void telemetryArmState(Telemetry telemetry) {
        telemetry.addData("Arm State", currentState.toString());
        telemetry.addData("Shoulder Position", shoulderMotor.getCurrentPosition());
    }

    //    TODO: if needed change name of the void
    //          head it is intake

    // Set position methods for intake and transfer positions
}
