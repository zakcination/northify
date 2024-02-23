//package org.firstinspires.ftc.teamcode;
//
//import static org.firstinspires.ftc.teamcode.autonomous.spikeMark.CENTER;
//import static org.firstinspires.ftc.teamcode.autonomous.spikeMark.LEFT;
//import static org.firstinspires.ftc.teamcode.autonomous.spikeMark.RIGHT;
//
//import androidx.annotation.NonNull;
//
//import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
//import com.acmerobotics.roadrunner.Action;
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.SequentialAction;
//import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.acmerobotics.roadrunner.ftc.Actions;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.Gamepad;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
//import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
//import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
////import org.firstinspires.ftc.teamcode.opmode.auto.BlueCloseAuto;
//import org.firstinspires.ftc.teamcode.subsystems.Location;
//import org.firstinspires.ftc.teamcode.subsystems.drone;
//import org.firstinspires.ftc.teamcode.subsystems.teamElementDetection.aprilTagDetectionPipeline;
//import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
//import org.openftc.apriltag.AprilTagDetection;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//
//import java.util.ArrayList;
//
//@Autonomous(name = "camera")
//public class autonomous extends LinearOpMode {
//    private org.firstinspires.ftc.teamcode.subsystems.robotArm arm;
//    private org.firstinspires.ftc.teamcode.subsystems.intake intake;
//    private org.firstinspires.ftc.teamcode.subsystems.robotLift lift;
//    private org.firstinspires.ftc.teamcode.subsystems.drone drone;
//    public MecanumDrive drive = new MecanumDrive(hardwareMap, null);
//    enum Side{
//        Right,
//        Left
//    }
//    enum Alliance{
//        Red,
//        Blue
//    }
//    enum spikeMark{
//        CENTER,
//        LEFT,
//        RIGHT
//    }
//    OpenCvCamera camera;
//    AprilTagDetection tagOfInterest = null;
//    aprilTagDetectionPipeline aprilTagDetectionPipeline;
//
//    //static final double FEET_PER_METER = 3.28084;
//
//    // Lens intrinsics
//    // UNITS ARE PIXELS
//    // NOTE: this calibration is for the C920 webcam at 800x448.
//    // You will need to do your own calibration for other configurations!
//    double fx = 578.272;
//    double fy = 578.272;
//    double cx = 402.145;
//    double cy = 221.506;
//
//    // UNITS ARE METERS
//    double tagsize = 0.166;
//    Location loc;
//
//
//    // Tag ID 1,2,3 from the 36h11 family
//    int Left = 1;
//    int Middle = 2;
//    int Right = 3;
//    private final ElapsedTime runtime = new ElapsedTime();
//    public class Sleep implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet) {
//            intake.intakeMotor.setPower(0.5);
//            sleep(4000);
//            return false;
//        }
//    }
//    public class AprilTagAlign implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet) {
//            boolean tagFound = false;
//
//            aprilTagDetectionPipeline aprilTagDetectionPipeline;
//            aprilTagDetectionPipeline = new aprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
//
//            org.openftc.apriltag.AprilTagDetection tagOfInterest = null;
//
//            camera.setPipeline(aprilTagDetectionPipeline);
//
//            ArrayList<AprilTagDetection> currentDetections;
//            runtime.reset();
//            while (!tagFound && runtime.seconds() <= 5.0)
//            {
//                currentDetections = aprilTagDetectionPipeline.getLatestDetections();
//
//                for(org.openftc.apriltag.AprilTagDetection tag : currentDetections)
//                {
//                    if((loc==LEFT && (tag.id==1 || tag.id==4))
//                            || (loc==CENTER && (tag.id==2 || tag.id==5))
//                            || (loc==RIGHT && (tag.id==3 || tag.id==6)))
//                    {
//                        tagFound = true;
//                        tagOfInterest = tag;
//                        break;
//                    }
//                }
//            }
//            if (tagFound) {
//                drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
//
//                double x = tagOfInterest.pose.x - 0.095;
//                double xperInch = 40;
//
//                TrajectoryActionBuilder trajApril;
//                trajApril = drive.actionBuilder(new Pose2d(0, 0, 0))
//                        .strafeToConstantHeading(new Vector2d(0, xperInch * x));
//                Actions.runBlocking(new SequentialAction(
//                        trajApril.build()
//                ));
//            }
//            Actions.runBlocking(new SequentialAction(
//                    new BlueCloseAuto.LiftUp()
//            ));
//            return true;
//
//        }
//    }
//    public class DropPixel implements Action {
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet) {
//            sleep(1000);
//            return false;
//        }
//    }
//    @Override
//    public void runOpMode()
//    {
//
//    }
//}
//
