package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.robotArm;
import org.firstinspires.ftc.teamcode.subsystems.robotLift;

@TeleOp(name = "RobotLiftTest", group = "Tests")

public class robotLiftTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        while (opModeIsActive()) {
            // Get input from the gamepad to control the shooter motor
            double power = gamepad1.right_trigger; // Example: Use right trigger to control power

            // Control the shooter motor based on gamepad input
//            robotLift.setPower(power);

            // Optionally, you can add telemetry to display shooter velocity or any other information
            telemetry.addData("Shooter Power", power);
            telemetry.update();

            // Add a short delay to prevent the loop from running too fast
            sleep(50);
        }
    }
}

//public class robotLiftTest1 extends LinearOpMode {
//    private org.firstinspires.ftc.teamcode.subsystems.robotArm robotArm;
//    private final ElapsedTime runtime = new ElapsedTime();
//
//    @Override
//    public void runOpMode(){
//        robotArm = new robotArm(hardwareMap); // Initialize the robot arm subsystem
//        gamepad1 = new Gamepad();
//        telemetry.update();
//        waitForStart();
//
//        while (opModeIsActive()) {
//            if (gamepad1.y) {}
//            if (gamepad1.a) {}
//            if (gamepad1.x) {}
//            if (gamepad1.b) {}
//            if (gamepad1.right_bumper) {}
//            if (gamepad1.left_bumper) {}
//            if (gamepad1.dpad_down) {}
//            if (gamepad1.dpad_up) {}
//            if (gamepad1.dpad_left) {}
//            if (gamepad1.dpad_right) {}
//            telemetry.update();
//        }
//    }
//}
