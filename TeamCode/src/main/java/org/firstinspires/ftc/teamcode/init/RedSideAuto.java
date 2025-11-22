package org.firstinspires.ftc.teamcode.init;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Red Side", group ="Autos")
public class RedSideAuto extends LinearOpMode {

    public DcMotor frontRightDrive;
    public DcMotor frontLeftDrive;
    public DcMotor backRightDrive;
    public DcMotor backLeftDrive;
    public DcMotor rubberBandWheel;
    public DcMotor turretLauncher;
    public CRServo smallWheel;
    public CRServo intakePartTwo;
    public DcMotor intakeMotor;

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

        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rubberBandWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        turretLauncher.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        // Forward
        mecanumDrive(0.75, 0.75, 0.75, 0.75, 0.00, 1.00, 0.00, 0.00, 0.00, 0.7);
        // Moves right
        mecanumDrive(-0.75, 0.75, 0.75, -0.75, 0.00, 1.00, 0.00, 0.00, 0.00, 0.6);
        // Forward again
        mecanumDrive(0.75, 0.75, 0.75, 0.75, 0.00, 1.00, 0.00, 0.00, 0.00, 0.3);
        // Turns left
        mecanumDrive(0.75, -0.75, 0.75, -0.75, 0.00, 1.00, 0.00, 0.00, 0.00, 0.35);
        // Buffer time before shooting
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, 1.00, 0.00, 0.00, 0.00, 0.5);
        // Shooting first 2 artifacts
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 1.00, 1.00, -1.00, 0.00, -0.5, 1.0);
        // Charging up to shoot again
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, 1.00, 0.00, 0.00, 0.00, 1.5);
        // Shoots last artifact
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 1.00, 1.00, -1.00, 0.00, -0.5, 1.0);
        // Stalling a bit
        mecanumDrive(0.00, 0.00, 0.00, 0.00, 0.00, 1.00, 0.00, 0.00, 0.00, 1.5);
        // Moves right again
        mecanumDrive(-0.75, 0.75, 0.75, -0.75, 0.00, 1.00, 0.00, 0.00, 0.00, 0.4);
        // Turns Right
        mecanumDrive(-0.75, 0.75, -0.75, 0.75, 0.00, 1.00, 0.00, 0.00, 0.00, 0.4);
        // Moves forward and intakes more artifacts
        mecanumDrive(0.75, 0.75, 0.75, 0.75, 0.00, 0.00, -1.00, 1.00, -0.5, 0.5);

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

        }

    }
}
