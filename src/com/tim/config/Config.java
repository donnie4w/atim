package com.tim.config;

import com.tim.log.LogLevel;

/**
 * ClassName: Config.java <br/>
 *
 * @author dong
 * @version
 * @since JDK 1.7
 * @classDesc 类描述: 配置类
 */
public class Config implements Cloneable {

	public final static String ENCODE = "utf-8";
	public final static String NS = "atim";
	public final static String VERSION = "1.0";

	static LogLevel LOGLEVEL = LogLevel.INFO;

	private String domain = "donnie4w@gmail.com";

	private String ip = "127.0.0.1";

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	private int port = 3737;
	private int heartbeat = 30;// default 30second
	private boolean reconnectionAllowed = true;
	private int connectTimeout = 10000; // default 30second
	private String resource = "timclient";
	private String loginName;
	private String password;
	private boolean TLS = false;
	private int TsslPort = 5757;

	/** 日志级别 (default info) */
	public static void SetLogLevel(LogLevel level) {
		LOGLEVEL = level;
	}

	public String getIp() {
		return ip;
	}

	/** 连接服务器ip地址 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	/** 连接服务器端口 */
	public void setPort(int port) {
		this.port = port;
	}

	public int getHeartbeat() {
		return heartbeat;
	}

	/** 心跳时间 （秒） */
	public void setHeartbeat(int heartbeat) {
		this.heartbeat = heartbeat;
	}

	public boolean isReconnectionAllowed() {
		return reconnectionAllowed;
	}

	/** 日志级别 */
	public static void setLogLevel(LogLevel level) {
		LOGLEVEL = level;
	}

	public static LogLevel getLogLevel() {
		return LOGLEVEL;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	/** 连接超时时间 （毫秒） */
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/** 是否断开重连，默认开启（秒） */
	public void setReconnectionAllowed(boolean reconnectionAllowed) {
		this.reconnectionAllowed = reconnectionAllowed;
	}

	public String getDomain() {
		return domain;
	}

	/** 域名，客户端标识，相当于appid */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTsslPort() {
		return TsslPort;
	}

	public void setTsslPort(int tsslPort) {
		TsslPort = tsslPort;
	}

	public boolean isTLS() {
		return TLS;
	}

	public void setTLS(boolean tLS) {
		TLS = tLS;
	}
}
