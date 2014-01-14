package org.CodeCentre.Jacket.network;

import static org.CodeCentre.Jacket.PacketType.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Random;

import org.CodeCentre.Jacket.Jacket;

public class PacketHandler {
	private static byte[] current;

	protected static void handle(DatagramPacket received) throws IOException {
		current = getReal(received.getData(), received.getLength());
		switch (current[0]) {
		case ID_CONNECTED_PING_OPEN_CONNECTIONS:
		case ID_UNCONNECTED_PING_OPEN_CONNECTIONS:
			byte[] resp0x1c = response0x1c();
			PacketListenerRunnable.getServerSocket().send(
					new DatagramPacket(resp0x1c, resp0x1c.length, received
							.getAddress(), received.getPort()));
			break;
		
		default:
			System.out.println("Received: 0x0" + current[0]);
		}
	}

	private static byte[] getReal(byte[] ba, int length) {
		int realSize = length;
		byte[] realPacket = new byte[realSize];
		System.arraycopy(ba, 0, realPacket, 0, realSize);
		return realPacket;
	}

	private static byte[] response0x1c() throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		DataOutputStream outputstream = new DataOutputStream(bytestream);
		DataInputStream inputstream = cIn();
		inputstream.skipBytes(1);
		long pingid = inputstream.readLong();
		outputstream.write(ID_ADVERTISE_SYSTEM_1);
		outputstream.writeLong(pingid);
		outputstream.writeLong(Jacket.getServerID());
		writeMagic(outputstream);
		outputstream.writeUTF("MCCPP;Demo;"
				+ (!Jacket.getServer().isInvisible() ? Jacket.getServer()
						.getMOTD() : ""));
		return bytestream.toByteArray();
	}

	private static void writeMagic(DataOutputStream out) throws IOException {
		out.write(0);
		out.write(0xff);
		out.write(0xff);
		out.write(0);
		out.writeInt(0xfefefefe);
		out.writeInt(0xfdfdfdfd);
		out.writeInt(0x12345678);
	}

	private static DataInputStream cIn() {
		return new DataInputStream(new ByteArrayInputStream(current));
	}
}
