package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

public class FlyWheel{
    public DcMotorEx turretLauncher;
    public DcMotorEx turretLauncher2;
    public double lowVelocity = 1200;
    double highVelocity = 400;
    Telemetry telemetry;

    public void init(@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        turretLauncher = hardwareMap.get(DcMotorEx.class, "Turret Launcher");
        turretLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turretLauncher.setDirection(DcMotorSimple.Direction.FORWARD);

//        turretLauncher2 = hardwareMap.get(DcMotorEx.class, "Turret Launcher 2");
//        turretLauncher2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        turretLauncher2.setDirection(DcMotorSimple.Direction.FORWARD);


    }

    public void flyWheelPower (float powerButton, float bigRedButton) {
       if (powerButton > 0.5 && bigRedButton < 0.5) {
           turretLauncher.setVelocity(lowVelocity);
           PIDFCoefficients pidfCoefficients = new PIDFCoefficients(19, 0, 0, -150);
           turretLauncher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
       }
       else if (powerButton < 0.5 && bigRedButton > 0.5) {
           turretLauncher.setVelocity(highVelocity);
       }
       else {
           turretLauncher.setVelocity(0);
       }
    }
    public void TelemetryOutput(){
        telemetry.addData("Current Velocity", turretLauncher.getVelocity() * -1);

    }
}