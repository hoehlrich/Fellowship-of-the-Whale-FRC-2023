package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class DriveWithJoystick extends CommandBase {
private final Drivetrain drive;

  public DriveWithJoystick(Drivetrain dt){
    drive = dt;
    addRequirements(drive);
    
  }
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    drive.driveWithJoystick();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished(){
    return false;
  }
}