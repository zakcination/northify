package org.firstinspires.ftc.teamcode.tests;

import org.firstinspires.ftc.teamcode.subsystems.drone;
import org.firstinspires.ftc.teamcode.subsystems.robotLift;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "droneTest", group = "Tests")
public class droneTest extends LinearOpMode{
    private org.firstinspires.ftc.teamcode.subsystems.drone drone;
    private org.firstinspires.ftc.teamcode.subsystems.robotLift robotLift;
    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode(){
        drone = new drone(hardwareMap); // Initialize the drone subsystem
        robotLift = new robotLift(hardwareMap);
        gamepad1 = new Gamepad();
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                drone.setStateCharged();
            }
            if(gamepad1.x){
                robotLift.setStateShoot();
                drone.setStateNotCharged();
            }
            robotLift.updateState();
            drone.updateState();


            telemetry.addData("Drone State", drone.currentState);
            telemetry.addData("Drone Servo Position", drone.droneServo.getPosition());

            telemetry.update();

            drone.displayDroneState(telemetry); // Display drone state for telemetry
            idle();
        }
    }

}
