package org.CodeCentre.Jacket;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Random;

import org.CodeCentre.Jacket.network.PacketListenerRunnable;
import org.CodeCentre.Jacket.utils.ByteUtils;

public class Server {
	private static Thread pt;
	private long serverid;
	private boolean invisible;
	private int port;
	private String motd;
	protected boolean init(HashMap<String, Object> options) {
		port = (options.containsKey("port") ? Integer.valueOf(options.get(
				"port").toString()) : 19132);
		invisible = (options.containsKey("invisible") ? Boolean.valueOf(options
				.get("invisible").toString()) : false);
		motd = (options.containsKey("motd") ? options.get("motd").toString() : "Default JacketCraft Server");
		DatagramSocket sock;
		try {
			sock = new DatagramSocket(port);
		} catch (IOException ex) {
			System.err.println("FAILED TO BIND PORT:");
			System.err.println(ex.toString());
			return false;
		}
		byte[] serverba = new byte[8];
		new Random().nextBytes(serverba);
		serverid = ByteUtils.bytesToLong(serverba);
		PacketListenerRunnable run = new PacketListenerRunnable(sock);
		pt = new Thread(run);
		pt.start();
		System.out.println("Started Server :)");
		return true;
	}

	public long getID() {
		return serverid;
	}

	public boolean isInvisible() {
		return invisible;
	}

	public String getMOTD() {
		// TODO Automatisch generierter Methodenstub
		return motd;
	}
}
