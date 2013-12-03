package com.my.device;

import java.util.Set;

import org.snmp4j.CommandResponderEvent;

public interface IDeviceHandler {
	void handle(CommandResponderEvent respEvnt);
	Set<TrapElement> warnningElements();
	void outputElements();
}
