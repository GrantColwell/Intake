package ca._4976.sub;

import ca._4976.io.Controller;
import ca._4976.io.Input;
import ca._4976.io.Output;


/**
 * Created by Grant on 1/23/2016.
 */
public class Shooter {

    boolean IntakeState = false;    // false = down, true = up
    boolean SHOOTER = false;     // true = 75%, false = golden efficiency
    double GRIPPER = 0.0;    //true=on' false=off
    boolean HOOD = false;

    public void teleopPeriodic() {

        if (IntakeState == true) {

            if (Controller.Primary.Button.A.isDownOnce()) {
                Output.Motor.GRIPPER.set(0.3);
                GRIPPER = 0.3;
                System.out.println("The gripper is spinning at: "+GRIPPER);
            }

            if (Input.Digital.BALL_PRESENT.get()){
                Output.Motor.GRIPPER.set(0.0);
                GRIPPER = 0.0;
                System.out.println("The gripper is not spinning");
            }

            if (Controller.Primary.Button.B.isDownOnce()) {
                Output.PneumaticSolenoid.Intake.set(false);
                IntakeState = false;
                System.out.println("The intake is up");
                Output.PneumaticSolenoid.HOOD.set(false);
                HOOD = false;

                if (SHOOTER == false) {
                    Output.Motor.SHOOTER.set(0.75);
                    SHOOTER = true;
                    System.out.println("The shooter is going");

                } else {
                    Output.Motor.GRIPPER.set(0.3);
                    GRIPPER = 0.3;
                    System.out.println("The gripper is spinning at: "+GRIPPER);
                }
            }

            if (Controller.Secondary.Button.X.isDownOnce()) {
                Output.PneumaticSolenoid.Intake.set(false);
                IntakeState = false;
                System.out.println("The intake is up");
            }

        }
        if (IntakeState == false) {

            if (Controller.Primary.Button.A.isDownOnce()) {

                Output.PneumaticSolenoid.Intake.set(true);
                IntakeState = true;
                System.out.println("The intake is down");
                Output.Motor.GRIPPER.set(0.0);
                GRIPPER = 0.0;
                System.out.println("The gripper is spinning at: "+GRIPPER);
                Output.Motor.SHOOTER.set(0.0);
                SHOOTER = false;
                System.out.println("The shooter is off");
                Output.PneumaticSolenoid.HOOD.set(true);
                HOOD = true;

            }

            if (Controller.Primary.Button.B.isDownOnce()) {

                if (SHOOTER == true) {

                    Output.Motor.GRIPPER.set(0.3);
                    GRIPPER = 0.3;
                    System.out.println("The gripper is spinning at: "+GRIPPER);


                }
                else {
                    Output.Motor.SHOOTER.set(0.75);
                    SHOOTER = true;
                    System.out.println("The shooter is spinning");
                }
            }

            if (Controller.Secondary.Button.X.isDownOnce()) {
                Output.PneumaticSolenoid.Intake.set(true);
                IntakeState = true;
                System.out.println("The inatke is down");
            }

        }
    }
}
