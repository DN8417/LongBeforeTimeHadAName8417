package org.firstinspires.ftc.teamcode.init;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.TelemetryManager;
import com.bylazar.telemetry.PanelsTelemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.follower.Follower;
import com.pedropathing.paths.PathChain;
import com.pedropathing.geometry.Pose;

@Autonomous(name = "Pedro Pathing Autonomous", group = "Autonomous")
@Configurable // Panels
public class RedGoalAutoPedro extends OpMode {
    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    public Follower follower; // Pedro Pathing follower instance
    private int pathState; // Current autonomous path state (state machine)
    private Paths paths; // Paths defined in the Paths class

    @Override
    public void init() {
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(72, 8, Math.toRadians(90)));

        paths = new Paths(follower); // Build paths

        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);
    }

    @Override
    public void loop() {
        follower.update(); // Update Pedro Pathing
        pathState = autonomousPathUpdate(); // Update autonomous state machine

        // Log values to Panels and Driver Station
        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
    }

    public static class Paths {
        public PathChain MainChain;

        public Paths(Follower follower) {
            MainChain = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(116.203, 122.474),
                                    new Pose(117.741, 93.127)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(90))
                    .addPath(
                            new BezierLine(
                                    new Pose(117.741, 93.127),
                                    new Pose(117.835, 75.334)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(90))
                    .addPath(
                            new BezierLine(
                                    new Pose(117.835, 75.334),
                                    new Pose(89.744, 99.016)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(45))
                    .addPath(
                            new BezierLine(
                                    new Pose(89.744, 99.016),
                                    new Pose(117.897, 69.241)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(90))
                    .addPath(
                            new BezierLine(
                                    new Pose(117.897, 69.241),
                                    new Pose(118.024, 52.350)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(90))
                    .addPath(
                            new BezierLine(
                                    new Pose(118.024, 52.350),
                                    new Pose(89.887, 99.037)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(45))
                    .addPath(
                            new BezierLine(
                                    new Pose(89.887, 99.037),
                                    new Pose(117.905, 46.999)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(90))
                    .addPath(
                            new BezierLine(
                                    new Pose(117.905, 46.999),
                                    new Pose(118.111, 28.735)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(90))
                    .addPath(
                            new BezierLine(
                                    new Pose(118.111, 28.735),
                                    new Pose(89.796, 99.167)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(45))
                    .addPath(
                            new BezierLine(
                                    new Pose(89.796, 99.167),
                                    new Pose(119.424, 103.430)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(45))
                    .build();
        }
    }

    public int autonomousPathUpdate() {
        // Add your state machine Here
        // Access paths with paths.pathName
        // Refer to the Pedro Pathing Docs (Auto Example) for an example state machine
        return 0;
    }
}

