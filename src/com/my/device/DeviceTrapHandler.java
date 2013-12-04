package com.my.device;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.snmp4j.CommandResponderEvent;

import com.my.pub.FileOperate;

public class DeviceTrapHandler implements IDeviceHandler{
	
	private Map<String, IDeviceHandler> devices = new HashMap<String, IDeviceHandler>();
	
	public DeviceTrapHandler(String iniFileName) throws Exception{
		try {
			Map<String, String> deviceMap = FileOperate.readIniFile(iniFileName);
			if(deviceMap == null)
				throw new Exception("配置文件不存在");
			Set<String> keySet = deviceMap.keySet();
			for (String key : keySet) {
				String type = deviceMap.get(key);
				IDeviceHandler deviceHandle = newDeviceHandler(type, key);
				if(deviceHandle != null)
					devices.put(key, deviceHandle);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public void handle(CommandResponderEvent respEvnt) {
		// TODO Auto-generated method stub
		String ipaddr = respEvnt.getPeerAddress().toString().substring(0, respEvnt.getPeerAddress().toString().indexOf("/"));
		IDeviceHandler deviceHandler = devices.get(ipaddr);
		if(deviceHandler != null){
			deviceHandler.handle(respEvnt);
			deviceHandler.outputElements();
		}
	}

	@Override
	public Map<String, TrapElement> warnningElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void outputElements() {
		// TODO Auto-generated method stub
		Set<String> keySet = devices.keySet();
		for (String key : keySet) {
			IDeviceHandler deviceHandler = devices.get(key);
			deviceHandler.outputElements();
		}
	}
	
	private IDeviceHandler newDeviceHandler(String type, String ip) throws Exception {
		for (DeviceType it : DeviceType.values()) {
			if(it.name().equals(type)){
				Class clazz = it.getClazz();
				Constructor cons = clazz.getConstructor(String.class);	    
				return (IDeviceHandler)cons.newInstance(ip);       
			}
		}
		return null;
	}
	
	public static void main(String args[]){
		try {
			DeviceTrapHandler deviceTrapHandler = new DeviceTrapHandler("deivce.ini");
			deviceTrapHandler.outputElements();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
