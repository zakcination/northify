package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;



public class intake {


    private enum IntakeState {
        IN,
        TRANSFER,
        STOP
    }

    public DcMotorEx intakeMotor;
    public IntakeState currentState = IntakeState.STOP;

    private static final double INTAKE_VELOCITY = 1.0; // Adjust as needed
    private static final double INTAKE_POWER = 1.0; // Adjust as needed
    private static final double PID_Coefficient_intake = 150;
    public intake(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake_motor");
        intakeMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        intakeMotor.setPositionPIDFCoefficients(150);
    }

    public void setStateStop() {
        currentState = IntakeState.STOP;
    }

    public void setStateIn() {
        currentState = IntakeState.IN;
    }

    public void setStateTransfer() {
        currentState = IntakeState.TRANSFER;

    }

    public void updateState() {
        switch (currentState) {
            case IN:
                intakeMotor.setPower(INTAKE_POWER);
                break;
            case TRANSFER:
                intakeMotor.setPower(-INTAKE_POWER);
            case STOP:
                intakeMotor.setPower(0);
                break;
        }
    }

    public void displayIntakeState(Telemetry telemetry) {
        telemetry.addData("Intake State", currentState);
    }

}
