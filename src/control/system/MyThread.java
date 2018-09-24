/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.system;

/**
 *
 * @author Akash
 */
public class MyThread implements Runnable{
    Main f;
    public MyThread(Main f) {
        this.f = f;
    }

    @Override
    public void run() {
        while(true) {
            try 
            {
                Thread.sleep(1000);
                f.speed = (int)(f.speed + 9.81 * Math.sin(Math.toRadians(f.inclination)));
                f.error = f.desired - f.speed;
                if(f.error!=0) {                    
                    f.errorIntegral += f.error;
                    f.speed += f.pC * f.error;
                    f.speed += f.iC * f.errorIntegral;
                    f.speed += f.dC * (f.error-f.errorPre);
                    f.errorPre = f.error;
                } else {
                    f.errorIntegral = 0;
                    f.errorPre = 0;
                }
                if((int)f.speed == (int)f.desired) {
                    f.inclination=0;
                }
                System.out.println(f.speed);
                f.setSpeed((int)f.speed);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}

