package com.briup.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;

import com.briup.bean.Environment;
import com.briup.gather.Gather;
import com.briup.loger.Log;

/**
 * 客户端 发送数据到服务器端
 * @author zhijie
 * @created 2018年9月21日 上午9:22:29
 */
public class Client {
	private static Log log = new Log();
	public static void main(String[] args) {
		Socket socket = null;
		try {
			log.info("客户端开始连接服务器·············");
			socket = new Socket("127.0.0.1",22222);
			//获取集合对象
			Collection<Environment> collection = new Gather().gather();
			//发送数据
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			log.info("数据发送开始");
			oos.writeObject(collection);
			log.info("发送数据完成");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				log.warn("关闭socket服务");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("IO异常");
			}
		}
	}
}
