package org.firstinspires.ftc.teamcode.current.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.current.subsytems.Launcher2026;

public class resetLaunchCommand extends CommandBase {
    public Launcher2026 m_launchSubsystem;
    public resetLaunchCommand(Launcher2026 launchSubsystem) {
        m_launchSubsystem = launchSubsystem;
    }

    public void initialize() {
        m_launchSubsystem.resetPower();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
