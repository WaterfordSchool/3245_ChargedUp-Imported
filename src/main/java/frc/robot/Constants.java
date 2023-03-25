// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
    //drive ids
        public static final int driveleft1ID = 1;
        public static final int driveleft2ID = 2;
        public static final int driveright1ID = 3;
        public static final int driveright2ID = 4;
    //motor ids
        public static final int armMotorShoulderMasterID = 7;
        public static final int armMotorShoulderSlaveID = 8;
        public static final int armMotorWristJointID = 6;

        public static final int clawRunLeftMotorID = 9;
        public static final int clawRunRightMotorID = 10;
        public static final int clawCloseMotorID = 11;
        public static final int clawV2flangeMotorID = 14;
        public static final int clawV2clawMotorID = 15;

        public static final int wristMotorID = 12;
    //motor speeds/max values
        public static final double clawConstantSpeedVal = -0.07;
        public static final double clawRunSpeed = 0.45;

        public static final double clawCloseMaxVal = 0.3;

        public static final double armShoulderClosedMaxOutput = 0.3;
        public static final double armWristClosedMaxOutput = 0.2;
        public static final double armShoulderPeakOutputFor = 0.25;

        public static final double wristMaxOutput = 0.60;

    //gains, ramping
        public static final double driveRampRate = 0.3;

        public static final double clawCloseClosedRampRate = 0.4;
        public static final double armWristClosedRampRate = 0.4;

        public static final double clawDriveClosedrampRate = 0.4;

        public static final double wristClosedRampRate = 0.7;
        
    //PID

        public static final double armShoulderkF = 0;
        public static final double armShoulderkP = 0.1;
        public static final double armShoulderkI = 0;
        public static final double armShoulderkD = 0.2;

        public static final double armWristkF = 0.0;
        public static final double armWristkP = 0.15;
        public static final double armWristkI = 0.0;
        public static final double armWristkD = 0.0;

        public static final double wristkF = 0.0;
        public static final double wristkP = 0.15;
        public static final double wristkI = 0.0;
        public static final double wristkD = 0.0;

        public static final double clawRunkF = 0.0;
        public static final double clawRunkP = 0.15;
        public static final double clawRunkI = 0.0;
        public static final double clawRunkD = 0.0;

        public static final double clawClosekF = 0.0;
        public static final double clawClosekP = 0.15;
        public static final double clawClosekI = 0.0;
        public static final double clawClosekD = 0.0;

    //CONTROLS
    //driver controls
        //drive axes: 0, 2, 3
        public static final int fastButton = 1;
        public static final int slowButton = 2;

    //operator controls
        //axes
        public static final int manualShoulderAxis = 1;
        
        public static final int manualWristAxis =5;

        public static final int manualClawCloseAxis = 4;

        public static final int manualwristAxis = 6;

        //buttons
        //public static final int manualClawLeftSpinButton = 1;
        public static final int manualClawInButton = 5;
        public static final int manualClawSpitButton = 6;
        public static final int manualFlangeInButton = 12;
        public static final int manualFlangeOutButton = 13;
        public static final int currentSlideButton = 14;
        public static final int armHomeButton = 1; //a
        public static final int armLowButton = 2; //b
        public static final int armMidButton = 3; //x
        public static final int armHighButton = 4; //y
        public static final int wristStayButton = 6;

    //encoder values
        public static final double shoulderUpPos = 89000;
        public static final double shoulderMidPos = 75000;
        public static final double shoulderDownPos = 20000;
        public static final double shoulderHomePos = 0;

        public static final double wristUpPos = -66000;
        public static final double wristMidPos = -49000;
        public static final double wristDownPos = -35000;
        public static final double wristHomePos = 0;

    //AUTO
    //auto thresholds
        public static final double angleThresh1 = 10;
        public static final double angleThresh2 = 12;
        public static final double angleThresh3 = 14;
        public static final double angleThreshStop = 1;

    //auto speeds
        public static final double autoSpeed1 = 0.55;
        public static final double autoSpeed2 = 0.2;
        public static final double autoSpeed3 = 0.1;



    //37000, 26000, 10000

//low: 11, 38000
//mid: 300, 62000
//high: 430, 74000
//home: 0, 174
}