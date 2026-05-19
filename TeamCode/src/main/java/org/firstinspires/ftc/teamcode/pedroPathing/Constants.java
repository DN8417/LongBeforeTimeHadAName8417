package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.TwoWheelConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(13.33562)
            .headingPIDFCoefficients(new PIDFCoefficients(1, 0, 0.05, 0.01))
            .translationalPIDFCoefficients(new PIDFCoefficients(0.1, 0, 0.01, 0.04))
            .forwardZeroPowerAcceleration(-36.610346876398026)
            .forwardZeroPowerAcceleration(-59.93356521686264);

    public static PathConstraints pathConstraints = new PathConstraints(0.99,
            100,
            2,
            1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .twoWheelLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .build();
    }

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("Front Right")
            .rightRearMotorName("Back Right")
            .leftRearMotorName("Back Left")
            .leftFrontMotorName("Front Left")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .xVelocity(82.6293639215)
            .yVelocity(35.48224743780934);

    public static TwoWheelConstants localizerConstants = new TwoWheelConstants()
            .forwardEncoder_HardwareMapName("Front Right")
            .strafeEncoder_HardwareMapName("Back Left")
            .IMU_HardwareMapName("imu")
            .strafePodX(56.50144495738053)
            .forwardPodY(-3.104809075238)
            .forwardTicksToInches(0.004)
            .strafeTicksToInches(0.0027)
            .forwardEncoderDirection(Encoder.FORWARD)
            .strafeEncoderDirection(Encoder.REVERSE)
            .IMU_Orientation(
                    new RevHubOrientationOnRobot(
                            RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                            RevHubOrientationOnRobot.UsbFacingDirection.UP
                    )
            );
}
