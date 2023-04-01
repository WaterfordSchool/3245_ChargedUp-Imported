package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSSubsystem extends SubsystemBase{
    Spark leds;
    public LEDSSubsystem(){
        leds = new Spark(9);
    }

    @Override
    public void periodic() {}

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

    public void yellow(){
        leds.set(0.69);
    }

    public void purple(){
        leds.set(0.91);
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
        if(controller.getRawButton(8)){
            leds.set(0.69);
            //yellow
        }
        if(controller.getRawButton(7)){
            leds.set(0.91);
            //purple
        }
        /*if(!controller.getRawButton(7) && !controller.getRawButton(8)){
            leds.set(-0.59);
            //small flames
        }*/
        
        
    }
   
}
