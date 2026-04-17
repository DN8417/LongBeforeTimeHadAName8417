package org.firstinspires.ftc.teamcode.init;


import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
//import org.firstinspires.ftc.teamcode.action.FieldCentricTest;
import org.firstinspires.ftc.teamcode.action.FieldCentricTest;
import org.firstinspires.ftc.teamcode.action.Light;
import org.firstinspires.ftc.teamcode.action.ColorSensor;
import org.firstinspires.ftc.teamcode.action.Parking;
//import org.firstinspires.ftc.teamcode.action.limelight;
//import org.firstinspires.ftc.teamcode.action.FieldCentricTest;
import org.firstinspires.ftc.teamcode.action.FlyWheel;
import org.firstinspires.ftc.teamcode.action.WhiteLight;
import org.firstinspires.ftc.teamcode.action.touchSensor;
//import org.firstinspires.ftc.teamcode.action.mecanumDrive;
import org.firstinspires.ftc.teamcode.action.Intake;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

@TeleOp (name = "TeleOp", group = "Main")
public class Teleop extends OpMode {
    //mecanumDrive mecanumDrive = new mecanumDrive();
    Intake intake = new Intake();
    Parking parking = new Parking();
    ColorSensor colorSensor = new ColorSensor();
    private IMU imu;
    private Limelight3A limelight;
    private double CameraHeight = 41.958;
    private double CameraAngle = 21;
    private double GoalHeight = 74.95;
    private double distance = 0;
    boolean mode = true;
    //hello world
    ElapsedTime swapDelay = new ElapsedTime();
    touchSensor touchsensor = new touchSensor();
    FieldCentricTest fieldCentric = new FieldCentricTest();
    FlyWheel flyWheel = new FlyWheel();

    public double getDistanceFromTage(double Ty) {
        double angleToGoal = CameraAngle + Ty;
        double heightDifferance = GoalHeight - CameraHeight;
        return heightDifferance / Math.tan(Math.toRadians(angleToGoal));
    }

    Light light = new Light();
    WhiteLight whiteLight = new WhiteLight();

    @Override
    public void init() {
        //Initialize our motors
        //mecanumDrive.init(this);
        intake.init(this);
        parking.init(this);
        colorSensor.init(this);
        touchsensor.init(this);
        fieldCentric.init(this);
        flyWheel.init(this);
        light.init(this);
        whiteLight.init(this);

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0); // april tag #11 pipeline

        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.UP);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));


    }

    public void start() {
        //mecanumDrive.runWithoutEncoder();
        intake.init(this);
        //parking.init(this);
        colorSensor.init(this);
        fieldCentric.init(this);
        flyWheel.init(this);
        touchsensor.init(this);
        limelight.start();

    }

    @Override
    public void loop() {

        if (gamepad1.back || gamepad2.back) {
            if (swapDelay.time() > .75) {
                mode = !mode;
                swapDelay.reset();
            }
        }

        if (mode) {
            //Controls for mecanumDrive()
            fieldCentric.slowMode(gamepad1.left_bumper);
            fieldCentric.driversideDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.right_bumper);
            intake.parkingTurretDirection(gamepad1.a, gamepad1.b);
            //parking.buttonParking(gamepad2.left_stick_y, gamepad1.x);

            intake.takeAndGive(gamepad2.right_bumper, gamepad2.left_bumper);
            intake.startLoading(gamepad2.b);
            intake.finishLoading(gamepad2.dpad_up || gamepad2.dpad_down);
            intake.smallWheelSpin(gamepad2.b);
            flyWheel.setMotorRPM(gamepad2.left_trigger, gamepad2.right_trigger);
            flyWheel.adjustLowVelocity(gamepad1.dpad_up, gamepad1.dpad_down);//tuning code
            //intake.launch(gamepad2.right_trigger);

            //parking.buttonParking(gamepad2.left_stick_y, gamepad2.x);

            fieldCentric.telemetryOutput();
            parking.telemetryOutput();
            flyWheel.telemetryOutput();

            fieldCentric.telemetryOutput();



        } else if (!mode) {
            //Controls for mecanumDrive()
            fieldCentric.slowMode(gamepad2.left_bumper);
            fieldCentric.driversideDrive(gamepad2.left_stick_x, gamepad2.left_stick_y, gamepad2.right_stick_x, gamepad1.right_bumper);
            intake.parkingTurretDirection(gamepad2.a, gamepad2.b);
            //parking.buttonParking(gamepad1.left_stick_y, gamepad1.x);

            intake.takeAndGive(gamepad1.right_bumper, gamepad1.left_bumper);
            intake.startLoading(gamepad1.b);
            intake.finishLoading(gamepad1.dpad_up || gamepad1.dpad_down);
            intake.smallWheelSpin(gamepad1.b);
            flyWheel.setMotorRPM(gamepad1.left_trigger, gamepad1.right_trigger);
            flyWheel.adjustLowVelocity(gamepad2.dpad_up, gamepad1.dpad_down);//tuning code
            //intake.launch(gamepad1.right_trigger);

            fieldCentric.telemetryOutput();
            parking.telemetryOutput();
            flyWheel.telemetryOutput();

        }


        LLResult llResult = limelight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            distance = getDistanceFromTage(llResult.getTy());
            telemetry.addData("Distance", distance);
        } else {
            telemetry.addData("No Valid Target", "Found");
        }


        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw());
        if (llResult != null && llResult.isValid()) {
            Pose3D botpose = llResult.getBotpose_MT2();
            distance = getDistanceFromTage(llResult.getTa());
            telemetry.addData("Tx", llResult.getTx());
            telemetry.addData("Ta", llResult.getTa());

        }
        double Tx = llResult.getTx();

        if (Tx < -3 && !touchsensor.rightTouchSensorIsPressed() && llResult != null && llResult.isValid()) {
            telemetry.addData("Tx", "TurretLeft");
            intake.turretDirection(-0.2);
        } else if (Tx > 3 && !touchsensor.leftTouchSensorIsPressed() && llResult != null && llResult.isValid()) {
            telemetry.addData("Tx", "TurretRight");
            intake.turretDirection(0.2);
        } else {
            telemetry.addData("Tx", "Good");
            intake.turretDirection(0);
        }

        telemetry.addData("Tx", "llresult.getTx");
        telemetry.addData("Tx Value", Tx);

        if (distance > 2.7) {
            light.setServoPos(0.277);
        } else if (distance > 1.28 && distance < 2.7) {
            light.setServoPos(0.500);
        } else {
            light.setServoPos(0.388);
        }


        if (flyWheel.getRPM() < 1250 && flyWheel.getRPM() > 1100) {
            whiteLight.setServoPos(0.15);
        } else {
            whiteLight.setServoPos(0.00);
        }


        telemetry.addData("CurrentMode: ", mode ? 0 : 1);
        colorSensor.getDetectedColor(telemetry);
        telemetry.addData("Left Test Sensor Position", touchsensor.leftTouchSensorIsPressed());
        telemetry.addData("Right Test Sensor Position", touchsensor.rightTouchSensorIsPressed());
        telemetry.addData("Endgame Sensor is Pressed", touchsensor.endgameTouchSensorPressed());
        telemetry.addData("Current RPM", flyWheel.getRPM());



    }

}