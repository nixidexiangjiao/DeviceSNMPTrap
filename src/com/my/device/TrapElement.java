package com.my.device;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;

public class TrapElement {
	public String name;
	public OID oid;
	public Variable variable;
	public boolean warnning;
	
	public TrapElement() {
		super();
	}

	public TrapElement(String name, OID oid, Variable variable, boolean warnning) {
		super();
		this.name = name;
		this.oid = oid;
		this.variable = variable;
		this.warnning = warnning;
	}
}
