package com.my.device;

import org.snmp4j.smi.OID;

public class ChristieHandler extends AbstractDeviceHandler{

	public ChristieHandler(String ip) {
		super(ip);
		// TODO Auto-generated constructor stub
		this.type = DeviceType.CHRISTIE;
	}

	@Override
	protected String findName(OID oid) {
		// TODO Auto-generated method stub
		return null;
	}

}
