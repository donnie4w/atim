atim是tim即时聊天服务器的java客户端，可用于android移动端     <br/>
交互流程已经在程序中实现，暴露给开发者的是几个参数值的设置与监听器的设置<br/><br/>

		Config.setLogLevel(LogLevel.INFO);   打印日志级别 
		Config config = new Config();			 
		config.setDomain("wuxiaodong");		  tim在登陆时需要设置域名 
		config.setHeartbeat(40);              心跳时间 默认是40s 
		config.setIp("127.0.0.1");			  tim服务器ip地址 
		config.setPort(3737);				  tim服务器端口 
		config.setResource("iphone5s");	      客户端来源标识，可以是手机型号或 
		config.setReconnectionAllowed(true);  是否断开重连，默认为真 
		config.setTLS(true); 				  用TLS传输
		config.setTsslPort(5757); 			  服务器TLS端口

    AckListener 是服务器ack回复的监听接口 ，登陆成功后的服务器反馈信息等 
    MessageListener  是服务器推送message的监听接口 ，接收好友或群的信息 
	PresenceListener 是用户在线状态的监听接口 ，如好友上下线或离开等状态 
	
	Client是主要的操作接口  ：提供 login , sendMessage ,close 等方法 
	
	具体的操作方式请参考 test包中TestClient.java 类