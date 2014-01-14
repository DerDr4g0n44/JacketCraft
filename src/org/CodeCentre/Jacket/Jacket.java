package org.CodeCentre.Jacket;

public final class Jacket {
	private static Server server;
	private static final Integer CURRENT_VERSION = 5;
	public static int getCurrentProtocolVersion()
	{
		return CURRENT_VERSION;
	}
	public static Server getServer()
	{
		return server;
	}
	
	public static void setServer(Server server)
	{
		if(Jacket.server != null)
		{
			System.out.println("Server could not be set.");
			return;
		}
		Jacket.server = server;
	}
	
	public static long getServerID()
	{
		return server.getID();
	}
}
