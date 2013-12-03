package com.my.device;

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
		return null;
	}

}
