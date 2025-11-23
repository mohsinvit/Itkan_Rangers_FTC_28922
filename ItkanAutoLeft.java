package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "ItkanAutoLeft", group = "LinearOpMode")
public class ItkanAutoLeft extends LinearOpMode {

    private DcMotor fl, fr, bl, br;
    private DcMotor outLeft, outRight, intake;
    // private Servo gate;

    @Override
    public void runOpMode() throws InterruptedException {

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        outLeft = hardwareMap.get(DcMotor.class, "ol");
        outRight = hardwareMap.get(DcMotor.class, "or");
        intake = hardwareMap.get(DcMotor.class, "i");

        // gate = hardwareMap.get(Servo.class, "gate");

        // Motor directions
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.FORWARD);
        outRight.setDirection(DcMotor.Direction.REVERSE);

        // Close gate at start
        // gate.setPosition(0.8);

        waitForStart();

        // ----------------------------------------------------
        // 1) DRIVE FORWARD ~1 FOOT
        // ----------------------------------------------------
        fl.setPower(0.5);
        fr.setPower(0.5);
        bl.setPower(0.5);
        br.setPower(0.5);

        sleep(2000); // Tune this for exactly 1 foot

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

        sleep(300);

        // ----------------------------------------------------
        // 2) TURN LEFT ~45 DEGREES
        // LEFT = left motors backward, right motors forward
        // ----------------------------------------------------
        fl.setPower(0.5);
        bl.setPower(0.5);
        fr.setPower(-0.5);
        br.setPower(-0.5);

        sleep(200); // Tune this for exact 45°

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

        sleep(300);

        // ----------------------------------------------------
        // 3) DRIVE FORWARD A LITTLE MORE (adjust as needed)
        // ----------------------------------------------------
       /* fl.setPower(0.2);
        fr.setPower(0.2);
        bl.setPower(0.2);
        br.setPower(0.2);

        sleep(1000);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
*/
        // ----------------------------------------------------
        // 4) SHOOT BALL
        // ----------------------------------------------------

        // Open gate
        // gate.setPosition(0.2);
        // sleep(300);

        // Spin shooter wheels
        outLeft.setPower(0.8);
        outRight.setPower(0.8);
        sleep(2000);

        // Push ball with intake
        intake.setPower(1.0);
        sleep(1500);

        // Stop everything
        intake.setPower(0);
        outLeft.setPower(0);
        outRight.setPower(0);

        // Close gate
        // gate.setPosition(0.8);
        // sleep(300);
        
        fl.setPower(0.5);
        fr.setPower(0.5);
        bl.setPower(-0.5);
        br.setPower(-0.5);
        sleep(300); // tune this for 1 ft
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        
        fl.setPower(-0.5);
        fr.setPower(-0.5);
        bl.setPower(-0.5);
        br.setPower(-0.5);
        sleep(1000);
        
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }
}