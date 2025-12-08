package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensor {
    NormalizedColorSensor color;

    public enum DetectedColor {
        RED,
        GREEN,
        BLUE,
        UNKNOWN

    }

    public void init (@NonNull OpMode opMode) {
       HardwareMap hardwareMap = opMode.hardwareMap;
       color = hardwareMap.get(NormalizedColorSensor.class, "Color Sensor");

    }

    public DetectedColor getDetectedColor(Telemetry telemetry) {
        NormalizedRGBA colors = color.getNormalizedColors();

        float normRed, normGreen, normBlue;
        normRed = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;

        telemetry.addData("Red", normRed);
        telemetry.addData("Green", normGreen);
        telemetry.addData("Blue", normBlue);

        return DetectedColor.UNKNOWN;

    }

}
