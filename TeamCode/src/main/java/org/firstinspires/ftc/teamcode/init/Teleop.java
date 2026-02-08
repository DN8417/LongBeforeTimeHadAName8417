package org.firstinspires.ftc.teamcode.init;


import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
//import org.firstinspires.ftc.teamcode.action.FieldCentricTest;
import org.firstinspires.ftc.teamcode.action.Light;
import org.firstinspires.ftc.teamcode.action.ColorSensor;
import org.firstinspires.ftc.teamcode.action.Parking;
//import org.firstinspires.ftc.teamcode.action.limelight;
//import org.firstinspires.ftc.teamcode.action.FieldCentricTest;
import org.firstinspires.ftc.teamcode.action.FlyWheel;
import org.firstinspires.ftc.teamcode.action.WhiteLight;
import org.firstinspires.ftc.teamcode.action.touchSensor;
import org.firstinspires.ftc.teamcode.action.mecanumDrive;
import org.firstinspires.ftc.teamcode.action.Intake;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

@TeleOp (name = "TeleOp", group = "Main")
public class Teleop extends OpMode {
    mecanumDrive mecanumDrive = new mecanumDrive();
    Intake intake = new Intake();
    Parking parking = new Parking();
    ColorSensor colorSensor = new ColorSensor();
    private IMU imu;
    private double distance;
    private Limelight3A limelight;
    boolean mode = true;
    ElapsedTime swapDelay = new ElapsedTime();
    touchSensor touchsensor = new touchSensor();
    //FieldCentricTest fieldCentric = new FieldCentricTest();
    FlyWheel flyWheel = new FlyWheel();
    private DcMotorEx turretLauncher;
    public double getDistanceFromTage(double ta) {
        double scale = 3.085408;  // y value in equation
        double distance = (scale / ta);
        return distance;
    }
    Light light = new Light();
    WhiteLight whiteLight = new WhiteLight();

    @Override
    public void init() {
        //Initialize our motors
        mecanumDrive.init(this);
        intake.init(this);
        parking.init(this);
        colorSensor.init(this);
        touchsensor.init(this);
        //fieldCentric.init(this);
        flyWheel.init(this);
        light.init(this);
        whiteLight.init(this);

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0); // april tag #11 pipeline

        imu= hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot= new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.UP);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));

        turretLauncher = hardwareMap.get(DcMotorEx.class, "Turret Launcher");

    }

    public void start() {
        mecanumDrive.runWithoutEncoder();
        intake.init(this);
        parking.init(this);
        colorSensor.init(this);
        //fieldCentric.init(this);
        flyWheel.init(this);
        touchsensor.init(this);
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
            intake.parkingTurretDirection(gamepad1.a, gamepad1.b);
            parking.buttonParking(gamepad2.left_stick_y, gamepad1.x);

            intake.takeAndGive(gamepad2.right_bumper, gamepad2.left_bumper);
            intake.startLoading(gamepad2.b);
            intake.finishLoading(gamepad2.dpad_up || gamepad2.dpad_down);
            intake.smallWheelSpin(gamepad2.b);
            flyWheel.flyWheelPower(gamepad2.left_trigger, gamepad2.right_trigger);
            parking.buttonParking(gamepad2.left_stick_y, gamepad2.x);

            mecanumDrive.telemetryOutput();
            parking.telemetryOutput();

        } else if (!mode) {
            //Controls for mecanumDrive()
            mecanumDrive.slowMode(gamepad2.left_bumper);
            mecanumDrive.setPower(gamepad2.left_stick_x, gamepad2.left_stick_y, gamepad2.right_stick_x);
            intake.parkingTurretDirection(gamepad2.a, gamepad2.b);
            parking.buttonParking(gamepad1.left_stick_y, gamepad1.x);

            intake.takeAndGive(gamepad1.right_bumper, gamepad1.left_bumper);
            intake.startLoading(gamepad1.b);
            intake.finishLoading(gamepad1.dpad_up || gamepad1.dpad_down);
            intake.smallWheelSpin(gamepad1.b);
            flyWheel.flyWheelPower(gamepad1.left_trigger, gamepad1.right_trigger);
            //intake.launch(gamepad1.right_trigger);

            mecanumDrive.telemetryOutput();
            parking.telemetryOutput();

        }



        YawPitchRollAngles orientation= imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw());
        LLResult llResult = limelight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            Pose3D botpose = llResult.getBotpose_MT2();
            distance = getDistanceFromTage(llResult.getTa());
            telemetry.addData("distance", distance);
            telemetry.addData("Tx", llResult.getTx());
            telemetry.addData("Ta", llResult.getTa());

        }
            double Tx = llResult.getTx();

            if (Tx < -3 && !touchsensor.rightTouchSensorIsPressed() && llResult != null && llResult.isValid()) {
                telemetry.addData("Tx", "TurretLeft");
                intake.turretDirection(-0.2);
            }

            else if (Tx > 3 && !touchsensor.leftTouchSensorIsPressed() && llResult != null && llResult.isValid()) {
                telemetry.addData("Tx", "TurretRight");
                intake.turretDirection(0.2);
            }

            else {
                telemetry.addData("Tx", "Good");
                intake.turretDirection(0);
            }

            PIDFCoefficients pidfCoefficients= new PIDFCoefficients(23,0,0,-650);
            turretLauncher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

            telemetry.addData("Tx", "llresult.getTx");
            telemetry.addData("Tx Value", Tx);

        if (distance > 2.7) {
            light.setServoPos(0.277);
        }

        else if (distance > 1.28 && distance < 2.7) {
            light.setServoPos(0.500);
        }

        else {
            light.setServoPos(0.388);
        }
        if (turretLauncher.getVelocity() > -1250 && turretLauncher.getVelocity() < -1100) {
            whiteLight.setServoPos(0.25);
        }

        else {
            whiteLight.setServoPos(0.00);
        }



            telemetry.addData("CurrentMode: ", mode ? 0 : 1);
            colorSensor.getDetectedColor(telemetry);
            telemetry.addData("Left Test Sensor Position", touchsensor.leftTouchSensorIsPressed());
            telemetry.addData("Right Test Sensor Position", touchsensor.rightTouchSensorIsPressed());
            telemetry.addData("Endgame Sensor is Pressed", touchsensor.endgameTouchSensorPressed());
            telemetry.addData("Current Velocity", turretLauncher.getVelocity());


        }

    }