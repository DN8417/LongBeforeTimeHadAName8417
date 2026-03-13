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

    private DcMotorEx flywheelMaster;
    private DcMotorEx flywheelSlave;

    private Telemetry telemetry;

    // Velocity targets
    public double lowVelocity = 1125;
    public double highVelocity = 1300;
    private boolean lastDpadUp = false;
    private boolean lastDpadDown = false;

    // Fixed PIDF constants
    private static final double kP =6.;
    private static final double kI = 0.0;
    private static final double kD = 0.0;
    private static final double kF = 10.4;
    public double getVelocity() {
        return flywheelMaster.getVelocity();
    }

    public void init(@NonNull OpMode opMode) {

        HardwareMap hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        flywheelMaster = hardwareMap.get(DcMotorEx.class, "Turret Launcher");
        flywheelSlave  = hardwareMap.get(DcMotorEx.class, "Turret Launcher 2");

        flywheelSlave.setDirection(DcMotorSimple.Direction.FORWARD);
        flywheelMaster.setDirection(DcMotorSimple.Direction.FORWARD);

        flywheelMaster.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flywheelMaster.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelSlave.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // <-- FIXED

        PIDFCoefficients pidf = new PIDFCoefficients(kP, kI, kD, kF);
        flywheelMaster.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidf);
        flywheelSlave.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidf); // optional


    }

    public void flyWheelPower(float leftTrigger, float rightTrigger) {

        double targetVelocity = 0;

        if (leftTrigger > 0.5 && rightTrigger < 0.5) {
            targetVelocity = lowVelocity;
        }
        else if (rightTrigger > 0.5 && leftTrigger < 0.5) {
            targetVelocity = highVelocity;
        }



        flywheelMaster.setVelocity(targetVelocity);
        flywheelSlave.setVelocity(targetVelocity);
    }

    public void adjustLowVelocity(boolean dpadUp, boolean dpadDown) {  //tuning code

        if (dpadUp && !lastDpadUp) {
            lowVelocity += 10;
        }

        if (dpadDown && !lastDpadDown) {
            lowVelocity -= 10;
        }

        lastDpadUp = dpadUp;
        lastDpadDown = dpadDown;

        telemetry.addData("Low Velocity Setting", lowVelocity);
    }
    public void TelemetryOutput() {

        telemetry.addData("Target Velocity",
                flywheelMaster.getVelocity());

        telemetry.addData("Actual Velocity",
                flywheelMaster.getVelocity());
    }
}