package com.my.device;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;

public class TrapElement {
	public String name;
	public OID oid;
	public Variable variable;
	public boolean warnning;
	public long time;
	
	public TrapElement() {
		super();
	}

	public TrapElement(String name, OID oid, Variable variable, boolean warnning, long time) {
		super();
		this.name = name;
		this.oid = oid;
		this.variable = variable;
		this.warnning = warnning;
		this.time = time;
	}
	
	public boolean equals(Object trapElement) {
        boolean retVal = false;
        if(trapElement != null && trapElement.getClass().equals(this.getClass()))
        {
        	TrapElement bean = (TrapElement)trapElement;
            if(bean.oid==null && this.oid == null)
            {
                retVal = super.equals(trapElement);
            }
            else
            {
                if(bean.oid.equals(this.oid))
                {
                    retVal = true;
                }
            }
        }
        return retVal;
    }
	
	public int hashCode() {
		int result;
		result = name == null ? 0 : name.hashCode();
		result = 29*result + (oid == null ? 0 : oid.hashCode());
		return result;
	}
}
