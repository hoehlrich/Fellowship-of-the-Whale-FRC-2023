package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class DriveElevator extends CommandBase {
  private final Elevator elevator;
  private final double speed;

  public DriveElevator(Elevator elevator, double speed) {
    this.elevator = elevator;
    this.speed = speed;
    
    addRequirements(elevator);
  }

  @Override
  public void initialize() {
    elevator.left.set(speed);
    elevator.right.set(-speed);
  }

  @Override
  public void end(boolean interrupted) {
    elevator.left.set(0.0);
    elevator.right.set(0.0);
  }

  @Override
  public boolean isFinished(){
    return false;
  }
}