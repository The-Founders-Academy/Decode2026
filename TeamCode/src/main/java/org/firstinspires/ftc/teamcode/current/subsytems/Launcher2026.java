package org.firstinspires.ftc.teamcode.current.subsytems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Launcher2026 extends SubsystemBase {
    private final CRServo intake1;
    private final CRServo intake2;

    private final DcMotor launcher;


    public Launcher2026(final HardwareMap hardwareMap) {
        intake1 = hardwareMap.get(CRServo.class, "wrist");
        intake2 = hardwareMap.get(CRServo.class, "wrist2");
        launcher = hardwareMap.get(DcMotor.class, "launcher");

        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
   }

    public void startLauncher() {
        launcher.setPower(0.91);
    }

   public void startIntake() {
        intake1.setPower(0.5);
        intake2.setPower(0.5);
   }

   public void resetPower() {
        launcher.setPower(0);
        intake1.setPower(0);
        intake2.setPower(0);
   }
}
