package com.my.device;

import java.util.Map;

import org.snmp4j.smi.OID;

public class BarcoHandler extends AbstractDeviceHandler{
	static enum NOTIFICATIONTYPE {
		SystemFail(new OID(".1.3.6.1.4.1.12612.220.11.1.3.0.1")),
		waterCoolingFail(new OID(".1.3.6.1.4.1.12612.220.11.2.3.0.2")),
		authenticationViolation(new OID(".1.3.6.1.4.1.12612.220.11.2.3.0.3")),
		lampRunTimeWarning(new OID(".1.3.6.1.4.1.12612.220.11.2.3.0.4")),
		lampRuntimeAlarm(new OID(".1.3.6.1.4.1.12612.220.11.2.3.0.5")),
		lampOffByProjector(new OID(".1.3.6.1.4.1.12612.220.11.2.3.0.6")),
		lampNoStrike(new OID(".1.3.6.1.4.1.12612.220.11.2.3.0.7")),
		dowserNotOpen(new OID(".1.3.6.1.4.1.12612.220.11.2.3.0.8")),
		globalFailure(new OID(".1.3.6.1.4.1.12612.220.11.2.3.0.9")),
		
		temperatureFail(new OID(".1.3.6.1.4.1.12612.220.11.4.3.0.6")),
		temperatureWarning(new OID(".1.3.6.1.4.1.12612.220.11.4.3.0.10")),
		voltageFail(new OID(".1.3.6.1.4.1.12612.220.11.4.3.0.7")),
		voltageWarning(new OID(".1.3.6.1.4.1.12612.220.11.4.3.0.11")),
		fanSpeedFail(new OID(".1.3.6.1.4.1.12612.220.11.4.3.0.8")),
		fanSpeedWarning(new OID(".1.3.6.1.4.1.12612.220.11.4.3.0.12"))
		;
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

	@Override
	protected void trapElementsSetClear(Map<String, TrapElement> trapElements) {
		// TODO Auto-generated method stub
		trapElements.clear();
	}
}
