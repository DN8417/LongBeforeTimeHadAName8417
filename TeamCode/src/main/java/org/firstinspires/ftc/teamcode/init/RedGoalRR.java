//package org.firstinspires.ftc.teamcode.init;
//
//import com.acmerobotics.roadrunner.Action;
//import com.acmerobotics.roadrunner.ParallelAction;
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.SequentialAction;
//import com.acmerobotics.roadrunner.TranslationalVelConstraint;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.acmerobotics.roadrunner.ftc.Actions;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.MecanumDrive;
//import org.firstinspires.ftc.teamcode.action.FieldCentricTest;
//import org.firstinspires.ftc.teamcode.CustomAction.IntakeRR;
//
//@Autonomous
//public class RedGoalRR {
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        IntakeRR intake = new IntakeRR();
//
//        FieldCentricTest drive = new FieldCentricTest();
//
//        waitForStart();
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        IntakeRR.IntakeArtifactsIn()
//                )
//        );
//
//    }
//
//}
