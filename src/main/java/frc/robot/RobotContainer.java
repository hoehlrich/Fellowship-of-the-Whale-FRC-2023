package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.commands.*;

public class RobotContainer {
  public static Joystick joystick = new Joystick(0);

  private final Trigger buttonOne = new JoystickButton(joystick, 1);
  private final Trigger buttonTwo = new JoystickButton(joystick, 2);
  private final Trigger buttonFive = new JoystickButton(joystick, 5);

  private final Drivetrain drive = new Drivetrain();
  private final Elevator elevator = new Elevator();

  private final DriveWithJoystick driving = new DriveWithJoystick(drive);
  private final DriveElevator elevatorUp = new DriveElevator(elevator, -0.3);
  private final DriveElevator elevatorDown = new DriveElevator(elevator, 0.3);

  public RobotContainer() {
    configureBindings();
    resetEncoders();

    driving.addRequirements(drive);
    drive.setDefaultCommand(driving);
  }

  private void configureBindings() {
    buttonTwo.and(buttonOne.negate()).whileTrue(elevatorUp);
    buttonOne.and(buttonTwo.negate()).whileTrue(elevatorDown);
  }

  public void resetEncoders() {
    drive.resetEncoders();
    elevator.resetEncoders();
  }
}
