package org.firstinspires.ftc.teamcode.current.autos;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.current.commands.DriveToPosition;
import org.firstinspires.ftc.teamcode.current.commands.launchCommand;
import org.firstinspires.ftc.teamcode.current.subsytems.Launcher2026;
import org.firstinspires.ftc.teamcode.shared.mecanum.MecanumConfigs;
import org.firstinspires.ftc.teamcode.shared.mecanum.MecanumDrive;

@Autonomous
public class BasicAuto extends CommandOpMode {

    private MecanumDrive m_mecanumDrive;
    private Launcher2026 m_launcher;

    @Override
    public void initialize() {
        MecanumConfigs mecanumConfigs = new MecanumConfigs().runMode(Motor.RunMode.RawPower);

        m_mecanumDrive = new MecanumDrive(hardwareMap, mecanumConfigs, new Pose2d(-150, -40, Rotation2d.fromDegrees(0)), MecanumDrive.Alliance.BLUE);
        m_launcher = new Launcher2026(hardwareMap);

        CommandScheduler.getInstance().schedule(  new DriveToPosition(m_mecanumDrive, new Pose2d(-100, -40, Rotation2d.fromDegrees(0))).withTimeout(2000) );
    }
}
