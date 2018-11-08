package com.briup.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import com.briup.bean.Environment;
import com.briup.loger.Log;
import com.briup.util.DBUtil;

/**
 * 数据保存到Oracle中
 * 
 * @author zhijie
 * @created 2018年9月21日 上午10:12:11
 */
public class DBStore {
	private static Log log = new Log();
	private static PreparedStatement pstmt = null;
	
	/**
	 * 入库
	 * 
	 * @param envs
	 */
	public static void saveToDB(Collection<Environment> envs) {
		//获取数据库连接对象
		Connection connection = DBUtil.getConnection();
		//用于计算放入批处理的个数
		int count = 0;
		//用于表示下一天
		int nextDay = 0;
		for (Environment env : envs) {
			// 将每个对象插入到对应的表中
			// 拿到这个月的某一天
			int day = getDay(env);
			if (pstmt == null||nextDay != day) {
				nextDay = day;
				//执行上一天的残余
				if (pstmt != null) {
					try {
						System.out.println(count);
						pstmt.executeBatch();
						//pstmt.addBatch();
						pstmt.clearBatch();
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				String sql = "insert into e_detail_" + day + " values(?,?,?,?,?,?,?,?,?)";
				try {
					pstmt = connection.prepareStatement(sql);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error("SQL异常");
				}
			}
			try {
				pstmt.setObject(1, env.getName());
				pstmt.setObject(2, env.getSrcId());
				pstmt.setObject(3, env.getDesId());
				pstmt.setObject(4, env.getSensorAddress());
				pstmt.setObject(5, env.getCounter());
				pstmt.setObject(6, env.getCmd());
				pstmt.setObject(7, env.getStatus());
				pstmt.setObject(8, env.getData());
				pstmt.setObject(9, env.getDate());
				pstmt.addBatch();
				if (++count%1000==0) {
					pstmt.executeBatch();
					pstmt.clearBatch();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("SQL异常");
			}
			// int day = Integer.parseInt(timestamp.toString().substring(8,
			// 10));
//			String sql = "insert into e_detail_" + day + " values(?,?,?,?,?,?,?,?,?)";
//			DBUtil.executeUpdate(sql,
//					new Object[] { env.getName(), env.getSrcId(), env.getDesId(), env.getSensorAddress(),
//							env.getCounter(), env.getCmd(), env.getStatus(), env.getData(), env.getDate() });
		}
		if (pstmt != null) {
			try {
				pstmt.executeBatch();
				pstmt.clearBatch();
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("SQL异常");
			}
		}
	}
	/**
	 * 获取环境对象对应的某一天
	 * @param environment 环境对象
	 * @return 某一天
	 */
	@SuppressWarnings("static-access")
	private static int getDay(Environment env) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(env.getDate());
		return calendar.get(calendar.DAY_OF_MONTH);
	}
}
