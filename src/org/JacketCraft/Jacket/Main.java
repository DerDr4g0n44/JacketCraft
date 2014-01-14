package org.JacketCraft.Jacket;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	private static boolean started = false;
	private static Logger log = Logger.getLogger("JacketCraft");

	public static void main(String[] args) {
		if (started)
			return;
		started = true;
		/*System.setErr(new PrintStream(new LogStream(Level.SEVERE, log), true));
		System.setOut(new PrintStream(new LogStream(Level.INFO, log), true));*/
		try {
			startServer(args);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void startServer(String[] args) throws IOException {
		Server s = new Server();
		checkFiles();
		Properties props = new Properties();
		props.load(new FileInputStream(new File("server.properties")));
		HashMap<String, Object> options = new HashMap<String, Object>();
		for (Entry<Object, Object> entry : props.entrySet()) {
			options.put((String) entry.getKey(), entry.getValue());

		}
		boolean successfull = s.init(options);
		if (!successfull)
			System.exit(0);
		Jacket.setServer(s);
	}

	private static void checkFiles() throws IOException {
		File propfile = new File("server.properties");
		if (!propfile.exists())
			propfile.createNewFile();
		File pluginsdir = new File("plugins" + File.separator);
		if (!pluginsdir.exists())
			pluginsdir.mkdir();
	}

	private static class LogStream extends ByteArrayOutputStream {
		private Logger l;
		private Level lev;

		public LogStream(Level level, Logger log) {
			super();
			l = log;
			lev = level;
		}

		public void flush() throws IOException {
			super.flush();
			reset();
			l.log(lev, toString());
		}
	}
}
