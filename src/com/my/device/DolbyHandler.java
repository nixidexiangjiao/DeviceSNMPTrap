package com.my.device;

import java.util.Map;

import org.snmp4j.smi.OID;

public class DolbyHandler extends AbstractDeviceHandler{
	static enum NOTIFICATIONTYPE {
		//ShowStore
		showStoreStorageEvent(new OID(".1.3.6.1.4.1.6729.2.1.1.3.1.5.1")),
		showStoreTempEvent(new OID(".1.3.6.1.4.1.6729.2.1.1.3.1.5.2")),
		showStoreHardwareEvent(new OID(".1.3.6.1.4.1.6729.2.1.1.3.1.5.3")),
		//ShowPlayer
		showPlayerFanEvent(new OID(".1.3.6.1.4.1.6729.2.1.1.4.1.5.1")),
		showPlayerPSUEvent(new OID(".1.3.6.1.4.1.6729.2.1.1.4.1.5.2")),
		showPlayerConnectionEvent(new OID(".1.3.6.1.4.1.6729.2.1.1.4.1.5.3"));
		private OID oid;
		private NOTIFICATIONTYPE(OID oid) {
			this.oid = oid;
		}
		public OID getOid() {return oid;}
	}
	
	public DolbyHandler(String ip) {
		super(ip);
		// TODO Auto-generated constructor stub
		this.type = DeviceType.DOLBY;
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
