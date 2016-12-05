package com.test;
import java.util.Timer;



public class TimerCall {
public static void main(String[] args) {
	Timer call=new Timer();
	call.scheduleAtFixedRate(new UserTimerTask(), 10000, 1000);
}
}
