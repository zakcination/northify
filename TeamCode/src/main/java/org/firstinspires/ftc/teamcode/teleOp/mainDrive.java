package org.firstinspires.ftc.teamcode.teleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@TeleOp (name = "MainDrive")
public class mainDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        double  min = -1;
        double  max = 1;
        boolean hanger = false;
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = gamepad1.left_stick_y; // Keep y-axis as is
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



            if(gamepad1.dpad_up){
                drive.rightFront.setPower(0.75);
            }
            if(gamepad1.dpad_down){
                drive.leftFront.setPower(0.75);
            }
            if(gamepad1.dpad_left){
                drive.leftBack.setPower(0.75);
            }
            if(gamepad1.dpad_right){
                drive.rightBack.setPower(0.75);
            }

            else if(!hanger){
            }
            if(gamepad1.left_bumper){
            }
            if(gamepad1.right_bumper) {
            }

            telemetry.addData("leftFront: ", drive.leftBack.getCurrentPosition());
            telemetry.addData("rightFront: ", drive.rightFront.getCurrentPosition());
            telemetry.update();
        }
    }
    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
}