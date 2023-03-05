package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.commands.*;

public class RobotContainer {
  public static Joystick joystick = new Joystick(0);

  private final Trigger buttonOne = new JoystickButton(joystick, 1);
  private final Trigger buttonTwo = new JoystickButton(joystick, 2);
  private final Trigger buttonThree = new JoystickButton(joystick, 3);
  private final Trigger buttonFour = new JoystickButton(joystick, 4);
  private final Trigger buttonFive = new JoystickButton(joystick, 5);
  private final Trigger buttonSix = new JoystickButton(joystick, 6);

  private final Drivetrain drive = new Drivetrain();
  private final Elevator elevator = new Elevator();
  private final Arm arm = new Arm();
  private final DoubleSolenoid solenoid = new DoubleSolenoid(10, PneumaticsModuleType.REVPH, 0, 1);

  private final DriveWithJoystick driving = new DriveWithJoystick(drive);
  private final Command elevatorBottom = Commands.runOnce(() -> elevator.setSetpoint(0), elevator);
  private final Command elevatorTop = Commands.runOnce(() -> elevator.setSetpoint(34), elevator);
  private final Command toggleSolenoid = Commands.runOnce(() -> solenoid.toggle());
  private final DriveElevator elevatorUp = new DriveElevator(elevator, -0.3);
  private final DriveElevator elevatorDown = new DriveElevator(elevator, 0.3);
  private final Command armTop = Commands.runOnce(() -> arm.setSetpoint(0), arm);
  private final Command arm90 = Commands.runOnce(() -> arm.setSetpoint(-0.25), arm);


  public RobotContainer() {
    configureBindings();
    resetEncoders();

    driving.addRequirements(drive);
    drive.setDefaultCommand(driving);
    // elevator.enable();
    arm.enable();
    solenoid.set(Value.kForward);
  }

  private void configureBindings() {
    // buttonTwo.and(buttonOne.negate()).whileTrue(elevatorUp);
    // buttonOne.and(buttonTwo.negate()).whileTrue(elevatorDown);
    elevator.setSetpoint(0);
    arm.setSetpoint(0);
    buttonThree.onTrue(armTop);
    buttonFour.onTrue(arm90);
    buttonFive.onTrue(toggleSolenoid);
  }

  public void resetEncoders() {
    drive.resetEncoders();
    elevator.resetEncoders();
    arm.resetEncoders();
  }
}
