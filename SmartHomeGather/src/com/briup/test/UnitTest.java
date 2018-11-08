package com.briup.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 测试junit
 * @author zhijie
 * @created 2018年9月25日 下午3:19:46
 */
public class UnitTest {
	/**
	 * 在所有方法之前调用
	 */
	@BeforeClass
	public static void allBefore() {
		System.out.println("allbefore");
	}
	/**
	 * 在测试方法之前调用
	 */
	@Before
	public void before(){
		System.out.println("before");
	}
	@Test
	public void test11(){
		System.out.println("test");
	}
	/**
	 * 在测试方法之后调用
	 */
	@After
	public void after() {
		System.out.println("after");
	}
	/**
	 * 在所有方法之后调用
	 */
	@AfterClass
	public static void allAfter() {
		System.out.println("allafter");
	}
}
