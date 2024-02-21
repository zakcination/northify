package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "LiftOpMode")
public class LiftOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        robotLift lift = new robotLift(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            // Check gamepad input to trigger shooter spin-up
            if (gamepad1.a) { // Adjust the threshold according to your gamepad sensitivity
                Actions.runBlocking(robotLift.spinUp());
            }

            // Add any other gamepad controls or robot actions here

            // Update telemetry if needed
            int shooterMotorPosition = robotLift.liftMotor.getCurrentPosition();

            // Update telemetry with shooter motor's position
            telemetry.addData("Shooter Motor Position", shooterMotorPosition);
            telemetry.update();
        }

        Actions.runBlocking(robotLift.spinUp()); // Call spinUp statically
    }
}
