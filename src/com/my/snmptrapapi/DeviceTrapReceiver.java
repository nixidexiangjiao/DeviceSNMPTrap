package com.my.snmptrapapi;

import java.io.IOException;
import java.net.UnknownHostException;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;

import com.my.device.DeviceTrapHandler;

public class DeviceTrapReceiver implements CommandResponder {
	private String ip;
	private String type;//tcp or udp
	private int port = 162;
	private MultiThreadedMessageDispatcher dispatcher;
	private Snmp snmp = null;
	private Address listenAddress;
	private ThreadPool threadPool;
	
	private DeviceTrapHandler deviceTrapHandler;

	/**
	 * @param ip
	 * @param type tcp or udp
	 * @param port default 162
	 * @param iniFile 配置文件路径
	 * @throws Exception 
	 */
	public DeviceTrapReceiver(String ip, String type, int port, String iniFile) throws Exception {
		this.ip = ip;
		this.type = type;
		this.port = port;
		deviceTrapHandler = new DeviceTrapHandler(iniFile);
	}

	private void init() throws UnknownHostException, IOException {
		threadPool = ThreadPool.create("Trap", 2);
		dispatcher = new MultiThreadedMessageDispatcher(threadPool,
				new MessageDispatcherImpl());
		listenAddress = GenericAddress.parse(System.getProperty(
				"snmp4j.listenAddress", type + ":" + ip + "/" + port)); // 本地IP与监听端口
		TransportMapping transport;
		// 对TCP与UDP协议进行处理
		if (listenAddress instanceof UdpAddress) {
			transport = new DefaultUdpTransportMapping(
					(UdpAddress) listenAddress);
		} else {
			transport = new DefaultTcpTransportMapping(
					(TcpAddress) listenAddress);
		}
		snmp = new Snmp(dispatcher, transport);
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3());
		USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3
				.createLocalEngineID()), 0);
		SecurityModels.getInstance().addSecurityModel(usm);
		snmp.listen();
	}

	
	public void run() {
		try {
			init();
			snmp.addCommandResponder(this);
			System.out.println("开始监听设备Trap信息!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 实现CommandResponder的processPdu方法, 用于处理传入的请求、PDU等信息
	 * 当接收到trap时，会自动进入这个方法
	 * 
	 * @param respEvnt
	 */
	public void processPdu(CommandResponderEvent respEvnt) {
		// 解析Response
        if (respEvnt != null && respEvnt.getPDU() != null) {
        	deviceTrapHandler.handle(respEvnt);
		}
	}
}
