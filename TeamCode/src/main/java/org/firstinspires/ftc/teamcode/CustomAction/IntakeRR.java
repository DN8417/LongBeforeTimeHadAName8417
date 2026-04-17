package org.firstinspires.ftc.teamcode.CustomAction;

//import.androidx.annotation.NonNull;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.action.touchSensor;

import java.text.DecimalFormat;

public class IntakeRR {
    static final DecimalFormat df = new DecimalFormat("0.00");
    Telemetry telemetry;
    private DcMotor intakeMotor;
    private CRServo intakePartTwo;
    private CRServo ziptieWheel2;
    private CRServo ziptieWheel3;
    private DcMotor rubberBandWheel;
    private CRServo smallWheel;
    private DcMotorEx turretLauncher;
    private CRServo turretMotor;
    private TouchSensor leftTouchSensor;

    public void init (@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        intakePartTwo = hardwareMap.get(CRServo.class, "Second Intake");
        rubberBandWheel = hardwareMap.get(DcMotor.class, "Rubber Band Wheel");
        smallWheel = hardwareMap.get(CRServo.class, "Small Wheel");
        turretLauncher = hardwareMap.get(DcMotorEx.class, "Turret Launcher");
        turretMotor = hardwareMap.get(CRServo.class, "Turret Motor");
        leftTouchSensor = hardwareMap.get(TouchSensor.class, "Left Touch Sensor");
        ziptieWheel2 = hardwareMap.get(CRServo.class, "Ziptie Wheel 2");
        ziptieWheel3 = hardwareMap.get(CRServo.class, "Ziptie Wheel 3");

        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rubberBandWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


    }

    public class IntakeArtifactsIn implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            telemetryPacket.put("IntakePos", intakeMotor.getCurrentPosition());

            if (intakeMotor.getCurrentPosition() >= 3000) {
                intakeMotor.setPower(0);
                return false;
            }
            intakeMotor.setPower(1);
            return true;

        }
    }

}
