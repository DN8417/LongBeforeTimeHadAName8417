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


    public void init (@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        lifting1 = hardwareMap.get(Servo.class, "Lifting Servo 1");
        //lifting2 = hardwareMap.get(Servo.class, "Lifting Servo 2");

        lifting1.setPosition(0.2);
        //lifting2.setPosition(0.45);
    }

    public void lifting (boolean readyToPark) {

        if (readyToPark) {
            lifting1.setPosition(0.22);
            //lifting2.setPosition(0.80);

        }

    }
}
