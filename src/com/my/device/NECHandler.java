package com.my.device;

import java.util.Map;

import org.snmp4j.smi.OID;

public class NECHandler extends AbstractDeviceHandler{
	static enum NOTIFICATIONTYPE {
		//specific-trap
		vTrapStatus(new OID(".1.3.6.1.4.1.119.2.3.123.0.1")),
		vTrapProtect(new OID(".1.3.6.1.4.1.119.2.3.123.0.2")),
		vTrapProtectNC(new OID(".1.3.6.1.4.1.119.2.3.123.0.3")),
		//generictraptype
		cold_start(new OID(".1.3.6.1.4.1.12612.220.11.2.3.0.4")),
		hot_start(new OID(".1.3.6.1.4.1.119.2.3.123.0")),
		authentication_failure(new OID(".1.3.6.1.4.1.119.2.3.123.0"));
		private OID oid;
		private NOTIFICATIONTYPE(OID oid) {
			this.oid = oid;
		}
		public OID getOid() {return oid;}
	}
	
	public NECHandler(String ip) {
		super(ip);
		// TODO Auto-generated constructor stub
		this.type = DeviceType.NEC;
	}

	@Override
	protected String findName(OID oid) {
		// TODO Auto-generated method stub
		for (NOTIFICATIONTYPE it : NOTIFICATIONTYPE.values()) {
			if(it.getOid().equals(oid)){
				return it.name();
			}
		}
		return null;
	}

	@Override
	protected void trapElementsSetClear(Map<String, TrapElement> trapElements) {
		// TODO Auto-generated method stub
		trapElements.clear();
	}

}
