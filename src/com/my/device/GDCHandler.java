package com.my.device;

import org.snmp4j.smi.OID;

public class GDCHandler extends AbstractDeviceHandler{

	public GDCHandler(String ip) {
		super(ip);
		// TODO Auto-generated constructor stub
		this.type = DeviceType.GDC;
	}

	@Override
	protected String findName(OID oid) {
		// TODO Auto-generated method stub
		return null;
	}

}
