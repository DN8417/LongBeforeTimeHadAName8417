package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.text.DecimalFormat;

public class Intake {
    static final DecimalFormat df = new DecimalFormat("0.00");
    Telemetry telemetry;
    private DcMotor intakeMotor;
    private CRServo intakePartTwo;
    private CRServo rubberBandWheel;
    //private CRServo secondRubberBandWheel;
    private CRServo smallWheel;
    //private CRServo triggerWheel;
    private DcMotor turretLauncher;
    private Servo turretAngle;

    public void init (@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        intakePartTwo = hardwareMap.get(CRServo.class, "Second Intake");
        rubberBandWheel = hardwareMap.get(CRServo.class, "Rubber Band Wheel");
        //secondRubberBandWheel = hardwareMap.get(CRServo.class, "Second Rubber Band Wheel");
        smallWheel = hardwareMap.get(CRServo.class, "Small Wheel");
        //triggerWheel = hardwareMap.get(CRServo.class, "Trigger Wheel");
        turretLauncher = hardwareMap.get(DcMotor.class, "Turret Launcher");
        turretAngle = hardwareMap.get(Servo.class, "Turret Angle");

        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turretLauncher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


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

        if (finishesLoading) {
            rubberBandWheel.setPower(-1.00);
//            secondRubberBandWheel.setPower(1.00);

        }
        else if (!finishesLoading) {
            rubberBandWheel.setPower(0.0);
//            secondRubberBandWheel.setPower(0.0);

        }

    }

    /*public void triggerWheels (float triggerIntaking, float triggerReverse) {

        if (triggerIntaking > 0.5) {
            triggerWheel.setPower(1.00);

        }
        else if (triggerReverse > 0.5) {
            triggerWheel.setPower(-1.00);

        }

    }*/

    public void launch (boolean bigRedButton) {

        if (bigRedButton) {
            turretLauncher.setPower(1.0);

        }
        else if (!bigRedButton) {
            turretLauncher.setPower(0.0);

        }

    }
    

}