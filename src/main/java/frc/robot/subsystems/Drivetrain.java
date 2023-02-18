package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends SubsystemBase {

  DifferentialDrive drive;

  public Drivetrain() {
    MotorController leftMaster = new CANSparkMax(1, MotorType.kBrushless);
    MotorController leftSlave = new CANSparkMax(2, MotorType.kBrushless);
    MotorController rightMaster = new CANSparkMax(3, MotorType.kBrushless);
    MotorController rightSlave = new CANSparkMax(4, MotorType.kBrushless);

    MotorControllerGroup leftMotors = new MotorControllerGroup(leftMaster, leftSlave);
    MotorControllerGroup rightMotors = new MotorControllerGroup(rightMaster, rightSlave);

    rightMotors.setInverted(true);

    drive = new DifferentialDrive(leftMotors, rightMotors);
  }

  @Override
  public void periodic() {}

  public void driveWithJoystick(Joystick controller) {
    drive.arcadeDrive(-controller.getY(), -controller.getX());
  }

  public void driveForward(int speed) {
    drive.tankDrive(speed, speed);
  }

  public void stop() {
    drive.stopMotor();
  }
}
