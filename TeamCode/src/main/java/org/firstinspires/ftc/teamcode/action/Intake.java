package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.text.DecimalFormat;

public class Intake {
    static final DecimalFormat df = new DecimalFormat("0.00");
    Telemetry telemetry;
    private DcMotor intakeMotor;
    private CRServo intakePartTwo;
    private CRServo rubberBandWheel;
    private CRServo smallWheel;
    private CRServo triggerWheel;

    public void init (@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        intakePartTwo = hardwareMap.get(CRServo.class, "Second Intake");
        rubberBandWheel = hardwareMap.get(CRServo.class, "Rubber Band Wheel");
        smallWheel = hardwareMap.get(CRServo.class, "Small Wheel");
        triggerWheel = hardwareMap.get(CRServo.class, "Trigger Wheel");

        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public void takeAndGive (boolean isTaking, boolean isGiving) {

        if (isTaking && !isGiving) {
            intakeMotor.setPower(1.00);

        }
        else if (!isTaking && isGiving) {
            intakeMotor.setPower(-1.00);

        }
        else if (!isTaking && !isGiving) {
            intakeMotor.setPower(0.0);

        }
    }

    public void startLoading (boolean startsLoading, boolean startsEjecting) {

        if (startsLoading && !startsEjecting) {
            intakePartTwo.setPower(0.5);

        }
        else if (!startsLoading && startsEjecting) {
            intakePartTwo.setPower(-0.5);

        }
        else if (!startsLoading && !startsEjecting) {
            intakePartTwo.setPower(0.0);

        }

    }

    public void smallWheelSpin (boolean startsSpinning, boolean opositeSpin) {

        if (startsSpinning && !opositeSpin) {
            smallWheel.setPower(1.00);

        }
        else if (!startsSpinning && opositeSpin) {
            smallWheel.setPower(-1.00);

        }
        else if (!startsSpinning && !opositeSpin) {
            smallWheel.setPower(0.00);

        }

    }

    public void finishLoading (boolean finishesLoading) {

        if (finishesLoading) {
            rubberBandWheel.setPower(-1.00);

        }
        else if (!finishesLoading) {
            rubberBandWheel.setPower(0.0);

        }

    }

    public void triggerWheels (float triggerIntaking, float triggerReverse) {

        if (triggerIntaking > 0.5) {
            triggerWheel.setPower(1.00);

        }
        else if (triggerReverse > 0.5) {
            triggerWheel.setPower(-1.00);

        }

    }

}