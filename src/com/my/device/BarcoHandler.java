package com.my.device;

import org.snmp4j.smi.OID;

public class BarcoHandler extends AbstractDeviceHandler{
	static enum NOTIFICATIONTYPE {
		SystemFail(new OID(".1.3.6.1.4.1.12612.220.11.1.3.0.1"));
		private OID oid;
		private NOTIFICATIONTYPE(OID oid) {
			this.oid = oid;
		}
		public OID getOid() {return oid;}
	}
	
	public BarcoHandler(String ip) {
		super(ip);
		this.type = DeviceType.BARCO;
	}

	protected String findName(OID oid){
		for (NOTIFICATIONTYPE it : NOTIFICATIONTYPE.values()) {
			if(it.getOid().equals(oid)){
				return it.name();
			}
		}
		return null;
	}
}
