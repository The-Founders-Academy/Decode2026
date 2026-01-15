package org.firstinspires.ftc.teamcode.current.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.current.commands.DriverRelativeDrive;
import org.firstinspires.ftc.teamcode.shared.mecanum.MecanumConfigs;
import org.firstinspires.ftc.teamcode.shared.mecanum.MecanumDrive;
import org.firstinspires.ftc.teamcode.shared.util.CommandGamepad;

@TeleOp()
public class BlueFieldRelative extends CommandOpMode {

    private MecanumDrive m_mecanumDrive;
    private CommandGamepad m_driver;

    @Override
    public void initialize() {

        MecanumConfigs configs = new MecanumConfigs().runMode(MotorEx.RunMode.RawPower);

        m_mecanumDrive = new MecanumDrive(hardwareMap, configs, new Pose2d(42.38, 161, Rotation2d.fromDegrees(270)), MecanumDrive.Alliance.BLUE);


        m_driver = new CommandGamepad(gamepad1, 0.2, 0.2);
        m_mecanumDrive.setDefaultCommand(new DriverRelativeDrive(m_mecanumDrive, m_driver));

        // Driver Commands
        m_driver.buttonA().whenPressed(new InstantCommand(() -> m_mecanumDrive.resetHeading()));





    }
}
