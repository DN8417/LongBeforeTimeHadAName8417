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
    private DcMotor endgameMotor;
    private Servo endgameServo1;
    private Servo endgameServo2;
    private Servo endgameServo3;
    private Servo endgameServo4;
    Telemetry telemetry;

    public void init(@NonNull OpMode opMode){
        HardwareMap hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;
        endgameMotor = hardwareMap.get(DcMotor.class, "Endgame Motor");
        endgameServo1 = hardwareMap.get(Servo.class, "Endgame Servo 1");
        endgameServo2 = hardwareMap.get(Servo.class, "Endgame Servo 2");
        endgameServo3 = hardwareMap.get(Servo.class, "Endgame Servo 3");
        endgameServo4 = hardwareMap.get(Servo.class, "Endgame Servo 4");

        endgameMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        endgameMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setMotorSpeed(double speed, boolean xButton) {
            endgameMotor.setPower(speed / 2);
    }

    public void buttonParking(double speed, boolean xButton) {
        boolean stickPark = true;
        if (xButton && stickPark) {
            stickPark = !stickPark;
        } else if (xButton && !stickPark) {
            stickPark = true;
        }

        if (!stickPark){
            endgameMotor.setPower(0.14);
        } else if (stickPark) {
            endgameMotor.setPower(speed);
        }
    }
    public void setServoPos(double angle) {
        endgameServo1.setPosition(angle);
        endgameServo2.setPosition(angle);
        endgameServo3.setPosition(angle);
        endgameServo4.setPosition(angle);
    }

    public void telemetryOutput(){
        telemetry.addData("Current Parking Speed", endgameMotor.getPower());
    }
}