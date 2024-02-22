package org.firstinspires.ftc.teamcode.tests;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.intake;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "IntakeTest", group = "Tests")
public class intakeTest extends LinearOpMode{
    private org.firstinspires.ftc.teamcode.subsystems.intake intake;


    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode(){
        intake = new intake(hardwareMap); // Initialize the intake subsystem
        gamepad1 = new Gamepad();
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                intake.setStateIn();
            }
            if(gamepad1.x){
                intake.setStateTransfer();
            }
            if(gamepad1.b){
                intake.setStateStop();
            }
            intake.updateState();

            telemetry.addData("Intake State", intake.currentState);
            intake.displayIntakeState(telemetry); // Display intake state for telemetry
            idle();
        }
    }

}
