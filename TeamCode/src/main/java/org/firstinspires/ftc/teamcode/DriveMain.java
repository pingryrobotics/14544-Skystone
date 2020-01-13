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
    private DcMotor left;
    private DcMotor right;
    private Servo wrist;
    private Servo finger;
    private Servo lat;
    private Servo pushbarLeft;
    private Servo pushbarRight;
    private Servo hornLeft;
    private Servo hornRight;


    public void init() {
        left = hardwareMap.get(DcMotor.class, "ShoulderMotor");
        right = hardwareMap.get(DcMotor.class, "WinchMotor");
        wrist = hardwareMap.get(Servo.class, "WristServo");
        finger = hardwareMap.get(Servo.class, "FingerServo");
        lat = hardwareMap.get(Servo.class, "LatServo");
        pushbarRight = hardwareMap.get(Servo.class, "PushbarServoRight");
        pushbarLeft = hardwareMap.get(Servo.class, "PushbarServoLeft");
        hornRight = hardwareMap.get(Servo.class, "HornServoR");
        hornLeft = hardwareMap.get(Servo.class, "HornServoL");

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

        double x = Math.atan2(-gamepad2.left_stick_y, -gamepad2.left_stick_x);
        double y = Math.hypot(gamepad2.left_stick_x, gamepad2.left_stick_y);
        double a = -Range.clip(gamepad2.right_stick_x, -1, 1);
        double b = Math.sin(x + (Math.PI / 4)) * magnitude;
        double c = Math.sin(x - (Math.PI / 4)) * magnitude;
        double d = Math.sin(x - (Math.PI / 4)) * magnitude;
        double e = Math.sin(x + (Math.PI / 4)) * magnitude;

        left.setPower(d + a);
        right.setPower(c + a);

        if(gamepad2.dpad_down){
            intake.latreverse();
        }
        if (gamepad2.dpad_up){
            intake.latmovement();
        }
        if (gamepad2.dpad_right){
            intake.wrist();
        }
        if (gamepad2.dpad_left){
            intake.wristreset();
        }
        if (gamepad2.a){
            intake.latstop();
        }
        if (gamepad2.right_bumper){
            intake.horndeploy();
        }
        if (gamepad2.right_trigger>0.3){
            intake.hornretract();
        }
        if (gamepad2.left_bumper){
            intake.pushersOut();
        }
        if (gamepad2.left_trigger>0.3){
            intake.pushersIn();
        }

        telemetry.addData("leftfrontPower", leftFront.getPower());
        telemetry.addData("rightfrontPower", rightFront.getPower());
        telemetry.addData("leftrearPower",leftRear.getPower());
        telemetry.addData("rightrearPower",rightRear.getPower());
        telemetry.addData("ArmPower",left.getPower());
        telemetry.addData("WinchPower",right.getPower());
        telemetry.addData("Arm Position (degrees)",right.getCurrentPosition()/(28*40));
        telemetry.addData("PushbarRightPosition", pushbarRight.getPosition());
        telemetry.addData("PushbarLeftPosition", pushbarLeft.getPosition());
        telemetry.addData("HornRightPosition",hornRight.getPosition());
        telemetry.addData("HornLeftPosition",hornLeft.getPosition());
        telemetry.update();
        telemetry.update();
    }
}
