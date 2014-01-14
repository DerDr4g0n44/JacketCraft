package org.CodeCentre.Jacket;

public class PacketType {
	public static final byte ID_CONNECTED_PING_OPEN_CONNECTIONS = 0x01;
	public static final byte ID_UNCONNECTED_PING_OPEN_CONNECTIONS = 0x02;
	public static final byte ID_OPEN_CONNECTION_REQUEST_1 = 0x05;
	public static final byte ID_OPEN_CONNECTION_REPLY_1 = 0x06;
	public static final byte ID_OPEN_CONNECTION_REQUEST_2 = 0x07;
	public static final byte ID_OPEN_CONNECTION_REPLY_2 = 0x08;
	public static final byte ID_INCOMPATIBLE_PROTOCOL_VERSION = 0x1A;
	public static final byte ID_ADVERTISE_SYSTEM_1 = 0x1C;
	public static final byte ID_ADVERTISE_SYSTEM_2 = 0x1D;
}
