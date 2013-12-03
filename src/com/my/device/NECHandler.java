package com.my.device;

import org.snmp4j.smi.OID;

public class NECHandler extends AbstractDeviceHandler{

	public NECHandler(String ip) {
		super(ip);
		// TODO Auto-generated constructor stub
		this.type = DeviceType.NEC;
	}

	@Override
	protected String findName(OID oid) {
		// TODO Auto-generated method stub
		return null;
	}

}
