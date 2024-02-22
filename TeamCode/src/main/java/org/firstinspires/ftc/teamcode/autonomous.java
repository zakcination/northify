//package org.firstinspires.ftc.teamcode;
//
//
//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.roadrunner.Pose2d;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
//
//@Config
//@Autonomous(name = "autonomous", group = "drive")
//public class autonomous extends LinearOpMode {
//    public static double MAX_POWER = 0.7;
//    public static double DISTANCE = 100; // in
//    MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
//    public void runOpMode() throws InterruptedException {
//        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
//        waitForStart();
//        drive.followTrajectory(drive.trajectoryBuilder(new Pose2d(0, 0, 0))
//                .splineTo(new Pose2d(30, 30, Math.PI / 2))
//                .splineTo(new Pose2d(0, 60, Math.PI))
//                .build());
//    }
//}