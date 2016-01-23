package remoteDragRacer;

import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;
import lejos.hardware.Button;

public class RemoteController {
	
	UnregulatedMotor wheelMotorB = new UnregulatedMotor(MotorPort.B);
	UnregulatedMotor wheelMotorC = new UnregulatedMotor(MotorPort.C);
	EV3MediumRegulatedMotor A = new EV3MediumRegulatedMotor(MotorPort.A);


	EV3IRSensor rc = new EV3IRSensor(SensorPort.S4);
	int rcChannel = 0;
	byte [] cmds = new byte[4];
	/*
	 * IR Sensor buttons
	 * 1 TOP-LEFT 
	 * 2 BOTTOM-LEFT 
	 * 3 TOP-RIGHT 
	 * 4 BOTTOM-RIGHT
	 */
 	Button topLeft;
	Button buttomLeft;
	Button topRight;
	Button buttomRight;

	public int getRCInput(){
		return rc.getRemoteCommand(rcChannel);
	}
	
	public void idleInput(){
		A.rotate(0);
	}
	
	public void turnRight(){
		A.rotate(-170, true);		
	}
	public void turnLeft(){
		A.rotate(170, true);
	}
	
	public void forward(){
		
		wheelMotorB.setPower(100);
		wheelMotorC.setPower(100);
		
		wheelMotorB.forward();
		wheelMotorC.forward();
		
		if(getRCInput()==0){
			stopMotors();
		}
				
	}
	private void stopMotors() {
		// TODO Auto-generated method stub
		wheelMotorB.flt();
		wheelMotorC.flt();			
		//wheelMotorB.stop();
		//wheelMotorC.stop();
		
	}

	public void closeConnection(){
		
		A.close();
		wheelMotorB.close();
		wheelMotorC.close();
		
	}
	
	public void reverse(){
		wheelMotorB.setPower(100);
		wheelMotorC.setPower(100);
		
		wheelMotorB.backward();
		wheelMotorC.backward();

		if(getRCInput()==0){
			stopMotors();
		}		
		
	}
}
