package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutonomousRedAlternatePark", group = "")
public class AutonomousRedAlternatePark extends LinearOpMode {

    private DcMotor leftRear;
    private DcMotor rightRear;
    private DcMotor rightFront;
    private DcMotor leftFront;
    private Servo HornServoR;
    private Servo HornServoL;
    private int Ticks;
    private double inches;
    private ElapsedTime t = new ElapsedTime();

    /**
     * Describe this function...
     */
    private void encoderStraight() {
        // positive forward, negative backward
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setTargetPosition(0 - Ticks);
        leftFront.setTargetPosition(0 - Ticks);
        rightRear.setTargetPosition(0 + Ticks);
        rightFront.setTargetPosition(0 + Ticks);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setPower(0.7);
        rightRear.setPower(0.7);
        rightFront.setPower(0.7);
        leftFront.setPower(0.7);
        while (leftFront.isBusy() && rightFront.isBusy() && rightRear.isBusy() && leftRear.isBusy() && opModeIsActive()) {
            // nothing
        }
        leftRear.setPower(0);
        rightRear.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(2);
        leftFront.setPower(3);
    }

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightRear = hardwareMap.dcMotor.get("rightRear");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        HornServoR = hardwareMap.servo.get("HornServoR");
        HornServoL = hardwareMap.servo.get("HornServoL");

        waitForStart();
        if (opModeIsActive()) {
            // Test:
            // 588 = 1 rotation
            HornServoR.setPosition(0.9);
            HornServoL.setPosition(0.2);
            inches = 14;
            Ticks = (int) (62.4 * inches);
            encoderStrafe(2);
            sleep(200);
            inches = 25;
            Ticks = (int) (62.4 * inches);
            encoderStraight();
            HornServoR.setPosition(0.3);
            HornServoL.setPosition(0.85);
            sleep(200);
            inches = -30;
            Ticks = (int) (62.4 * inches);
            encoderStraight();
            sleep(200);
            HornServoR.setPosition(0.9);
            HornServoL.setPosition(0.2);
            inches = -20.5;
            Ticks = (int) (62.4 * inches);
            encoderStrafe(2);
            inches = 18;
            Ticks = (int) (62.4 * inches);
            encoderStraight();
            inches = -20;
            Ticks = (int) (62.4 * inches);
            encoderStrafe(2);
        }
    }

    /**
     * Describe this function...
     */
    private void encoderStrafe(int timeout) {
        t.reset();
        // positive right, negative left
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setTargetPosition(0 + Ticks);
        leftFront.setTargetPosition(0 - Ticks);
        rightRear.setTargetPosition(0 + Ticks);
        rightFront.setTargetPosition(0 - Ticks);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setPower(0.8);
        rightRear.setPower(0.8);
        rightFront.setPower(0.8);
        leftFront.setPower(0.8);
        while (leftFront.isBusy() && rightFront.isBusy() && rightRear.isBusy() && leftRear.isBusy() && opModeIsActive() && t.seconds() < timeout) {
            telemetry.addData("leftfrontposition", leftFront.getCurrentPosition());
            telemetry.addData("leftfronttarget", leftFront.getTargetPosition());
            telemetry.addData("rightfrontposition", rightFront.getCurrentPosition());
            telemetry.addData("rightfrontarget", rightFront.getTargetPosition());
            telemetry.addData("leftrearposition", leftRear.getCurrentPosition());
            telemetry.addData("leftreartarget", leftRear.getTargetPosition());
            telemetry.addData("rightrearposition", rightRear.getCurrentPosition());
            telemetry.addData("rightreartarget", rightRear.getTargetPosition());
            telemetry.update();
        }
        leftRear.setPower(0);
        rightRear.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
    }
}
