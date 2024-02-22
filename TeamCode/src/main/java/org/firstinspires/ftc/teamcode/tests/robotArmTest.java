package org.firstinspires.ftc.teamcode.tests;

import org.firstinspires.ftc.teamcode.subsystems.intake;
import org.firstinspires.ftc.teamcode.subsystems.robotArm;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "RobotArmTest", group = "Tests")
public class robotArmTest extends LinearOpMode {
    private org.firstinspires.ftc.teamcode.subsystems.robotArm robotArm;
    private org.firstinspires.ftc.teamcode.subsystems.intake intake;
    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        robotArm = new robotArm(hardwareMap);
        intake = new intake(hardwareMap);// Initialize the robot arm subsystem
        gamepad1 = new Gamepad();
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                robotArm.setStateIntake();
            }
            if (gamepad1.x) {
                robotArm.setStateTransfer();
            }
            if (gamepad1.b) {
                robotArm.setStateStart();
            }
            if (gamepad1.dpad_down) {
                intake.setStateIn();
            }
            if (gamepad1.dpad_up) {
                intake.setStateTransfer();
            }
            if (gamepad1.dpad_right) {
                intake.setStateStop();
            }
            intake.updateState();

            robotArm.updateState();
            robotArm.telemetryArmState(telemetry);
            robotArm.telemetryElbowPositions(telemetry);
            telemetry.update();
        }
    }








    private void displayEncoderValues() {
        telemetry.addData("Shoulder Encoder", robotArm.getShoulderPosition());
        robotArm.telemetryElbowPositions(telemetry);
        robotArm.telemetryArmState(telemetry);
        // Add telemetry for elbows and intake as needed
        telemetry.update();
    }
}