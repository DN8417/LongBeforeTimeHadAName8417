package org.firstinspires.ftc.teamcode.action;


import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Parking {
    private DcMotor endgameMotor;
    private Servo endgameServo1;
    private Servo endgameServo2;
    private Servo endgameServo3;
    private Servo endgameServo4;

    public void init(@NonNull OpMode opMode){
        HardwareMap hardwareMap = opMode.hardwareMap;
        endgameMotor = hardwareMap.get(DcMotor.class, "Endgame Motor");
        endgameServo1 = hardwareMap.get(Servo.class, "Endgame Servo 1");
        endgameServo2 = hardwareMap.get(Servo.class, "Endgame Servo 2");
        endgameServo3 = hardwareMap.get(Servo.class, "Endgame Servo 3");
        endgameServo4 = hardwareMap.get(Servo.class, "Endgame Servo 4");

        endgameMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        endgameMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setMotorSpeed(double speed) {
        endgameMotor.setPower(speed);
    }
    public void setServoPos(double angle) {
        endgameServo1.setPosition(angle);
        endgameServo2.setPosition(angle);
        endgameServo3.setPosition(angle);
        endgameServo4.setPosition(angle);
    }
}