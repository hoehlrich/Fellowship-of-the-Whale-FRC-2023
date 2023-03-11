package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class Arm extends PIDSubsystem {

  private CANSparkMax left = new CANSparkMax(7, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(8, MotorType.kBrushless);

  private RelativeEncoder leftEncoder = left.getEncoder();
  private RelativeEncoder rightEncoder = right.getEncoder();

  public Arm() {
    super(new PIDController(0.2, 0.01, 0.01));
    setSetpoint(0);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    double speed = output*0.8;

    SmartDashboard.putNumber("armSetpoint", setpoint);
    SmartDashboard.putNumber("armRot", -leftEncoder.getPosition());
    SmartDashboard.putNumber("armSpeed", speed);

    left.set(-speed);
    right.set(speed);
  }

  @Override
  public double getMeasurement() {
    return -leftEncoder.getPosition();
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
