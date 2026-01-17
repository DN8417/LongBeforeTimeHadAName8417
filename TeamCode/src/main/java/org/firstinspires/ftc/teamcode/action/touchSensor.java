package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class touchSensor {
    private TouchSensor leftTouchSensor;
    private TouchSensor rightTouchSensor;

    public void init(@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        leftTouchSensor = hardwareMap.get(TouchSensor.class, "Left Touch Sensor");
        rightTouchSensor = hardwareMap.get(TouchSensor.class, "Right Touch Sensor");
//        leftTouchSensor.setMode(DigitalChannel.Mode.INPUT);
//        rightTouchSensor.setMode(DigitalChannel.Mode.INPUT);

    }

    public boolean leftTouchSensorIsPressed () {
        return leftTouchSensor.isPressed();
    }
    public boolean rightTouchSensorIsPressed () {
        return rightTouchSensor.isPressed();
    }

}
