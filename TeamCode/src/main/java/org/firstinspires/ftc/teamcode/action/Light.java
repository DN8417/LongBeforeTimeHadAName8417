package org.firstinspires.ftc.teamcode.action;
import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Light {
    private Servo light;

    public void init(@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        light = hardwareMap.get(Servo.class, "light");
    }

    public void setServoPos(double angle) {
        light.setPosition(angle);
    }


}