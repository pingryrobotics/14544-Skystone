/*
    This code is where we say what happens when a button is pressed on the contr
*/

package org.firstinspires.ftc.teamcode;

//Import 
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "DriveMain", group = "Iterative Opmode")
public class DriveMain extends OpMode {
    
    //set the motors
    //imports the DC motor 
    private Intake intake;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftRear = null;
    private DcMotor rightRear = null;
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    
   
    public void init(){
        intake = new Intake (hardwareMap);
        
        //names the motors
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status","Initialized");

    }
    public void loop(){
        // Helps with the drive controls, determines direction of wheels
        double theta = Math.atan2(-gamepad1.left_stick_y, -gamepad1.left_stick_x);
        double magnitude = Math.hypot(gamepad1.left_stick_x,gamepad1.left_stick_y);
        double turn = -Range.clip(gamepad1.right_stick_x, -1, 1);
        double rf = Math.sin(theta + (Math.PI/4)) * magnitude;
        double lf = Math.sin(theta - (Math.PI/4)) * magnitude;
        double rb = Math.sin(theta - (Math.PI/4)) * magnitude;
        double lb = Math.sin(theta + (Math.PI/4)) * magnitude;
        
        //Also helps set direction for the wheels
        leftRear.setPower(lb - turn);
        rightRear.setPower(rb + turn);
        leftFront.setPower(lf - turn);
        rightFront.setPower(rf + turn);
        
        /*Intake
        States that if the right bumper is pressed (on Gamepad 2)
        the arm should raise itself
        Please see Intake.java for function of "raisearm"*/
        if (gamepad2.right_bumper){
            intake.raisearm();
        }
        
        //States that if the left bumper is pressed (on Gamepad 2)
        //the arm should go down
        //Please see Intake.java for function of "retractarm"
        if (gamepad2.right_trigger >= 0.7){
            intake.retractarm();
        }
        
        //States that if the left trigger is pressed (on Gamepad 2)
        //the arm should move in 
        //Please see Intake.java for function of "moveArmIn"
        if (gamepad2.left_bumper){
            intake.moveArmIn();
        }
        
        //States that if the left trigger is pressed (on Gamepad 2)
        //the arm should move out 
        //Please see Intake.java for function of "moveArmOut"
        if (gamepad2.left_trigger >= 0.7){
            intake.moveArmOut ();
        }
        
        //Kill switch
        //if the dpad down is pressed (on Gamepad 2)
        //every function (on the arm) will stop
        if (gamepad2.dpad_down){
            intake.stopall ();
        }
        
        //States that if the dpad left is pressed (on Gamepad 2)
        //the wrist will move
        //Please see Intake.java for function of "wrist"
        if (gamepad2.dpad_left){
            intake.wrist ();
        }
        
        //States that if the dpad right is pressed (on Gamepad 2)
        //the finger will move
        if (gamepad2.dpad_right){
            intake.finger();
        }

        if (gamepad2.dpad_up){
            intake.latmovement();
        }
        
        if (gamepad2.a){
            intake.wristreset ();
        }
        if (gamepad2.b){
            intake.fingerreset ();
        }
        if (gamepad2.a){
            intake.latstop ();
        }
        
        if (gamepad2.y){
            intake.latreverse ();
        }
        
    }
}
