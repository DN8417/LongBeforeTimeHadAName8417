package org.firstinspires.ftc.teamcode.init;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.action.mecanumDrive;
import org.firstinspires.ftc.teamcode.action.Intake;

@TeleOp (name = "TeleOp", group = "Main")
public class Teleop extends OpMode {
    mecanumDrive mecanumDrive = new mecanumDrive();
    Intake intake = new Intake();
    boolean mode = true;
    ElapsedTime swapDelay = new ElapsedTime();
    @Override
    public void init() {
        //Initialize our motors
        mecanumDrive.init(this);
        intake.init(this);

    }

    public void start() {
        mecanumDrive.runWithoutEncoder();
        intake.init(this);

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
            mecanumDrive.telemetryOutput();

            intake.takeAndGive(gamepad2.right_bumper, gamepad2.left_bumper);
            intake.startLoading(gamepad2.b);
            intake.finishLoading(gamepad2.dpad_up || gamepad2.dpad_down);
            intake.smallWheelSpin(gamepad2.b);
            //intake.triggerWheels(gamepad2.right_trigger, gamepad2.left_trigger);
            intake.launch(gamepad2.a);

        } else if (!mode) {
            //Controls for mecanumDrive()
            mecanumDrive.slowMode(gamepad2.left_bumper);
            mecanumDrive.setPower(gamepad2.left_stick_x, gamepad2.left_stick_y, gamepad2.right_stick_x);
            mecanumDrive.telemetryOutput();

            intake.takeAndGive(gamepad1.right_bumper, gamepad1.left_bumper);
            intake.startLoading(gamepad1.b);
            intake.finishLoading(gamepad1.dpad_up || gamepad1.dpad_down);
            intake.smallWheelSpin(gamepad1.b);
            //intake.triggerWheels(gamepad1.right_trigger, gamepad1.left_trigger);
            intake.launch(gamepad1.a);

        }

        telemetry.addData("CurrentMode: ", mode ? 0 : 1);

    }
}