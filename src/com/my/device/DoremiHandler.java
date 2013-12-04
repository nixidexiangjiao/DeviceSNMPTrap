package com.my.device;

import java.util.Map;

import org.snmp4j.smi.OID;

public class DoremiHandler extends AbstractDeviceHandler{
	
	public DoremiHandler(String ip) {
		super(ip);
		// TODO Auto-generated constructor stub
		this.type = DeviceType.DOREMI;
	}

	@Override
	protected String findName(OID oid) {
		// TODO Auto-generated method stub
//		for (NOTIFICATIONTYPE it : NOTIFICATIONTYPE.values()) {
//			if(it.getOid().equals(oid)){
//				return it.name();
//			}
//		}
		return null;
	}

	@Override
	protected void trapElementsSetClear(Map<String, TrapElement> trapElements) {
		// TODO Auto-generated method stub
		trapElements.clear();
	}

}
