/*
    This code is where we say what happens when a button is pressed on the contr
*/

package org.firstinspires.ftc.teamcode;

//Import
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

//Declaration of DriveMain
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
    private DcMotor left;
    private DcMotor right;
    private Servo wrist;
    private Servo finger;
    private Servo block;
    private Servo hornLeft;
    private Servo hornRight;
    private Servo lat;


    //Declaration of all motors, servos, etc
    public void init() {
        left = hardwareMap.get(DcMotor.class, "ShoulderMotor");
        right = hardwareMap.get(DcMotor.class, "WinchMotor");
        wrist = hardwareMap.get(Servo.class, "WristServo");
        finger = hardwareMap.get(Servo.class, "FingerServo");
        hornRight = hardwareMap.get(Servo.class, "HornServoR");
        hornLeft = hardwareMap.get(Servo.class, "HornServoL");
        lat = hardwareMap.get(Servo.class, "LatServo");
        block = hardwareMap.get(Servo.class, "colorArmServo");

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
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addData("Status", "Initialized");

        //declaration of Intake.java (although never used)
        intake = new Intake(hardwareMap);
    }

    public void loop() {
        // Helps with the drive controls, determines direction of wheels
        double theta = Math.atan2(-gamepad1.left_stick_y, -gamepad1.left_stick_x);
        double magnitude = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double turn = -Range.clip(gamepad1.right_stick_x, -1, 1);
        double rf = Math.sin(theta + (Math.PI / 4)) * magnitude;
        double lf = Math.sin(theta - (Math.PI / 4)) * magnitude;
        double rb = Math.sin(theta - (Math.PI / 4)) * magnitude;
        double lb = Math.sin(theta + (Math.PI / 4)) * magnitude;

        //Also helps set direction for the wheels
        leftRear.setPower(lb - turn);
        rightRear.setPower(rb + turn);
        leftFront.setPower(lf - turn);
        rightFront.setPower(rf + turn);

        if (gamepad1.right_bumper){
            block.setPosition (0);
        }
        if (gamepad1.left_bumper){
            block.setPosition(1);
        }
        if (gamepad1.right_trigger>0.7){
            hornRight.setPosition(0.9);
            hornLeft.setPosition(0.1);
        }
        if (gamepad1.left_trigger>0.7){
            hornRight.setPosition(0.1);
            hornLeft.setPosition(0.9);
        }

        double x = Math.atan2(-gamepad2.left_stick_y, -gamepad2.left_stick_x);
        double magic = Math.hypot (gamepad2.left_stick_x, gamepad2.left_stick_y);
        double movement = -Range.clip(gamepad2.left_stick_x, -1, 1);
        double arm = Math.sin(x - (Math.PI / 4)) * magic;

        left.setPower(arm - movement);




        if (gamepad2.dpad_down){
            intake.finger();
        }
        if (gamepad2.dpad_up){
            intake.fingerreset();
        }
        if (gamepad2.right_bumper){
            lat.setPosition(0.8);
        }
        if (gamepad2.right_trigger>0.7){
            lat.setPosition(0.2);
        }
        if (gamepad2.dpad_left){
            lat.setPosition (0.5);
        }
        if (gamepad2.left_bumper){
            wrist.setPosition (0.7);
        }
        if (gamepad2.left_trigger>0.7){
            wrist.setPosition (0.3);
        }
        if (gamepad2.dpad_right){
            wrist.setPosition (0.5);
        }

        if (gamepad2.x){
            hornLeft.setPosition (0.4);
            hornRight.setPosition(0.6);
        }
        if (gamepad2.y){
            hornLeft.setPosition(0.9);
            hornRight.setPosition(0.1);
        }
    }
}
