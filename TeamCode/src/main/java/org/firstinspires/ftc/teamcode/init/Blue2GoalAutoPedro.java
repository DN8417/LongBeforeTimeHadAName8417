package org.firstinspires.ftc.teamcode.init;

import static org.firstinspires.ftc.teamcode.pedroPathing.Constants.pathConstraints;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.drivetrain.Drivetrain;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.drivetrains.Mecanum;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.TwoWheelConstants;
import com.pedropathing.ftc.localization.localizers.TwoWheelLocalizer;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.localization.Localizer;
import com.pedropathing.paths.PathChain;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "Pedro Pathing Autonomous", group = "Autonomous")
@Configurable
public class Blue2GoalAutoPedro extends OpMode {

    private Follower follower;
    private Paths paths;
    private int pathState = 0;
    @Override
    public void start() {
        pathState = 0;

        if (follower != null) {
            follower.followPath(paths.toShoot);
        }
    }
    @Override
    public void init() {

        FollowerConstants followerConstants = new FollowerConstants();
        MecanumConstants mecanumConstants = new MecanumConstants();

        Localizer localizer =
                new TwoWheelLocalizer(
                        hardwareMap,
                        Constants.localizerConstants
                );
        mecanumConstants.leftFrontMotorName = "Front Left";
        mecanumConstants.rightFrontMotorName = "Front Right";
        mecanumConstants.leftRearMotorName = "Back Left";
        mecanumConstants.rightRearMotorName = "Back Right";

        Drivetrain drivetrain =
                new Mecanum(hardwareMap, mecanumConstants);

        PathConstraints pathConstraints = new PathConstraints(
                50,
                50,
                180,
                180
        );

        follower = new Follower(
                followerConstants,
                localizer,
                drivetrain,
                pathConstraints
        );

        follower.setStartingPose(new Pose(22, 120));

        paths = new Paths(follower);
    }
    @Override
    public void loop() {
        if (follower != null) {
            follower.update();
        }
    }

    // =========================
    // PATHS
    // =========================
    public static class Paths {

        public PathChain toShoot;
        public PathChain toStack1;
        public PathChain backToShoot1;
        public PathChain toStack2;
        public PathChain backToShoot2;
        public PathChain park;

        public Paths(Follower follower) {

            toShoot = follower.pathBuilder()
                    .addPath(new BezierLine(
                            new Pose(22, 120),
                            new Pose(45.67, 95.1)
                    ))
                    .build();

            toStack1 = follower.pathBuilder()
                    .addPath(new BezierLine(
                            new Pose(45.67, 95.1),
                            new Pose(23.47, 82.35)
                    ))
                    .build();

            backToShoot1 = follower.pathBuilder()
                    .addPath(new BezierLine(
                            new Pose(23.47, 82.35),
                            new Pose(45.67, 95.1)
                    ))
                    .build();

            toStack2 = follower.pathBuilder()
                    .addPath(new BezierLine(
                            new Pose(45.67, 95.1),
                            new Pose(23.69, 58.18)
                    ))
                    .build();

            backToShoot2 = follower.pathBuilder()
                    .addPath(new BezierLine(
                            new Pose(23.69, 58.18),
                            new Pose(45.67, 95.1)
                    ))
                    .build();

            park = follower.pathBuilder()
                    .addPath(new BezierLine(
                            new Pose(45.67, 95.1),
                            new Pose(21.71, 86.74)
                    ))
                    .build();
        }
    }
}