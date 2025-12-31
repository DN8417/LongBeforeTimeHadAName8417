package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.text.DecimalFormat;

public class Parking {
    static final DecimalFormat df = new DecimalFormat("0.00");

    Telemetry telemetry;
    public Servo lifting1;
    public Servo lifting2;
    public Servo lifting3;
    public Servo lifting4;


    public void init (@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        lifting1 = hardwareMap.get(Servo.class, "Lifting Servo 1");
        lifting2 = hardwareMap.get(Servo.class, "Lifting Servo 2");
//        lifting3 = hardwareMap.get(Servo.class, "Lifting Servo 3");
//        lifting4 = hardwareMap.get(Servo.class, "Lifting Servo 4");

        lifting1.setPosition(0.0);
        lifting2.setPosition(0.0);
//        lifting3.setPosition(0.0);
//        lifting4.setPosition(0.0);
    }

//    public void lifting (boolean readyToPark) {
//
//        if (readyToPark) {
//            lifting1.setPosition(0.5);
//            lifting2.setPosition(0.5);
//            lifting3.setPosition(0.5);
//            lifting4.setPosition(0.5);
//
//        }
//
//    }


}
