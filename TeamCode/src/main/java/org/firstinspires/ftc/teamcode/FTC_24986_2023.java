package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.drone;
import org.firstinspires.ftc.teamcode.subsystems.robotArm;
import org.firstinspires.ftc.teamcode.subsystems.robotLift;
import org.firstinspires.ftc.teamcode.subsystems.intake;

@TeleOp (name = "MainDrive")
public class FTC_24986_2023 extends LinearOpMode {

    private robotArm robotArm;
    private robotLift robotLift;
    private org.firstinspires.ftc.teamcode.subsystems.intake intake;
    private drone drone;


    @Override
    public void runOpMode() throws InterruptedException {

        robotArm = new robotArm(hardwareMap);
        robotLift = new robotLift(hardwareMap);
        intake = new intake(hardwareMap);
        drone = new drone(hardwareMap);


        robotArm.setStateStart();
        robotLift.setStateStart();
        intake.setStateStop();
        drone.setStateCharged();

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        gamepad1 = new Gamepad();
        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Keep y-axis as is
            double x = gamepad1.left_stick_x * 1.12; // Assign left stick's x-axis to x for strafing
            double rx = -gamepad1.right_stick_x; // Invert right stick's x-axis and assign it to rx for in-place turning

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = y + x + rx;
            double backLeftPower = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower = y + x - rx;

// Set min and max power limits
            double minPower = -1.0;
            double maxPower = 1.0;

// Clamp motor powers to min and max values
            frontLeftPower = clamp(frontLeftPower, minPower, maxPower);
            backLeftPower = clamp(backLeftPower, minPower, maxPower);
            frontRightPower = clamp(frontRightPower, minPower, maxPower);
            backRightPower = clamp(backRightPower, minPower, maxPower);

// Set motor powers
            drive.leftFront.setPower(frontLeftPower);
            drive.leftBack.setPower(backLeftPower);
            drive.rightFront.setPower(frontRightPower);
            drive.rightBack.setPower(backRightPower);

            if (gamepad2.dpad_down) {
                robotArm.setStateIntake();
            }
            if (gamepad2.dpad_left) {
                robotArm.setStateTransfer();
                robotLift.setStateTransfer();
            }
            if (gamepad2.dpad_up) {
                robotLift.setStateScore();
            }
            if (gamepad2.dpad_right){
                robotArm.setStateStart();
            }
            if(gamepad2.x){
                intake.intakeMotor.setPower(1);
            }
            if (gamepad2.y){
                intake.intakeMotor.setPower(-1);
            }
            if(gamepad2.a){
                robotArm.setStateStart();
            }
            if(gamepad2.b) {
                intake.intakeMotor.setPower(0);
            }
            if (gamepad2.right_stick_button && gamepad2.left_stick_button ){
                    drone.setStateNotCharged();
            }
            if (gamepad2.dpad_right){
                    robotLift.setStateTransfer();
            }

            robotArm.updateState();
            robotLift.updateState();

            telemetry.addData("leftFront: ", drive.rightBack.getCurrentPosition());
            telemetry.addData("rightFront: ", drive.rightFront.getCurrentPosition());
            robotArm.telemetryElbowPositions(telemetry);
            intake.displayIntakeState(telemetry);
            robotArm.telemetryArmState(telemetry);
            telemetry.update();
            idle();

        }
    }

    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
}