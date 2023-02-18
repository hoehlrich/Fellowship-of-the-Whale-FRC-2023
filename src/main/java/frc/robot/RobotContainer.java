package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;

import frc.robot.commands.DriveWithJoystick;

public class RobotContainer {
  private final Drivetrain drive = new Drivetrain();
  private final DriveWithJoystick driving = new DriveWithJoystick(drive);

  public static Joystick joystick;


  public RobotContainer() {
    configureBindings();

    driving.addRequirements(drive);
    joystick = new Joystick(0);
    drive.setDefaultCommand(driving);
  }

  private void configureBindings() {
  }
}
