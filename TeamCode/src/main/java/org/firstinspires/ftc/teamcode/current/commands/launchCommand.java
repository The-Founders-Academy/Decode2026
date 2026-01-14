package org.firstinspires.ftc.teamcode.current.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.current.subsytems.Launcher2026;

public class launchCommand extends CommandBase {
    private final Launcher2026 m_launchSubsystem;
    public launchCommand(Launcher2026 launchSubsystem) {
        m_launchSubsystem = launchSubsystem;
        addRequirements(m_launchSubsystem);
    }

    @Override
    public void execute() {
        m_launchSubsystem.launch();
    }

    @Override
    public void end(boolean interrupted) {
        m_launchSubsystem.resetPower();
    }
}
