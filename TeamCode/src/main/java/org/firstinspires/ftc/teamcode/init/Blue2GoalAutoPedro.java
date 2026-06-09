package org.firstinspires.ftc.teamcode.init;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Pedro Pathing Autonomous", group = "Autonomous")
@Configurable
public class Blue2GoalAutoPedro extends OpMode {

    private Follower follower;
    private Paths paths;
    private int pathState = 0;

    @Override
    public void init() {

        // IMPORTANT: keep your real follower init here
        // follower = Constants.createFollower(hardwareMap);
        // OR whatever your project actually uses

        paths = new Paths(follower);
    }

    @Override
    public void start() {
        pathState = 0;
    }

    @Override
    public void loop() {
        if (follower != null) {
            follower.update();
        }

        // state machine goes here (not required for compile fix)
    }

    // =========================
    // FIXED PATHS CLASS
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
                    .addPath(
                            new BezierLine(
                                    new Pose(22, 120),
                                    new Pose(45.67, 95.1)
                            )
                    )
                    .build();

            toStack1 = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(45.67, 95.1),
                                    new Pose(23.47, 82.35)
                            )
                    )
                    .build();

            backToShoot1 = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(23.47, 82.35),
                                    new Pose(45.67, 95.1)
                            )
                    )
                    .build();

            toStack2 = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(45.67, 95.1),
                                    new Pose(23.69, 58.18)
                            )
                    )
                    .build();

            backToShoot2 = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(23.69, 58.18),
                                    new Pose(45.67, 95.1)
                            )
                    )
                    .build();

            park = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(45.67, 95.1),
                                    new Pose(21.71, 86.74)
                            )
                    )
                    .build();
        }
    }
}