package ca._4976.sub;

import ca._4976.io.Controller;
import ca._4976.io.Input;
import ca._4976.io.Output;

/**
 * Created by Grant on 1/23/2016.
 */
public class Shooter {

    public void teleopPeriodic() {

        boolean IntakeState = false;    // false = down, true = up
        boolean SHOOTER = false;     // true = 75%, false = golden efficiency
        double GRIPPER = 0.0;    //true=on' false=off


        if (IntakeState = false) {

            if (Controller.Primary.Button.B.isDownOnce()) {

                Output.PneumaticSolenoid.Intake.set(true);
                IntakeState = true;

                if (SHOOTER = false) {

                    Output.Motor.SHOOTER.set(0.75);
                    SHOOTER = true;

                } else {
                    Output.Motor.GRIPPER.set(0.3);

                    GRIPPER = 0.3;
                }
            }

            if (Controller.Secondary.Button.X.isDownOnce()) {

                Output.PneumaticSolenoid.Intake.set(true);
                IntakeState = true;
            }

            if (Controller.Primary.Button.A.isDownOnce()) {

                Output.Motor.GRIPPER.set(0.3);


            }

            if (Input.Digital.BALL_PRESENT.get()){

            Output.Motor.GRIPPER.set(0.0);

            }

        }
        if (IntakeState = true) {

            if (Controller.Primary.Button.B.isDownOnce()) {

                Output.PneumaticSolenoid.Intake.set(false);
                IntakeState = false;
                Output.Motor.GRIPPER.set(0.3);
                GRIPPER = 0.3;
                Output.Motor.SHOOTER.set(0.0);
                SHOOTER = false;

            }

            if (Controller.Primary.Button.A.isDownOnce()) {

                if (SHOOTER = true) {

                    Output.Motor.GRIPPER.set(0.3);
                    GRIPPER = 0.3;

                    if (Input.Digital.BALL_NOT_PRESENT.get()) {

                        Output.Motor.GRIPPER.set(0.0);
                        GRIPPER = 0.0;

                    }


                } else {
                    Output.Motor.SHOOTER.set(0.75);
                    SHOOTER = true;
                }
            }

            if (Controller.Secondary.Button.X.isDownOnce()) {

                Output.PneumaticSolenoid.Intake.set(false);
                IntakeState = false;

            }
        }
    }
}