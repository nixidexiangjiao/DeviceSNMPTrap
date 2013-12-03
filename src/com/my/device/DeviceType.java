package com.my.device;

public enum DeviceType {
	GDC(GDCHandler.class), DOREMI(DoremiHandler.class), DOLBY(
			DolbyHandler.class), BARCO(BarcoHandler.class), CHRISTIE(
			ChristieHandler.class), NEC(NECHandler.class);
	private Class clazz;

	private DeviceType(Class clazz) {
		this.clazz = clazz;
	}

	public Class getClazz() {
		return clazz;
	}
}