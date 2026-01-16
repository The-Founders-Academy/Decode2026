package org.firstinspires.ftc.teamcode.current.opmodes;

import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.current.subsytems.Launcher2026;
import org.firstinspires.ftc.teamcode.shared.mecanum.MecanumConfigs;
import org.firstinspires.ftc.teamcode.shared.mecanum.MecanumDrive;

@TeleOp()
//@Disabled
public class RobotRelativeDrive extends LinearOpMode {


    public MecanumDrive m_mecanumDrive;

    public MotorEx m_frontLeft, m_frontRight, m_backLeft, m_backRight;
    private Launcher2026 launchSubsystem;

    @Override
    public void runOpMode() {
        launchSubsystem = new Launcher2026(hardwareMap);


        MecanumConfigs configs = new MecanumConfigs().runMode(MotorEx.RunMode.RawPower);

        m_mecanumDrive = new MecanumDrive(hardwareMap, configs, new Pose2d(0, 0, Rotation2d.fromDegrees(90)), MecanumDrive.Alliance.RED);


        // Initializes the drive motors to the correct hardwaremap
        m_frontLeft = new MotorEx(hardwareMap,"fL", Motor.GoBILDA.RPM_312);
        m_frontRight = new MotorEx(hardwareMap, "fR", Motor.GoBILDA.RPM_312);
        m_backLeft = new MotorEx(hardwareMap, "bL", Motor.GoBILDA.RPM_312);
        m_backRight = new MotorEx(hardwareMap, "bR", Motor.GoBILDA.RPM_312);



        m_frontLeft.setRunMode(Motor.RunMode.RawPower);
        m_frontRight.setRunMode(Motor.RunMode.RawPower);
        m_backLeft.setRunMode(Motor.RunMode.RawPower);
        m_backRight.setRunMode(Motor.RunMode.RawPower);


        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);


        telemetry.addData("Status:", "Initialized");
        telemetry.update();

        /* Send telemetry message to signify robot waiting */
        telemetry.addLine("Robot Ready.");
        telemetry.update();
        /* Wait for the game driver to press play */
        waitForStart();

        /* Run until the driver presses stop */
        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;



            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            m_frontLeft.set(frontLeftPower);
            m_backLeft.set(backLeftPower);
            m_frontRight.set(frontRightPower);
            m_backRight.set(backRightPower);

            // DRIVER COMMANDS

            if (gamepad1.a) {
                imu.resetYaw();
            }

//            if(gamepad1.x) {
//                launchSubsystem.launch();
//            } else {
//                launchSubsystem.resetPower();
//            }



        }
    }
}