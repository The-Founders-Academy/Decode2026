package org.firstinspires.ftc.teamcode.current.subsytems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class Launcher2026 extends SubsystemBase {
    private final CRServo intake1;
    private final CRServo intake2;

    private final DcMotor launcher;
    public Launcher2026(final HardwareMap hardwareMap) {
        intake1 = hardwareMap.get(CRServo.class, "wrist");
        intake2 = hardwareMap.get(CRServo.class, "wrist2");
        launcher = hardwareMap.get(DcMotor.class, "launcher");
   }

   public void launch() {
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launcher.setPower(0.2);

        intake1.setPower(1);
        intake2.setPower(-1);
   }

   public void resetPower() {
        launcher.setPower(0);

        intake1.setPower(0);
        intake2.setPower(0);
   }
}
