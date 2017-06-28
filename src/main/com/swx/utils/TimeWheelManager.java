package com.swx.utils;

import java.util.*;

/**
 * 
 * @author rodking
 *  时间轮,
 * 1.base 秒表，
 * 2.分钟
 * 3.小时
 * 4.天
 * 5.月
 * 6.日
 * 7.年
 */
public class TimeWheelManager {
	
	// 单利模式
	private enum Singleton
	{
		INSTANCE;
		
		private TimeWheelManager singleton;
		
		Singleton() { singleton = new TimeWheelManager();}
		
		private TimeWheelManager GetProcess(){ return singleton;}
	}
	
	private List<TimeLogic>[] baseTimes = new LinkedList[60];
	private int currentCount = 0;
	private final int TIME_CONST = 60; 
	
	public void Add(TimeLogic element)
	{
		if(null == element)
			return;
		
		if(element.getTime() >= 0 && element.getTime() < 60) // 时间（0，60）
			return;
		
		baseTimes[element.getTime()].add(element); // 向时间轮中添加逻辑
	}
	
	public void Update()
	{
		currentCount = ++currentCount % TIME_CONST;
		List lst = baseTimes[currentCount];
		// 执行逻辑
		Iterator<TimeLogic> it = lst.iterator();
		while(it.hasNext())
		{
			TimeLogic obj = it.next();
			obj.action();// 执行
			
			it.remove(); // 移除
		}
	}
}
