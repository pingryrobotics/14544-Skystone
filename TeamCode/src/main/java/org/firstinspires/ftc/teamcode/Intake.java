/*
    This code is all of the functions we created for each action our robot can do
    This code is used in DriveMain.java, where we have our set of if statements to define
    what happens when each button is pressed
*/

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

// Declares the Motors and Servo Parts
public class Intake {
    private DcMotor left;
    private DcMotor right;
    private Servo wrist;
    private Servo finger;
    private Servo lat;
    private Servo pushbarLeft;
    private Servo pushbarRight;
    private Servo hornLeft;
    private Servo hornRight;
    public Intake(HardwareMap hardwareMap){
        left = hardwareMap.get(DcMotor.class, "ShoulderMotor");
        right = hardwareMap.get(DcMotor.class, "WinchMotor");
        wrist = hardwareMap.get(Servo.class, "WristServo");
        finger = hardwareMap.get(Servo.class, "FingerServo");
        lat = hardwareMap.get(Servo.class, "LatServo");
       pushbarRight = hardwareMap.get(Servo.class, "PushbarServoRight");
       pushbarLeft = hardwareMap.get(Servo.class, "PushbarServoLeft");
       hornRight = hardwareMap.get(Servo.class, "HornServoR");
       hornLeft = hardwareMap.get(Servo.class, "HornServoL");
    }
    
    // Raises the arm
    public void raisearm(){
        left.setPower(0.5);
    }    
    
    // Lowers the arm
    public void retractarm(){
        left.setPower(-0.3);
    }
    
    // Retracts the arm
    public void moveArmIn(){
        right.setPower (0.5);
    }
    
    // Extends the arm
    public void moveArmOut(){
        right.setPower (-0.5);
    }
    
    // Pinches with the finger
    public void finger(){
        finger.setPosition (0.9);
    }
    
    // Moves the wrist to face a block
    public void wrist(){
        wrist.setPosition (0.1);
    }
    
    // Stops the winch
    public void stopwinch (){
        right.setPower (0); 
    }
    
    // Kill switch for the arm in case anything goes wrong
    public void stopall (){
        left.setPower(-0.1);
        right.setPower(0);
        wrist.setPosition(0);
    }
    
    // Resets the wrist to original position, parallel to block on ground
    public void wristreset (){
        wrist.setPosition (0);
    }
    
    // Moves the finger out so it can "un-pinch" the block
    public void fingerreset (){
       finger.setPosition (0); 
    }
    
    //Kill Switch for the wrist, for precision movement
    public void latstop (){
        lat.setPosition (0.5);
    }
    
    //Function to move around the wrist
    public void latmovement (){
        lat.setPosition (0.55);
    }
    
    //The pushers extend, allowing thet robot to move bricks
    public void pushersOut (){
        pushbarLeft.setPosition(0.4);
        pushbarRight.setPosition(0.1);
    }
       
    //The pushers go inwards, allowing the robot to get closer for stacking
    public void pushersIn (){
        pushbarLeft.setPosition(0);
        pushbarRight.setPosition(0);
    }
    
    //The servos in the front of the robot that are used for autonomous go down
    public void horndeploy(){
        hornRight.setPosition(0.9);
        hornLeft.setPosition(0.2);
    }
    
    //Now the servos go back up again
    public void hornretract(){
        hornRight.setPosition(0.3);
        hornLeft.setPosition(0.85);
    }
    public void latreverse(){
        lat.setPosition (0.45);
    }
}