atim是tim即时聊天服务器的java客户端，可于android移动端     <br/>
交互流程已经在程序中实现，暴露给开发者的是几个参数值的设置与监听器的设置<br/><br/>

		Config.setLogLevel(LogLevel.INFO);   打印日志级别 <br/>
		Config config = new Config();			 <br/>
		config.setDomain("wuxiaodong");		  tim在登陆时需要设置域名 <br/>
		config.setHeartbeat(40);              心跳时间 默认是40s <br/>
		config.setIp("127.0.0.1");			  tim服务器ip地址 <br/>
		config.setPort(3737);				  tim服务器端口 <br/>
		config.setResource("iphone5s");	      客户端来源标识，可以是手机型号或 <br/>
		config.setReconnectionAllowed(true);  是否断开重连，默认为真 <br/>

    AckListener 是服务器ack回复的监听接口 ，登陆成功后的服务器反馈信息等 <br/>
    MessageListener  是服务器推送message的监听接口 ，接收好友或群的信息 <br/>
	PresenceListener 是用户在线状态的监听接口 ，如好友上下线或离开等状态 <br/> <br/>
	
	Client对象是主要的操作接口  ：提供 login , sendMessage ,close 等方法 <br/><br/>
	
	具体的操作方式请参考 test包中TestClient.java 类