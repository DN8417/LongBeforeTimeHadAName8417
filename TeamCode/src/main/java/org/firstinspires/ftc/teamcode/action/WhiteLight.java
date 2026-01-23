package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class WhiteLight {
    private Servo Whitelight;

    public void init(@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        Whitelight = hardwareMap.get(Servo.class, "White Light");
    }

    public void setServoPos(double angle) {
        Whitelight.setPosition(angle);
    }


}
