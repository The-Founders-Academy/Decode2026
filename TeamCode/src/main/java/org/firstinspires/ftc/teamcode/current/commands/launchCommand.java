package org.firstinspires.ftc.teamcode.current.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.current.subsytems.Launcher2026;
import com.qualcomm.robotcore.util.ElapsedTime;

public class launchCommand extends CommandBase {
    private final Launcher2026 m_launchSubsystem;
    private final ElapsedTime timer = new ElapsedTime();
    private boolean intakeStarted = false;
    public launchCommand(Launcher2026 launchSubsystem) {
        m_launchSubsystem = launchSubsystem;
        addRequirements(m_launchSubsystem);
    }

    @Override
    public void initialize() {
        timer.reset();
        intakeStarted = false;
        m_launchSubsystem.startLauncher();
    }

    @Override
    public void execute() {
        if(!intakeStarted && timer.seconds() >= 0.75) {
            m_launchSubsystem.startIntake();
            intakeStarted = true;
        }

    }

    @Override
    public void end(boolean interrupted) {
        m_launchSubsystem.resetPower();
    }
}
