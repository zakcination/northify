package org.firstinspires.ftc.teamcode.subsystems.teamElementDetection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.Arrays;
import java.util.List;


public class splitAveragePipeline extends OpenCvPipeline {

    List<Integer> ELEMENT_COLOR = Arrays.asList(255, 0, 0); //(red, green, blue)

    //Telemetry telemetry;

    static int color_zone = 1;

    int toggleShow = 1;

    //public SplitAveragePipeline(/*Telemetry telemetry, */int iCAMERA_HEIGHT, int iCAMERA_WIDTH){
    //    //this.telemetry = telemetry;
    //    this.CAMERA_HEIGHT = iCAMERA_HEIGHT;
    //    this.CAMERA_WIDTH = iCAMERA_WIDTH;
    //}

    Mat original;

    Mat zone1;
    Mat zone2;
    Mat zone3;

    Scalar avgColor1;
    Scalar avgColor2;
    Scalar avgColor3;

    double distance1 = 1;
    double distance2 = 1;
    double distance3 = 0;

    @Override
    public Mat processFrame(Mat input) {

        //Creating duplicate of original frame with no edits
        original = input.clone();

        //input = input.submat(new Rect(0));

        //Defining Zones
        //Rect(top left x, top left y, bottom right x, bottom right y)
        zone1 = input.submat(new Rect(0, 180, 115, 115));
        zone2 = input.submat(new Rect(316, 180, 115, 115));
        zone3 = input.submat(new Rect(660, 180, 115, 115));

        //Averaging the colors in the zones
        avgColor1 = Core.mean(zone1);
        avgColor2 = Core.mean(zone2);
        avgColor3 = Core.mean(zone3);

        //Putting averaged colors on zones (we can see on camera now)
        zone1.setTo(avgColor1);
        zone2.setTo(avgColor2);
        zone3.setTo(avgColor3);

        double distance1 = color_distance(avgColor1, ELEMENT_COLOR);
        double distance2 = color_distance(avgColor2, ELEMENT_COLOR);
        double distance3 = color_distance(avgColor3, ELEMENT_COLOR);

        double max_distance = Math.min(distance3, Math.min(distance1, distance2));

        if (max_distance == distance1){
            //telemetry.addData("Zone 1 Has Element", distance1);
            color_zone = 1;

        }else if (max_distance == distance2){
            //telemetry.addData("Zone 2 Has Element", distance2);
            color_zone = 2;
        }else{
            //telemetry.addData("Zone 2 Has Element", distance3);
            color_zone = 3;
        }

        // Allowing for the showing of the averages on the stream
        if (toggleShow == 1){
            return input;
        }else{
            return original;
        }
    }

    public double color_distance(Scalar color1, List color2){
        double r1 = color1.val[0];
        double g1 = color1.val[1];
        double b1 = color1.val[2];

        int r2 = (int) color2.get(0);
        int g2 = (int) color2.get(1);
        int b2 = (int) color2.get(2);

        return Math.sqrt(Math.pow((r1 - r2), 2) + Math.pow((g1 - g2), 2) + Math.pow((b1 - b2), 2));
    }

    public void setAlliancePipe(String alliance){
        if (alliance.equals("red")){
            ELEMENT_COLOR = Arrays.asList(255, 0, 0);
        }else{
            ELEMENT_COLOR = Arrays.asList(0, 0, 255);
        }
    }

    public int get_element_zone(){
        return color_zone;
    }

    public void toggleAverageZonePipe(){
        toggleShow = toggleShow * -1;
    }

}