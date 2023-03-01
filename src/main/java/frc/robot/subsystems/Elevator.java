package frc.robot.subsystems;

import frc.robot.RobotContainer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.SoftLimitDirection;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {

  public CANSparkMax left;
  public CANSparkMax right;

  private RelativeEncoder leftEncoder;
  private RelativeEncoder rightEncoder;

  public Elevator() {
    left = new CANSparkMax(5, MotorType.kBrushless);
    right = new CANSparkMax(6, MotorType.kBrushless);

    leftEncoder = left.getEncoder();
    rightEncoder = right.getEncoder();

    left.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    left.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
    right.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    right.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    left.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 0);
    left.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, -32);
    right.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 32);
    right.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, 0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("leftElevator", leftEncoder.getPosition());
    SmartDashboard.putNumber("rightElevator", rightEncoder.getPosition());
  }
  
  public void stop() {
    left.stopMotor();
    right.stopMotor();
  }

  public void resetEncoders() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }
}
