package org.firstinspires.ftc.teamcode.action;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Parking {
    private Servo endgameServo1;
    private Servo endgameServo2;
    private Servo endgameServo3;
    private Servo endgameServo4;
    Telemetry telemetry;

    public void init(@NonNull OpMode opMode){
        HardwareMap hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;
        endgameServo1 = hardwareMap.get(Servo.class, "Endgame Servo 1");
        endgameServo2 = hardwareMap.get(Servo.class, "Endgame Servo 2");
        endgameServo3 = hardwareMap.get(Servo.class, "Endgame Servo 3");
        endgameServo4 = hardwareMap.get(Servo.class, "Endgame Servo 4");

    }

    public void setServoPos(double angle) {
        endgameServo1.setPosition(angle);
        endgameServo2.setPosition(angle);
        endgameServo3.setPosition(angle);
        endgameServo4.setPosition(angle);
    }

    public void telemetryOutput(){
    }
}