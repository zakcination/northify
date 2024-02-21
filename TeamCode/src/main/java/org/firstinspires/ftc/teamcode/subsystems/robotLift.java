package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class robotLift{
        public static DcMotorEx liftMotor; // Make shooterMotor static

    public robotLift(HardwareMap hardwareMap) {
            liftMotor = hardwareMap.get(DcMotorEx.class, "shooterMotor");
        }

        public static class SpinUp implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    liftMotor.setTargetPosition(1025); // Access shooterMotor statically
                    initialized = true;
                }

                double vel = liftMotor.getVelocity(); // Access shooterMotor statically
                packet.put("shooterVelocity", vel);
                return vel < 10_000.0;
            }
        }

        public static Action spinUp() { // Make spinUp method static
            return new SpinUp();
        }
    }

//    public DcMotorEx liftMotor;
//    private static final double HOME_LIFT_POSITION = 0;
//    private static final double BACKDROP_LIFT_POSITION = 1;
//    private static final double PID_Coefficient_lift = 15;
//    public robotLift(HardwareMap hardwareMap){
//        liftMotor = hardwareMap.get(DcMotorEx.class, "lift_motor");
//        liftMotor.setPositionPIDFCoefficients(PID_Coefficient_lift);
//        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//    }
//    public class liftUp implements Action{
//        private boolean initialized = false;
//
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet) {
//            if (!initialized) {
//                liftMotor.setTargetPosition(1025);
//                initialized = true;
//            }
//
//            double vel = liftMotor.getVelocity();
//            packet.put("liftVelocity", vel);
//            return vel < 10_000.0;
//        }
//    }
//
//    public Action liftUp() {
//        return new liftUp();
//    }
//    public void setLiftPosition(boolean position){
//        liftMotor.setTargetPosition(1025);
//        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        liftMotor.setPower(0.5);
//    }
//    public void setPower(double power) {
//        liftMotor.setPower(power);
//    }
//    public void setLiftHomePosition(){
//        setLiftPosition(HOME_LIFT_POSITION);
//    }
//    public void setLiftBackdropPosition(){
//        setLiftPosition(BACKDROP_LIFT_POSITION);
//    }