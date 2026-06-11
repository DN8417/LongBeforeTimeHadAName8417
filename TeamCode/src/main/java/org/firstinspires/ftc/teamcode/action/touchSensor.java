package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class touchSensor {
    private TouchSensor leftTouchSensor;
    private TouchSensor rightTouchSensor;
    private TouchSensor endgameSensor;

    public void init(@NonNull OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        leftTouchSensor = hardwareMap.get(TouchSensor.class, "Left Touch Sensor");
        rightTouchSensor = hardwareMap.get(TouchSensor.class, "Right Touch Sensor");
        endgameSensor = hardwareMap.get(TouchSensor.class, "Endgame Sensor");

    }

    public boolean leftTouchSensorIsPressed () {
        return leftTouchSensor.isPressed();
    }
    public boolean rightTouchSensorIsPressed () {
        return rightTouchSensor.isPressed();
    }
    public boolean endgameTouchSensorPressed() {
        return endgameSensor.isPressed();
    }

}
