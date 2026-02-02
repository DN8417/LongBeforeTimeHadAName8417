package org.firstinspires.ftc.teamcode.init;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.action.touchSensor;

@Autonomous(name="Blue Goal Zone", group ="Autos")
public class BlueSideAuto extends LinearOpMode {

    public DcMotor frontRightDrive;
    public DcMotor frontLeftDrive;
    public DcMotor backRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor rubberBandWheel;
    public DcMotorEx turretLauncher;
    public CRServo smallWheel;
    public CRServo intakePartTwo;
    public DcMotor intakeMotor;
    private IMU imu;
    private double distance;
    private Limelight3A limelight;
    touchSensor touchsensor = new touchSensor();
    private CRServo turretMotor;


    public ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() throws  InterruptedException {

        frontRightDrive = hardwareMap.get(DcMotor.class, "Front Right");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "Front Left");
        backRightDrive = hardwareMap.get(DcMotor.class, "Back Right");
        backLeftDrive = hardwareMap.get(DcMotor.class, "Back Left");
        rubberBandWheel = hardwareMap.get(DcMotor.class, "Rubber Band Wheel");
        turretLauncher = hardwareMap.get(DcMotorEx.class, "Turret Launcher");
        smallWheel = hardwareMap.get(CRServo.class, "Small Wheel");
        intakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        intakePartTwo = hardwareMap.get(CRServo.class, "Second Intake");
        turretMotor = hardwareMap.get(CRServo.class, "Turret Motor");

        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rubberBandWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        turretLauncher.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        touchsensor.init(this);

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(1); // april tag #11 pipeline

        imu= hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot= new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.UP);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(19, 0, 0, -150);
        turretLauncher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        waitForStart();
        limelight.start();

        // Forward
        mecanumDrive(0.75, 0.75, 0.75, 0.75, 0.00, -1400, 0.00, 0.00, 0.00, 0.7);
        // Moves left
        mecanumDrive(0.75, -0.75, -0.75, 0.75, 0.00, -1400, 0.00, 0.00, 0.00, 0.6);
        // Forward again
        mecanumDrive(0.75, 0.75, 0.75, 0.75, 0.00, -1400, 0.00, 0.00, 0.00, 0.3);
        // Turns right
        mecanumDrive(-0.75, 0.75, -0.75, 0.75, 0.00, -1400, 0.00, 0.00, 0.00, 0.4);
        // Buffer time before shooting
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, -1400, 0.00, 0.00, 0.00, 0.5);
        // Shooting first artifact
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 1.00, -1400, -1.00, 0.00, -0.5, 1.0);
        // Charging up to shoot again
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, -1400, 0.00, 1.00, 0.00, 1.5);
        // Shooting Second artifact
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 1.00, -1400, -1.00, 1.00, -0.7, 1.0);
        // Charging up to shoot again
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, -1400, 0.00, 1.00, 0.00, 1.5);
        // Shoots last artifact
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 1.00, -1400, -1.00, 1.00, -0.7, 1.0);
        // Stalling a bit
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, -1400, 0.00, 1.00, 0.00, 1.5);
        // Moves left
        mecanumDrive(0.75, -0.75, -0.75, 0.75, 0.00, -1400, 0.00, 1.00, 0.00, 0.4);
        // Turns Right
        mecanumDrive(-0.75, 0.75, -0.75, 0.75, 0.00, -1400, 0.00, 1.00, 0.00, 0.6);
        // Moves left to not hit the gate
        mecanumDrive(-0.5, 0.5, 0.5, -0.5, 0.00, -1400, 0.00, 1.00, 0.00, 0.1);
        // Moves forward and intakes more artifacts
        mecanumDrive(-0.5, -0.5, -0.5, -0.5, 0.00, -1400, -1.00, 1.00, -0.5, 0.6);
        // Moving the artifacts along the intake
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, -1400, -1.00, 1.00, -0.5, 1.0);
        // Moves back while still intaking
        mecanumDrive(0.01, 0.01, 0.01, 0.01, 0.00, -1350, -1.00, 1.00, -0.5, 0.001);
        //Moves Forward to correct itself
        mecanumDrive(0.75, 0.75, 0.75, 0.75, 0.00, -1350, -1.00, 1.00, -0.5, 0.8);
        // Turn Right
        mecanumDrive(-0.75, 0.75, -0.75, 0.75, 0.00, -1350, -1.00, 1.00, -0.5, 0.65);
        // Buffer time before shooting
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, -1350, 0.00, 1.00, 0.00, 1.5);
        // Shooting first artifact
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 1.00, -1350, -1.00, 1.00, -0.5, 1.0);
        // Charging up to shoot again
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, -1350, 0.00, 1.00, 0.00, 1.5);
        // Shooting Second artifact
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 1.00, -1350, -1.00, 1.00, -0.7, 1.0);
        // Charging up to shoot again
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, -1350, 0.00, 1.00, 0.00, 1.5);
        // Shoots last artifact
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 1.00, -1350, -1.00, 0.00, -0.7, 1.0);
        // Turns Right
        mecanumDrive(-0.75, 0.75, -0.75, 0.75, 0.00, -1350, 0.00, 0.00, 0.00, 0.4);
        // move left to line up with the artifacts
        mecanumDrive(0.75, -0.75, -0.75, 0.75, 0.00, -1350, 0.00, 0.00, 0.00, 0.6);
        // moving backwards and picking up 3 more
        mecanumDrive(-0.3, -0.3, -0.3, -0.3, 0.00, -1350, 0.00, 1.00, -0.5, 1.0);



    }

    public void mecanumDrive(double frontRightPower, double frontLeftPower, double backRightPower, double backLeftPower,
                             double rubberBandPower, double launcherVelocity, double smallWheelPower, double intakePower,
                             double secondIntakePower, double seconds) {

        timer.reset();

        while(opModeIsActive() && (timer.seconds() <= seconds )) {

            frontRightDrive.setPower(frontRightPower);
            frontLeftDrive.setPower(frontLeftPower);
            backRightDrive.setPower(backRightPower);
            backLeftDrive.setPower(backLeftPower);
            rubberBandWheel.setPower(rubberBandPower);
            turretLauncher.setVelocity(launcherVelocity);
            smallWheel.setPower(smallWheelPower);
            intakePartTwo.setPower(secondIntakePower);
            intakeMotor.setPower(intakePower);

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
                turretMotor.setPower(-0.2);
            }

            else if (Tx > 3 && !touchsensor.leftTouchSensorIsPressed()) {
                telemetry.addData("Tx", "TurretRight");
                turretMotor.setPower(0.2);
            }

            else {
                telemetry.addData("Tx", "Good");
                turretMotor.setPower(0);
            }

        }

    }

}

