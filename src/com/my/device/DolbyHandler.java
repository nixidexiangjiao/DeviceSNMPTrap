package com.my.device;

import org.snmp4j.smi.OID;

public class DolbyHandler extends AbstractDeviceHandler{

	public DolbyHandler(String ip) {
		super(ip);
		// TODO Auto-generated constructor stub
		this.type = DeviceType.DOLBY;
	}

	@Override
	protected String findName(OID oid) {
		// TODO Auto-generated method stub
		return null;
	}

}
