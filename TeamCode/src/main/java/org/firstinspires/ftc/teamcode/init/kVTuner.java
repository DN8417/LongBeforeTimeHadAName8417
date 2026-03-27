//// This is step two of the PIDf tuning process. In this step you slowly increase the kV
//// until your flywheel is spinning at the correct speed.
//
//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.action.FlyWheel;
//
//@TeleOp
//public class kVTuner extends OpMode {
//
//    FlyWheel flywheel = new FlyWheel();
//    public double kV = 0.000171;
//    public double kS = 0.09; // insert the value found for kS in step 1
//    public double goalRPM = 1000;
//
//    double[] increments = {0.000001, 0.00001, 0.0001, 0.001, 0.01};
//    int incrementIdx = 4; //starts at the fourth increment
//
//    @Override
//    public void init(){
//        flywheel.init(hardwareMap);
//    }
//
//    @Override
//    public void loop(){
//        if (gamepad1.dpadRightWasPressed() && incrementIdx < 4) {
//            incrementIdx++;
//        }
//        else if (gamepad1.dpadLeftWasPressed() && incrementIdx > 0) {
//            incrementIdx--;
//        }
//
//        if (gamepad1.a) {
//            goalRPM = 2000;
//        }
//        else if (gamepad1.b) {
//            goalRPM = 1500;
//        }
//
//        double currentStep = increments[incrementIdx];
//
//        if (gamepad1.dpadUpWasPressed()) { kV += currentStep;}
//        if (gamepad1.dpadUpWasPressed()) { kV -= currentStep;}
//
//        double power = (kV * goalRPM) + kS;
//        flywheel.setMotorPower(power);
//
//        telemetry.addData("Step", "%.6f", currentStep);
//        telemetry.addData("kV", "%.6f", kV);
//        telemetry.addData("RPM", flywheel.getRPM());
//        telemetry.addData("Ticks Per Sec", flywheel.getTicksPerSec());
//
//    }
//}