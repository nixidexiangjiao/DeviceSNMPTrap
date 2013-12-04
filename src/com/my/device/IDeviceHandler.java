package com.my.device;

import java.util.Map;

import org.snmp4j.CommandResponderEvent;

public interface IDeviceHandler {
	void handle(CommandResponderEvent respEvnt);
	Map<String, TrapElement> warnningElements();
	void outputElements();
}
