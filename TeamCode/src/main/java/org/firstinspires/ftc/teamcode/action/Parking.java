package org.firstinspires.ftc.teamcode.action;


import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Parking {
    private Servo endgameServo1;
    private Servo endgameServo2;
    private Servo endgameServo3;
    private Servo endgameServo4;
    Telemetry telemetry;
    boolean xWasPressed = false;
    boolean lastX = false;

    public void init(@NonNull OpMode opMode){
        HardwareMap hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;
        endgameServo1 = hardwareMap.get(Servo.class, "Endgame Servo 1");
        endgameServo2 = hardwareMap.get(Servo.class, "Endgame Servo 2");
        endgameServo3 = hardwareMap.get(Servo.class, "Endgame Servo 3");
        endgameServo4 = hardwareMap.get(Servo.class, "Endgame Servo 4");

        endgameServo1.setPosition(1.0);
        endgameServo2.setPosition(1.0);
        endgameServo3.setPosition(0.0);
        endgameServo4.setPosition(0.0);

    }

    public void setServoPos() {
        endgameServo1.setPosition(0.6);
        endgameServo2.setPosition(0.6);
        endgameServo3.setPosition(0.4);
        endgameServo4.setPosition(0.4);
    }

    public void buttonParking(boolean x) {
        if (x && !lastX) {
            xWasPressed = !xWasPressed;
        }

        if (xWasPressed) {
            setServoPos();
        }

    }

    public void telemetryOutput(){
    }
}