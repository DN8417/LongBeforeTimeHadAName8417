package org.firstinspires.ftc.teamcode.init;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.action.touchSensor;

@Autonomous(name="Blue Far Zone", group ="Autos")
public class BlueSideBottomAuto extends LinearOpMode {

    public DcMotor frontRightDrive;
    public DcMotor frontLeftDrive;
    public DcMotor backRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor rubberBandWheel;
    public DcMotor turretLauncher;
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
        turretLauncher = hardwareMap.get(DcMotor.class, "Turret Launcher");
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
        RevHubOrientationOnRobot revHubOrientationOnRobot= new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));

        waitForStart();
        limelight.start();

        // Giving the turret time to charge up
        mecanumDrive(0.00, 0.00, 0.00, 00.0, 0.00, 0.62, 0.00, 0.00, 0.00, 3.5);
        // Shooting first artifact
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 1.00, 0.62, -1.00, 0.00, -0.5, 1.0);
        // Charging up to shoot again
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, 0.62, 0.00, 0.00, 0.00, 1.5);
        // Shooting Second artifact
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 1.00, 0.62, -1.00, 0.00, -0.7, 1.0);
        // Charging up to shoot again
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, 0.62, -1.00, 1.00, -0.5, 1.5);
        // Shoots last artifact
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 1.00, 0.62, -1.00, 0.00, -0.7, 1.0);
        // Stalling a bit
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, 0.62, 0.00, 0.00, 0.00, 1.5);

        // Moving Forward so we don't scrape the wall
        mecanumDrive(0.75, 0.75, 0.75, 0.75, 0.00, 0.62, 0.00, 0.00, 0.00, 0.2);
        // Turns right
        mecanumDrive(-0.75, 0.75, -0.75, 0.75, 0.00, 0.62, 0.00, 0.00, 0.00, 0.3);
        // Moves back into the wall
        mecanumDrive(-0.75, 0.75, 0.75, -0.75, 0.00, 0.62, 0.00, 0.00, 0.00, 0.2);
        // Moves Backwards while intaking
        mecanumDrive(-0.75, -0.75, -0.75, -0.75, 0.00, 0.62, 0.00, 1.00, -0.5, 0.4);
        // Moving artifacts along the intake
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, 0.62, -1.00, 1.00, -0.5, 1.5);
        // Moving back into position
        mecanumDrive(0.75, 0.75, 0.75, 0.75, 0.00, 0.62, 0.00, 0.00, 0.00, 0.4);
        mecanumDrive(0.75, -0.75, -0.75, 0.75, 0.00, 0.62, 0.00, 0.00, 0.00, 0.2);
        mecanumDrive(0.75, -0.75, 0.75, -0.75, 0.00, 0.62, 0.00, 0.00, 0.00, 0.3);
        mecanumDrive(-0.75, -0.75, -0.75, -0.75, 0.00, 0.62, 0.00, 0.00, 0.00, 0.2);


    }

    public void mecanumDrive(double frontRightPower, double frontLeftPower, double backRightPower, double backLeftPower,
                             double rubberBandPower, double launcherPower, double smallWheelPower, double intakePower,
                             double secondIntakePower, double seconds) {

        timer.reset();

        while(opModeIsActive() && (timer.seconds() <= seconds )) {

            frontRightDrive.setPower(frontRightPower);
            frontLeftDrive.setPower(frontLeftPower);
            backRightDrive.setPower(backRightPower);
            backLeftDrive.setPower(backLeftPower);
            rubberBandWheel.setPower(rubberBandPower);
            turretLauncher.setPower(launcherPower);
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

//            if (Tx < -3 && !touchsensor.leftTouchSensorIsPressed()) {
//                telemetry.addData("Tx", "TurretLeft");
//                turretMotor.setPower(-0.2);
//            }
//
//            else if (Tx > 3 && !touchsensor.leftTouchSensorIsPressed()) {
//                telemetry.addData("Tx", "TurretRight");
//                turretMotor.setPower(0.2);
//            }
//
//            else {
//                telemetry.addData("Tx", "Good");
//                turretMotor.setPower(0);
//            }

        }

    }
}