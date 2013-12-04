package com.my.device;

import java.util.Map;

import org.snmp4j.smi.OID;

public class ChristieHandler extends AbstractDeviceHandler{
	static enum NOTIFICATIONTYPE {
		cdProjectorNotifyFan(new OID(".1.3.6.1.4.1.25766.1.11.100.3.0.1")),
		cdProjectorNotifyInterlock(new OID(".1.3.6.1.4.1.25766.1.11.100.3.0.2")),
		cdProjectorNotifyTemperature(new OID(".1.3.6.1.4.1.25766.1.11.100.3.0.3")),
		cdProjectorNotifySystemComm(new OID(".1.3.6.1.4.1.25766.1.11.100.3.0.4")),
		cdProjectorNotifyLampExpire(new OID(".1.3.6.1.4.1.25766.1.11.100.3.0.5")),
		cdProjectorNotifyLampStrike(new OID(".1.3.6.1.4.1.25766.1.11.100.3.0.6")),
		cdProjectorNotifyLampUnexpectedOff(new OID(".1.3.6.1.4.1.25766.1.11.100.3.0.7")),
		cdProjectorNotifyCoolant(new OID(".1.3.6.1.4.1.25766.1.11.100.3.0.8")),
		cdProjectorNotifyLampHalfLifeRotation(new OID(".1.3.6.1.4.1.25766.1.11.100.3.0.9")),
		cdProjectorNotifySecurityTamper(new OID(".1.3.6.1.4.1.25766.1.11.100.3.0.10"))
		;
		private OID oid;
		private NOTIFICATIONTYPE(OID oid) {
			this.oid = oid;
		}
		public OID getOid() {return oid;}
	}
	
	public ChristieHandler(String ip) {
		super(ip);
		// TODO Auto-generated constructor stub
		this.type = DeviceType.CHRISTIE;
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
