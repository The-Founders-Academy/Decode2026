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
        MecanumConfigs mecanumConfigs =
                new MecanumConfigs().runMode(Motor.RunMode.RawPower);

        double startHeadingDeg = 45.0;

        Pose2d startPose =
                new Pose2d(76.6, 159.3, Rotation2d.fromDegrees(startHeadingDeg));

        m_mecanumDrive =
                new MecanumDrive(
                        hardwareMap,
                        mecanumConfigs,
                        startPose,
                        MecanumDrive.Alliance.BLUE);

        m_launcher = new Launcher2026(hardwareMap);

        Rotation2d heading = startPose.getRotation();
        double distance = 45.0;

        Pose2d targetPose =
                new Pose2d(
                        startPose.getX() - distance * heading.getCos(),
                        startPose.getY() - distance * heading.getSin(),
                        heading);

        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new DriveToPosition(m_mecanumDrive, targetPose).withTimeout(1500L),

                        new launchCommand(m_launcher).withTimeout(2000L)
                )
        );
    }
}