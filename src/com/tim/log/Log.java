package com.tim.log;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

import com.tim.config.Config;
import com.tim.exception.TimException;

public class Log {
	private Logger logger;
	private static Log log = new Log();

	private Log() {
		logger = Logger.getLogger("com.tim");
		logger.setLevel(Level.INFO);
		logger.setUseParentHandlers(false);
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.INFO);
		consoleHandler.setFormatter(new LogFormat());
		logger.addHandler(consoleHandler);
	}

	public static Log getLogger() {
		return log;
	}

	public void info(Object... objects) {
		if (Config.getLogLevel() == LogLevel.INFO) {
			StringBuilder sb = new StringBuilder();
			sb.append(stacks()).append(" ");
			sb.append("[").append(" ");
			for (Object o : objects) {
				if (o instanceof Exception) {
					sb.append(stream((Exception) o)).append(" ");
				} else {
					sb.append(o).append(" ");
				}
			}
			sb.append(" ").append("]");
			sb.append("\n");
			logger.log(Level.INFO, sb.toString());
		}
	}

	public void severe(Object... objects) {
		if (Config.getLogLevel() != LogLevel.OFF) {
			StringBuilder sb = new StringBuilder();
			sb.append(stacks()).append(" ");
			sb.append("[").append(" ");
			for (Object o : objects) {
				if (o instanceof Exception) {
					sb.append(stream((Exception) o)).append(" ");
				} else {
					sb.append(o).append(" ");
				}
			}
			sb.append(" ").append("]");
			sb.append("\n");
			logger.log(Level.SEVERE, sb.toString());
		}
	}

	private static String stream(Exception o) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			PrintStream ps = new PrintStream(baos);
			o.printStackTrace(ps);
			return baos.toString("utf-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (Exception e) {
				}
			}
		}
	}

	private String stacks() {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		StringBuilder sb = new StringBuilder();
		sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append(" ");
		sb.append(stacks[3].getClassName()).append(" ");
		sb.append(stacks[3].getLineNumber()).append(" ");
		return sb.toString();
	}

	public static void main(String[] args) {
		Log.getLogger().info("a", "b", "c");
		Log.getLogger().severe("a", "b", "c");
		TimException e = new TimException("dddddddddda3343434\ndfdffdfdfdf\nddd9urj3");
		Log.getLogger().info(e);
	}
}

class LogFormat extends Formatter {
	@Override
	public String format(LogRecord record) {
		StringBuilder sb = new StringBuilder();
		sb.append("TIM").append(" ");
		sb.append(record.getLevel()).append(" ");
		sb.append(record.getMessage());
		return sb.toString();
	}
}
