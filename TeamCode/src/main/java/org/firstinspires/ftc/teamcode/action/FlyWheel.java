package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class FlyWheel{
    public DcMotorEx turretLauncher;
    public double lowVelocity = 1000;

    public void init(@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        turretLauncher = hardwareMap.get(DcMotorEx.class, "Turret Launcher");
        turretLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turretLauncher.setDirection(DcMotorSimple.Direction.FORWARD);

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(19, 0, 0, -150);
        turretLauncher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

    }

    public void flyWheelPower (float powerButton) {
       if (powerButton > 0.5) {
           turretLauncher.setVelocity(lowVelocity);
       }
       else {
           turretLauncher.setVelocity(0);
       }
    }
}