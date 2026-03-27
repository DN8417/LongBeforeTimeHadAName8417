//This is step one of the PIDf tuning process. This if for tuning the kS portion.
// S is for static friction. Using the game controller slowly increase kS until
// the power is just enough for the motor overcome the static friction. Test this
// a few times because it can change as the motor warms up. Once you know at what
// value the motors overcome static friction, then set that in the code as your kS
// and move on to step two. In the code for step two, you will also need to set
// your kS to the value you found.

//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.action.FlyWheel;
//
//@TeleOp
//public class kSTuner extends OpMode {
//
//    FlyWheel flywheel = new FlyWheel();
//    public double kS = 0.09;
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
//        double currentStep = increments[incrementIdx];
//
//        if (gamepad1.dpadUpWasPressed()) { kS += currentStep;}
//        if (gamepad1.dpadUpWasPressed()) { kS -= currentStep;}
//
//        flywheel.setMotorPower(kS);
//
//        telemetry.addData("Step", "%.6f", currentStep);
//        telemetry.addData("kS", "%.6f", kS);
//        telemetry.addData("RPM", flywheel.getRPM());
//        telemetry.addData("Ticks Per Sec", flywheel.getTicksPerSec());
//
//    }
//}
