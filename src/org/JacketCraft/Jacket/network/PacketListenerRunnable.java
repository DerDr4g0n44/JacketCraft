package org.JacketCraft.Jacket.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.JacketCraft.Jacket.Main;
public class PacketListenerRunnable implements Runnable {
	private static DatagramSocket socket;
	public PacketListenerRunnable(DatagramSocket sock) {
		socket = sock;
	}

	public void run() {
		while(true){
			DatagramPacket packet = new DatagramPacket(new byte[Short.MAX_VALUE], Short.MAX_VALUE);
			try {
				socket.receive(packet);
			} catch (IOException e) {
			}
			try {
				PacketHandler.handle(packet);
			} catch (IOException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
				System.err.println("Error handling Packet " + packet.getData()[0]);
			}
		}
	}
	
	protected static DatagramSocket getServerSocket()
	{
		return socket;
	}
}
