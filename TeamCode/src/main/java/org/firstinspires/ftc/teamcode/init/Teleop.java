package org.firstinspires.ftc.teamcode.init;


import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.action.ColorSensor;
//import org.firstinspires.ftc.teamcode.action.Parking;
//import org.firstinspires.ftc.teamcode.action.limelight;
import org.firstinspires.ftc.teamcode.action.FieldCentricTest;
import org.firstinspires.ftc.teamcode.action.touchSensor;
import org.firstinspires.ftc.teamcode.action.mecanumDrive;
import org.firstinspires.ftc.teamcode.action.Intake;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

@TeleOp (name = "TeleOp", group = "Main")
public class Teleop extends OpMode {
    mecanumDrive mecanumDrive = new mecanumDrive();
    Intake intake = new Intake();
    //Parking parking = new Parking();
    ColorSensor colorSensor = new ColorSensor();
    private IMU imu;
    private double distance;
    private Limelight3A limelight;
    boolean mode = true;
    ElapsedTime swapDelay = new ElapsedTime();
    touchSensor touchsensor = new touchSensor();
    FieldCentricTest fieldCentric = new FieldCentricTest();

    @Override
    public void init() {
        //Initialize our motors
        mecanumDrive.init(this);
        intake.init(this);
        //parking.init(this);
        colorSensor.init(this);
        touchsensor.init(this);
        fieldCentric.init(this);

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0); // april tag #11 pipeline

        imu= hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot= new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));


    }

    public void start() {
        mecanumDrive.runWithoutEncoder();
        intake.init(this);
        //parking.init(this);
        colorSensor.init(this);
        fieldCentric.init(this);
        limelight.start();

    }

    @Override
    public void loop() {

        if(gamepad1.back || gamepad2.back) {
            if(swapDelay.time() > .75) {
                mode = !mode;
                swapDelay.reset();
            }
        }

        if(mode) {
            //Controls for mecanumDrive()
            mecanumDrive.slowMode(gamepad1.left_bumper);
            mecanumDrive.setPower(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            //parking.lifting(gamepad1.b);
            mecanumDrive.telemetryOutput();
//            intake.telemetryOutput();

            intake.takeAndGive(gamepad2.right_bumper, gamepad2.left_bumper);
            intake.startLoading(gamepad2.b);
            intake.finishLoading(gamepad2.dpad_up || gamepad2.dpad_down);
            intake.smallWheelSpin(gamepad2.b);
            intake.launch(gamepad2.left_trigger, gamepad2.right_trigger);

        } else if (!mode) {
            //Controls for mecanumDrive()
            mecanumDrive.slowMode(gamepad2.left_bumper);
            mecanumDrive.setPower(gamepad2.left_stick_x, gamepad2.left_stick_y, gamepad2.right_stick_x);
            //parking.lifting(gamepad2.b);
            mecanumDrive.telemetryOutput();
//            intake.telemetryOutput();

            intake.takeAndGive(gamepad1.right_bumper, gamepad1.left_bumper);
            intake.startLoading(gamepad1.b);
            intake.finishLoading(gamepad1.dpad_up || gamepad1.dpad_down);
            intake.smallWheelSpin(gamepad1.b);
            intake.launch(gamepad1.left_trigger, gamepad1.right_trigger);

        }



        YawPitchRollAngles orientation= imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw());
        LLResult llResult = limelight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            Pose3D botpose = llResult.getBotpose_MT2();
            //distance = getDistanceFromTage(llResult.getTa());
            telemetry.addData("distance", distance);
            telemetry.addData("Tx", llResult.getTx());
            telemetry.addData("Ta", llResult.getTa());

        }
            double Tx = llResult.getTx();

            if (Tx < -3 && !touchsensor.leftTouchSensorIsPressed()) {
                telemetry.addData("Tx", "TurretLeft");
                intake.turretDirection(-0.2);
            }

            else if (Tx > 3 && !touchsensor.leftTouchSensorIsPressed()) {
                telemetry.addData("Tx", "TurretRight");
                intake.turretDirection(0.2);
            }

            else {
                telemetry.addData("Tx", "Good");
                intake.turretDirection(0);
            }

            telemetry.addData("Tx", "llresult.getTx");

            telemetry.addData("CurrentMode: ", mode ? 0 : 1);
            colorSensor.getDetectedColor(telemetry);
            telemetry.addData("Left Test Sensor Position", touchsensor.leftTouchSensorIsPressed());
            telemetry.addData("Right Test Sensor Position", touchsensor.rightTouchSensorIsPressed());

        }


    }