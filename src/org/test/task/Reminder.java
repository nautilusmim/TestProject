package org.test.task;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Reminder {

	private static Timer timer;
	
	static {
		timer = new Timer();
	}

    public Reminder(int seconds) {
        timer.schedule(new RemindTask("second"), 0, seconds*1000);
    }
    
    public Reminder(Date time) {
		timer.schedule(new RemindTask("date"), time);
    }

    class RemindTask extends TimerTask {
    	int num = 0;
    	String name;
    	
    	public RemindTask(String name) {
    		this.name = name;
    	}
    	
        public void run() {
        	if(num < 5) {
        		System.out.println(name + ": Time's up!");
        	} else {
        		timer.cancel(); //Terminate the timer thread
        	}
        	
        	num ++;
        }
    }

    public static void main(String args[]) {
        System.out.println("About to schedule task.");
        new Reminder(5);
        
  		Calendar calendar = Calendar.getInstance();
  		calendar.set(Calendar.HOUR_OF_DAY, 11);
  		calendar.set(Calendar.MINUTE, 8);
  		calendar.set(Calendar.SECOND, 0);
  		Date time = calendar.getTime();
  		new Reminder(time);
  		
        System.out.println("Task scheduled.");
    }

}
