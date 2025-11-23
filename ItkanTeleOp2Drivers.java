package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "BaseProgram", group = "LinearOpMode")
public class ItkanTeleOp2Drivers extends LinearOpMode {

    private DcMotor frontleftDrive, backleftDrive, frontrightDrive, backrightDrive;
    private DcMotor intake, outLeft, outRight;
    //private Servo gateServo;

    // Servo positions
    //private final double GATE_CLOSED_POS = 0.8;
    //private final double GATE_OPEN_POS = 0.2;

    @Override
    public void runOpMode() {

        // Map hardware
        frontleftDrive = hardwareMap.get(DcMotor.class, "fl");
        frontrightDrive = hardwareMap.get(DcMotor.class, "fr");
        backleftDrive = hardwareMap.get(DcMotor.class, "bl");
        backrightDrive = hardwareMap.get(DcMotor.class, "br");
        outRight = hardwareMap.get(DcMotor.class, "or");
        outLeft = hardwareMap.get(DcMotor.class, "ol");
        intake = hardwareMap.get(DcMotor.class, "i");

        //gateServo = hardwareMap.get(Servo.class, "gate");

        // Brake mode
        frontleftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontrightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backrightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Motor directions
        frontleftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontrightDrive.setDirection(DcMotor.Direction.FORWARD);
        backleftDrive.setDirection(DcMotor.Direction.REVERSE);
        backrightDrive.setDirection(DcMotor.Direction.FORWARD);
        outRight.setDirection(DcMotor.Direction.REVERSE);
        outLeft.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.FORWARD);

        // Start servo closed
        //gateServo.setPosition(GATE_CLOSED_POS);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        long shootStart = 0;

        while (opModeIsActive()) {

            // ----------------------------------------------------
            // GAMEPAD 1 → Driving
            // ----------------------------------------------------
            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            double frpower = drive + turn - strafe;
            double brpower = drive + turn + strafe;
            double flpower = drive - turn + strafe;
            double blpower = drive - turn - strafe;

            double maxPower = Math.max(Math.abs(frpower),
                          Math.max(Math.abs(brpower),
                          Math.max(Math.abs(flpower), Math.abs(blpower))));

            if (maxPower > 1.0) {
                frpower /= maxPower;
                brpower /= maxPower;
                flpower /= maxPower;
                blpower /= maxPower;
            }

            frontrightDrive.setPower(frpower);
            backrightDrive.setPower(brpower);
            frontleftDrive.setPower(flpower);
            backleftDrive.setPower(blpower);


            // ----------------------------------------------------
            // GAMEPAD 2 → SHOOT + INTAKE + GATE SERVO
            // B = Shoot system (open gate + spin shooters + wait → run intake)
            // A = Intake only
            // ----------------------------------------------------
            if (gamepad2.b) {

                //gateServo.setPosition(GATE_OPEN_POS);

                if (shootStart == 0) shootStart = System.currentTimeMillis();

                outLeft.setPower(0.8);
                outRight.setPower(0.8);

                if (System.currentTimeMillis() - shootStart > 2000) {
                intake.setPower(1.0);
                } else {
                    intake.setPower(0.0);
                }

            } else if (gamepad1.a) {
                shootStart = 0;
                intake.setPower(1.0);
                outLeft.setPower(0.0);
                outRight.setPower(0.0);
                //gateServo.setPosition(GATE_CLOSED_POS);
             
                
            }  else if(gamepad2.y) {
                 //intake.setDirection(DcMotor.Direction.REVERSE);
                 intake.setPower(-1.0);
                 
                }
                else if (gamepad1.b) {
                shootStart = 0;
                intake.setPower(-1.0);
                outLeft.setPower(0.0);
                outRight.setPower(0.0);
                //gateServo.setPosition(GATE_CLOSED_POS);
             
                
            } 

            else {
                shootStart = 0;
                intake.setPower(0.0);
                outLeft.setPower(0.0);
                outRight.setPower(0.0);
                //gateServo.setPosition(GATE_CLOSED_POS);
            } 


            // ----------------------------------------------------
            // TELEMETRY
            // ----------------------------------------------------
            telemetry.addData("Drive", "fl=%.2f fr=%.2f bl=%.2f br=%.2f",
                    flpower, frpower, blpower, brpower);
            telemetry.addData("Intake", intake.getPower());
            telemetry.addData("OutLeft", outLeft.getPower());
            telemetry.addData("OutRight", outRight.getPower());
            telemetry.addData("ShootTimer", shootStart);
            //telemetry.addData("GateServo", gateServo.getPosition());
            telemetry.update();
        }
    }
}