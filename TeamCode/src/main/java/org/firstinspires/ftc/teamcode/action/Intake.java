package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.text.DecimalFormat;

public class Intake {
    static final DecimalFormat df = new DecimalFormat("0.00");
    Telemetry telemetry;
    private DcMotor intakeMotor;
    private CRServo intakePartTwo;
    private DcMotor rubberBandWheel;
    private CRServo smallWheel;
    private DcMotor turretLauncher;
    private ColorSensor color;

    public void init (@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        intakePartTwo = hardwareMap.get(CRServo.class, "Second Intake");
        rubberBandWheel = hardwareMap.get(DcMotor.class, "Rubber Band Wheel");
        smallWheel = hardwareMap.get(CRServo.class, "Small Wheel");
        turretLauncher = hardwareMap.get(DcMotor.class, "Turret Launcher");
        color = hardwareMap.get(ColorSensor.class, "Color Sensor");

        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        turretLauncher.setDirection(DcMotorSimple.Direction.REVERSE);
        rubberBandWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turretLauncher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


    }

    public void takeAndGive (boolean isIntaking, boolean isOutaking) {

        if (isIntaking && !isOutaking) {
            intakeMotor.setPower(1.00);

        }
        else if (!isIntaking && isOutaking) {
            intakeMotor.setPower(-1.00);


        }
        else if (!isIntaking && !isOutaking) {
            intakeMotor.setPower(0.0);

        }
    }

    public void startLoading (boolean startsLoading) {

        if (startsLoading) {
            intakePartTwo.setPower(-0.5);

        }
        else if (!startsLoading) {
            intakePartTwo.setPower(0.0);

        }

    }

    public void smallWheelSpin (boolean startsSpinning) {

        if (startsSpinning) {
            smallWheel.setPower(-1.00);

        }
        else if (!startsSpinning) {
            smallWheel.setPower(0.00);

        }

    }

    public void finishLoading (boolean finishesLoading) {

        if (finishesLoading/* && !(color.green() || color.)*/) {
            rubberBandWheel.setPower(1.00);

        }
        else if (!finishesLoading) {
            rubberBandWheel.setPower(0.0);

        }

    }

    public void launch (float bigRedButton) {

        if (bigRedButton > 0.5) {
            turretLauncher.setPower(1.0);

        }
        else if (bigRedButton < 0.5) {
            turretLauncher.setPower(0.0);

        }

    }

    public void telemetryOutput() {

        telemetry.addData("Red", color.red());
        telemetry.addData("Green", color.green());
        telemetry.addData("Blue", color.blue());
        telemetry.update();
    }

}