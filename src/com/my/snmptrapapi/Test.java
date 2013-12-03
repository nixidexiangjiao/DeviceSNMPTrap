package com.my.snmptrapapi;

public class Test {
	public static void main(String args[]){
		try {
			DeviceTrapReceiver deviceTrapReceiver = new DeviceTrapReceiver("192.168.0.91", "udp", 162, "device.ini");
			deviceTrapReceiver.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
