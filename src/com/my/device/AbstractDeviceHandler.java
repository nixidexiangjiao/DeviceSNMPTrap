package com.my.device;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.snmp4j.CommandResponderEvent;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import com.my.pub.DateOperation;
import com.my.pub.FileOperate;

public abstract class AbstractDeviceHandler implements IDeviceHandler {
	private String ip;
	
	protected DeviceType type;
	protected Map<String, TrapElement> trapElements = new HashMap<String, TrapElement>();

	public AbstractDeviceHandler(String ip) {
		super();
		this.ip = ip;
	}

	@Override
	public void handle(CommandResponderEvent respEvnt) {
		// TODO Auto-generated method stub
		if (respEvnt != null && respEvnt.getPDU() != null) {
//			trapElementsSetClear(trapElements);
			Vector<VariableBinding> recVBs = respEvnt.getPDU().getVariableBindings();
			for (int i = 0; i < recVBs.size(); i++) {
				VariableBinding recVB = recVBs.elementAt(i);
				String name = findName(recVB.getOid());
				if (name != null)
					trapElements.put(name, new TrapElement(name, recVB.getOid(), recVB.getVariable(), true, new Date().getTime()));
			}
		}
	}
	
	@Override
	public Map<String, TrapElement> warnningElements() {
		// TODO Auto-generated method stub
		return trapElements;
	}
	
	@Override
	public void outputElements() {
		// TODO Auto-generated method stub
		if(!trapElements.isEmpty()){
			String filePath = "trapdir";
			String date = DateOperation.dateToString(new Date(), "yyyy-MM-dd");
			StringBuffer content = new StringBuffer();
			Set<String> keySet = trapElements.keySet();
			for (String key : keySet) {
				TrapElement trapElement = trapElements.get(key);
				String time = DateOperation.dateToString(new Date(), "HH:mm:ss");
				String temp = time + "   " + trapElement.time + "   " + trapElement.name + " : " + trapElement.variable +"\n";
				System.out.println(temp);
				content.append(temp);
			}
			content.append("\n\n");
			FileOperate.writeFile(filePath + File.separator + date + "_" + type.name() + "_" + ip, content.toString(), true);
		}
	}

	public String getIp() {
		return ip;
	}

	abstract protected String findName(OID oid);
	abstract protected void trapElementsSetClear(Map<String, TrapElement> trapElements);
}
