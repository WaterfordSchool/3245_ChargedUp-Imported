package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LEDSSubsystem extends SubsystemBase{
    //motors
    Spark leds;
    //init stuff
    public LEDSSubsystem(){
        //claw motor/encoder
        leds = new Spark(0);
    }

    @Override
    public void periodic() {
        //put encoder positions
        //put current value
    }

    public void green(){
        leds.set(.73);
    }

    public void blue(){
        leds.set(.83);
    }

    public void red(){
        leds.set(0.61);
    }

    public void sinelon(){
        leds.set(.55);
    }

    public void flames(){
        leds.set(-0.59);
    }

    public void glitterRainbow(){
        leds.set(-0.89);
    }

    public void color1lightchase(){
        leds.set(0.01);
    }

    public void color2lightchase(){
        leds.set(0.21);
    }

    public void manualControl(XboxController controller){
        if(controller.getPOV()==0){
            leds.set(0.61);
            //red
        }
        if(controller.getPOV()==90){
            leds.set(0.83);
            //blue
        }
        if(controller.getPOV()==180){
            leds.set(0.73);
            //green
        }
        if(controller.getPOV()==270){
            leds.set(-0.59);
            //small flames
        }
        
    }
   
}
