package org.firstinspires.ftc.teamcode.current.subsytems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Launcher2026 extends SubsystemBase {
    public DcMotor dih;


    @Config
    public static class Lift2025Params {
        public static int LIFT_MAX = 1220;
        public static int LIFT_COLLAPSED = 5;
        public static double Kp = 0.02;

    }

    public Launcher2026(final HardwareMap hardwaremap) {
        dih = hardwaremap.get(DcMotor.class, "launch");
        dih.setDirection(DcMotorSimple.Direction.REVERSE);
        dih.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dih.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void powerLauncher(int power) {

        dih.setPower(power);
    }

    @Override
    public void periodic() {



    }



}
