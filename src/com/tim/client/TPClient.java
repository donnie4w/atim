package com.tim.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.tim.config.Config;
import com.tim.log.Log;

/**
 * ClassName:TPClient <br/>
 * Date: 2016年4月21日 上午11:29:39 <br/>
 * 
 * @author dong
 * @version
 * @since JDK 1.6
 * @see
 */
public class TPClient<T> {
	Config config;

	private TPClient(Config config) {
		this.config = config;
	}

	@SuppressWarnings("rawtypes")
	public static TPClient newInstance(Config config) {
		return new TPClient(config);
	}

	@SuppressWarnings("unchecked")
	public T getClient(Client target) {
		return (T) ClientProxy.newInstance(config).bind(target);
	}
}

class ClientProxy implements InvocationHandler {
	private static final Log logger = Log.getLogger();
	private Client client;
	Config config;

	private ClientProxy(Config config) {
		this.config = config;
	}

	public static ClientProxy newInstance(Config config) {
		return new ClientProxy(config);
	}

	public Object bind(Client target) {
		this.client = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public synchronized Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		// int count = 1;
		try {
			// result = invokeloop(client, proxy, method, args, count);
			result = method.invoke(client, args);
		} catch (Exception e) {
			logger.severe(e);
			throw e;
			// client = ClientFactory.getClient(client, config);
		} finally {
		}
		return result;
	}

	// public static Object invokeloop(Object target, Object proxy, Method method, Object[] args, int count) throws Exception {
	// if (count <= 0) {
	// throw new Exception("method invoke error" + method.getName());
	// }
	// try {
	// return method.invoke(target, args);
	// } catch (Exception e) {
	// logger.severe(e);
	// Thread.sleep(100);
	// invokeloop(target, proxy, method, args, count--);
	// }
	// return null;
	// }
}