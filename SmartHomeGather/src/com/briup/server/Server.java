package com.briup.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;

import com.briup.bean.Environment;
import com.briup.loger.Log;

/**
 * 服务器端 接受客户端发送过来的数据并且保存到数据库中
 * @author zhijie
 * @created 2018年9月21日 上午9:15:14
 */
public class Server {
	private static Log log = new Log();
	public static void main(String[] args) {
		ServerSocket ss = null;
		int num = 1;
		try {
			ss = new ServerSocket(22222);
			while (true) {
				//监听客户端是否连接
				log.info("开始等待链接.....");
				Socket socket = ss.accept();
				log.info("客户端连接成功·······");
				//服务器端接收每个客户端异常
				new Thread(new ServerThread(socket, "客户端"+num++)).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("IO异常");
		}
	}
}
/**
 * 服务器端子线程 没线程用于连接每个客户端
 * @author zhijie
 * @created 2018年9月21日 下午2:32:35
 */
class ServerThread implements Runnable {
	private static Log log = new Log();
	private Socket socket;
	/**客户端名称*/
	private String clienName;
	public ServerThread(Socket socker,String clienName) {
		// TODO Auto-generated constructor stub
		this.socket = socker;
		this.clienName = clienName;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		InputStream in = null;
		ObjectInputStream ois = null;
		try {
			in = socket.getInputStream();
			ois = new ObjectInputStream(in);
			Object object = ois.readObject();
			log.info("数据接收成功");
			if (object instanceof Collection) {
				Collection<Environment> collection = (Collection<Environment>) object;
				System.out.println(clienName+"接收数据大小:"+collection.size());
				//数据入库
				log.info("开始入库········");
				new DBStore().saveToDB(collection);
				log.info("入库结束`````````");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("IO异常");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("类不存在");
		} finally {
			try {
				socket.close();
				log.warn("socket关闭");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("IO异常");
			}
		}	
	}
}












