package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "=AutonomousRedFarAlternatePark", group = "")
public class AutonomousRedFarAlternatePark extends LinearOpMode {

    private Servo HornServoR;
    private Servo HornServoL;
    private DcMotor leftRear;
    private DcMotor rightRear;
    private DcMotor rightFront;
    private DcMotor leftFront;
    private int Ticks;
    private double inches;
    private ElapsedTime t = new ElapsedTime();

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        HornServoR = hardwareMap.servo.get("HornServoR");
        HornServoL = hardwareMap.servo.get("HornServoL");
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightRear = hardwareMap.dcMotor.get("rightRear");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");

        waitForStart();
        if (opModeIsActive()) {
            // Test:
            // 588 = 1 rotation
            HornServoR.setPosition(0.9);
            HornServoL.setPosition(0.2);
            inches = 6;
            Ticks = (int) (62.4 * inches);
            encoderStraight();
            inches = 35;
            Ticks = (int) (62.4 * inches);
            encoderStrafe(2);
            inches = 12;
            Ticks = (int) (62.4 * inches);
            encoderStraight();
            inches = 34.5;
            Ticks = (int) (62.4 * inches);
            encoderStrafe(2);
            inches = 6.15;
            Ticks = (int) (62.4 * inches);
            encoderStraight();
            sleep(500);
            HornServoR.setPosition(0.3);
            HornServoL.setPosition(0.85);
            sleep(500);
            inches = -29.25;
            Ticks = (int) (62.4 * inches);
            encoderStraight();
            sleep(200);
            HornServoR.setPosition(0.9);
            HornServoL.setPosition(0.2);
            sleep(200);
            inches = -44;
            Ticks = (int) (62.4 * inches);
            encoderStrafe(2);

        }
    }

    /**
     * Describe this function...
     */
    private void encoderStrafe(int timeout) {
        // positive right, negative left
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        leftRear.setPower(0.4);
        rightRear.setPower(0.4);
        rightFront.setPower(0.4);
        leftFront.setPower(0.4);
        while (leftFront.isBusy() && true && rightRear.isBusy() && leftRear.isBusy() && opModeIsActive()) {
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
        leftRear.setPower(0.4);
        rightRear.setPower(0.4);
        rightFront.setPower(0.4);
        leftFront.setPower(0.4);
        while (leftFront.isBusy() && rightFront.isBusy() && rightRear.isBusy() && leftRear.isBusy() && opModeIsActive()) {
            // nothing
            telemetry.addData("leftfrontposition", leftFront.getCurrentPosition());
            telemetry.addData("leftfronttarget", leftFront.getTargetPosition());
            telemetry.addData("rightfrontposition", rightFront.getCurrentPosition());
            telemetry.addData("rightfrontarget", rightFront.getTargetPosition());
            telemetry.addData("leftrearposition",leftRear.getCurrentPosition());
            telemetry.addData("leftreartarget",leftRear.getTargetPosition());
            telemetry.addData("rightrearposition",rightRear.getCurrentPosition());
            telemetry.addData("rightreartarget",rightRear.getTargetPosition());
            telemetry.update();
        }
        leftRear.setPower(0);
        rightRear.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(2);
        leftFront.setPower(3);
    }
}
