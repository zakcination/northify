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

    private org.firstinspires.ftc.teamcode.subsystems.robotLift robotLift;
    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robotLift = new robotLift(hardwareMap); // Initialize the robot lift subsystem

        gamepad1 = new Gamepad();
        telemetry.update();


        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                robotLift.setStateScore();
            }
            if (gamepad1.dpad_down) {
                robotLift.setStateTransfer();
            }

            robotLift.updateState();

            telemetry.addData("Lift State", robotLift.currentState);
            telemetry.addData("Lift Encoder", robotLift.liftMotor.getCurrentPosition());
            telemetry.addData("Outtake State", robotLift.currentOuttakeState);
            telemetry.update();
        }
    }

    private void testLiftScorePosition() {
        robotLift.setStateScore();
        sleep(1000);
        robotLift.setStateStart();
    }

    private void testLiftTransferPosition() {
        robotLift.setStateTransfer();
        sleep(1000);
        robotLift.setStateStart();
    }

    private void testLiftStartPosition() {
        robotLift.setStateStart();
    }

    private void displayEncoderValues() {
        telemetry.addData("Lift Encoder", robotLift.liftMotor.getCurrentPosition());
    }
}
