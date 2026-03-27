package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FlyWheel {
    private DcMotorEx m1, m2;
    private double encoderCPM = 28;
    private double gearRatio = 3/2;
    private double  kV = 0.000171, kS = 0.09 , kP = 0.0009;

    public double lowVelocity = 3250;
    public double highVelocity = 4750;

    private double  targetRpm;
    Telemetry telemetry;

    private boolean lastDpadUp = false;
    private boolean lastDpadDown = false;

    public void init(@NonNull OpMode opMode) {
        telemetry = opMode.telemetry;
        HardwareMap hardwareMap = opMode.hardwareMap;

        m1 = hardwareMap.get(DcMotorEx.class, "Turret Launcher");
        m2 = hardwareMap.get(DcMotorEx.class, "Turret Launcher 2");
        m1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        m2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void setMotorPower(double power){
        m1.setPower(power);
        m2.setPower(power);
    }

    public void setMotorRPM (float leftTrigger, float rightTrigger){
        if (leftTrigger > 0.5 && rightTrigger < 0.5){
            targetRpm = lowVelocity;
            double error = targetRpm - getRPM();
            double ff = (kV * targetRpm) + kS;
            double fb = error * kP;
            double power = ff + fb;
            setMotorPower(power);
        }
        else if (rightTrigger > 0.5 && leftTrigger < 0.5) {
            targetRpm = highVelocity;
            double error = targetRpm - getRPM();
            double ff = (kV * targetRpm) + kS;
            double fb = error * kP;
            double power = ff + fb;
            setMotorPower(power);
        }
        else {
            setMotorPower(0);
        }



    }

    public void adjustLowVelocity (boolean dpadUp, boolean dpadDown) {
        if (dpadUp && !lastDpadUp) {
            lowVelocity += 10;
        }

        if (dpadDown && !lastDpadDown) {
            lowVelocity -= 10;
        }
    }

    public double getTicksPerSec(){
        return m1.getVelocity();
    }

    public double getRPM(){
        return ((getTicksPerSec()/encoderCPM) * 60) / gearRatio;
    }
    public void telemetryOutput() {
        telemetry.addData("Target Velocity", targetRpm);
    }
}