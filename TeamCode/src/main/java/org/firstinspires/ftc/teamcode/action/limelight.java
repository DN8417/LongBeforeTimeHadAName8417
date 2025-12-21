//package org.firstinspires.ftc.teamcode.action;
//
//import androidx.annotation.NonNull;
//
//import com.qualcomm.hardware.limelightvision.LLResult;
//import com.qualcomm.hardware.limelightvision.Limelight3A;
//import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.IMU;
//
//import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
//import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
//import org.firstinspires.ftc.teamcode.action.Intake;
//
//import java.lang.annotation.Target;
//
//public class limelight {
//    private Limelight3A limelight;
//    private IMU imu;
//    private DcMotor turretMotor;
//
//
//    private double distance;
//
//    public void init(@NonNull OpMode opMode) {
//        HardwareMap hardwareMap = opMode.hardwareMap;
//
//        limelight = hardwareMap.get(Limelight3A.class, "limelight");
//        turretMotor= hardwareMap.get(DcMotor.class, "Turret Motor");
//        limelight.pipelineSwitch(0); // april tag #11 pipeline
//        turretMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        imu= hardwareMap.get(IMU.class, "imu");
//        RevHubOrientationOnRobot revHubOrientationOnRobot= new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
//                RevHubOrientationOnRobot.UsbFacingDirection.UP);
//        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));
//    }
//
//
//
//    public double getDistanceFromTage(double ta) {
//        double scale= 3.085408;  // y value in equation
//        double distance= (scale / ta);
//        return distance;
//
//    }
//
//
//}