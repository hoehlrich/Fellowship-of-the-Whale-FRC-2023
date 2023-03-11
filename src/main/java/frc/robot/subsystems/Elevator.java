package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class Elevator extends PIDSubsystem {

  private CANSparkMax left = new CANSparkMax(5, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(6, MotorType.kBrushless);

  private RelativeEncoder leftEncoder = left.getEncoder();
  private RelativeEncoder rightEncoder = right.getEncoder();

  public Elevator() {
    super(new PIDController(0.05, 0, 0));
    setSetpoint(0);

    left.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    left.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
    right.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    right.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    left.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 0);
    left.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, -36);
    right.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 36);
    right.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, 0);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    double speed = output/4;

    SmartDashboard.putNumber("elevSetpoint", setpoint);
    SmartDashboard.putNumber("elevEncoder", (-leftEncoder.getPosition() + rightEncoder.getPosition()) / 2);
    SmartDashboard.putNumber("elevSpeed", speed);
    SmartDashboard.putNumber("elevOutput", output);

    left.set(-speed);
    right.set(speed);
  }

  @Override
  public double getMeasurement() {
    return (-leftEncoder.getPosition() + rightEncoder.getPosition()) / 2;
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
