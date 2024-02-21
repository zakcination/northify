package org.firstinspires.ftc.teamcode.tests;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.robotArm;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "RobotArmTest", group = "Tests")
public class robotArmTest extends LinearOpMode{
    private org.firstinspires.ftc.teamcode.subsystems.robotArm robotArm;
    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode(){
        robotArm = new robotArm(hardwareMap); // Initialize the robot arm subsystem
        gamepad1 = new Gamepad();
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.b) {
                robotArm.headAndShoulders_INTAKE();
                robotArm.updateState();
            }
            if(gamepad1.y){
                robotArm.headAndShoulders_TRANSFER();
                robotArm.updateState();
            }
            if (gamepad1.a) {
                testElbowsIntakePosition();
            }
            if(gamepad1.x){
                testElbowsTransferPosition();
            }
            if(gamepad1.right_bumper){
                testIntakeStop();
            }
            if (gamepad1.dpad_down){
                testShoulderIntakePosition();
            }
            if(gamepad1.dpad_up){
                testShoulderTransferPosition();
            }
            if(gamepad1.left_bumper){
                testShoulderHomePosition();
            }



//            testIntake(); // Test intake operation
//            testReverseIntake(); // Test reverse intake operation
//            testElbowsIntakePosition(); // Test elbows intake position
//            testElbowsTransferPosition(); // Test elbows transfer position
//            testShoulderIntakePosition(); // Test shoulder intake position
//            testShoulderTransferPosition(); // Test shoulder transfer position
            displayEncoderValues(); // Display encoder values for telemetry

            idle();
        }
    }



    private void testIntake() {
        robotArm.intake();
    }

    private void testReverseIntake() {
        robotArm.reverseIntake();
    }
    private void testIntakeStop(){
        robotArm.stopIntake();
    }

    // servo right 1-0.4
    private void testElbowHomePosition(){

    }

    private void testElbowsIntakePosition() {
        // Set the elbows to intake position
        // Implement the logic according to your requirement
        sleep(500);
        robotArm.setElbowsIntakePosition();
    }

    private void testElbowsTransferPosition() {
        // Set the elbows to transfer position
        // Implement the logic according to your requirement
        sleep(500);
        robotArm.setElbowsTransferPosition();
    }

    private void testShoulderIntakePosition() {
        // Set the shoulder to intake position
        // Implement the logic according to your requirement
        robotArm.setShoulderIntakePosition();
    }
    private void testShoulderHomePosition() {
        robotArm.setShoulderHomePosition();
    }

    private void testShoulderTransferPosition() {
        // Set the shoulder to transfer position
        // Implement the logic according to your requirement
        robotArm.setShoulderTransferPosition();
    }


    private void displayEncoderValues() {
        telemetry.addData("Shoulder Encoder", robotArm.getShoulderPosition());
        robotArm.telemetryElbowPositions(telemetry);
        robotArm.telemetryIntakeState(telemetry);
        robotArm.telemetryArmState(telemetry);
        // Add telemetry for elbows and intake as needed
        telemetry.update();
    }
}
