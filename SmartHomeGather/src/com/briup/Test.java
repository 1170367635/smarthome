package com.briup;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import com.briup.bean.Environment;
import com.briup.gather.Gather;

public class Test {
	public static void main(String[] args) {
		System.out.println(new Date(1516323598936L));
		System.out.println(new Timestamp(1516323598936L));
		Collection<Environment> collection = new Gather().gather();
		System.out.println(collection.toString());
		System.out.println(collection.size());
	}
}
