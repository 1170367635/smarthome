package com.briup.gather;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.briup.bean.Environment;
import com.briup.loger.Log;

/**
 * 解析radwtmp文件，将每行记录抽象成Environment对象
 * @author zhijie
 * @created 2018年9月20日 上午9:57:23
 */
public class Gather {	
	Collection<Environment> ens = new ArrayList<>();
	private static Map<String, List<String>> name =  new HashMap<>();
	private static Log log = new Log();
	static{
		List<String> co2 = new ArrayList<>();
		co2.add("co2");
		name.put("1280",co2 );
		List<String> illumination = new ArrayList<>();
		illumination.add("illumination");
		name.put("256", illumination);
		List<String> tem = new ArrayList<>();
		tem.add("tem");
		tem.add("hum");
		name.put("16", tem);
	}
	/**
	 * 解析文件，获得数据对象的集合
	 * @return 数据对象集合
	 */
	public Collection<Environment> gather(){
	
	
		//开始解析文件
		//创建随机处理流对象
		log.info("文件读取开始····");
		RandomAccessFile rsf = null;
		String line = null;
		try {
			log.debug("rsf发生空指针······");
			rsf = new RandomAccessFile("radwtmp", "r");
			while ((line = rsf.readLine()) != null) {
				//处理每行记录，封装成一个Environment对象并且放到集合中
				lineToEnvironment(line);
				//ens.add(environment);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				log.warn("关闭rsf流");
				rsf.close();
				log.info("文件读取结束········");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("发生IO异常，文件关闭错误·········");
			}
		}
		return ens;
	}
	/**
	 * 字符串转化为Environment对象,并把该对象放到集合中
	 * @param line 每行记录
	 * @return Environment对象
	 */
	private  void lineToEnvironment(String line) {
		//分割字符串
		String[] strs = line.split("[|]");
		Environment env = new Environment();
		if (name.containsKey(strs[3])) {
			if ("16".equals(strs[3])) {
				env.setName(name.get(strs[3]).get(0));
				env.setData(Integer.parseInt(strs[6].substring(0, 4),16) * 0.00268127 - 46.85);
				setEnv(env, strs);
				
				Environment humidity = new Environment();
				humidity.setName(name.get(strs[3]).get(0));
				humidity.setData(Integer.parseInt(strs[6].substring(4, 8),16) * 0.00190735-6);
				setEnv(humidity, strs);
			} else {
				env.setName(name.get(strs[3]).get(0));
				env.setData(Integer.parseInt(strs[6].substring(0, 4),16)*1.0);
				setEnv(env, strs);
			}
		}
//		if ("16".equals(strs[3])) {
//			//设置温度
//			env.setName("tem");
//			env.setData(Integer.parseInt(strs[6].substring(0, 4),16) * 0.00268127 - 46.85);
//			setEnv(env, strs);
//			
//			Environment humidity = new Environment();
//			humidity.setName("hum");
//			humidity.setData(Integer.parseInt(strs[6].substring(4, 8),16) * 0.00190735-6);
//			setEnv(humidity, strs);
//		}
//		if ("256".equals(strs[3])) {
//			env.setName("Illumination");
//			env.setData(Integer.parseInt(strs[6].substring(0, 4),16)*1.0);
//			setEnv(env, strs);
//		}
//		if ("1280".equals(strs[3])) {
//			 env.setName("co2");
//			 env.setData(Integer.parseInt(strs[6].substring(0, 4),16)*1.0);
//			 setEnv(env, strs);
//		}
		
	}
	/**
	 * 给环境对象赋值
	 * @param env 赋值的环境对象
	 * @param strs 字符串数组
	 */
	private void setEnv(Environment env, String... strs ) {
		//100|101|2|16|1|3|5d786fcc02|1|1516323610923
		env.setSrcId(Integer.parseInt(strs[0]));
		env.setDesId(Integer.parseInt(strs[1]));
		env.setDevId(Integer.parseInt(strs[2]));
		env.setSensorAddress(Integer.parseInt(strs[3]));
		env.setCounter(Integer.parseInt(strs[4]));
		env.setCmd(Integer.parseInt(strs[5]));
		env.setStatus(Integer.parseInt(strs[7]));
		env.setDate(new Timestamp(Long.parseLong(strs[8])));
		ens.add(env);
	}
}














