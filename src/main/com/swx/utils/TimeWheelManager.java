package com.swx.utils;

import java.util.*;

/**
 * 
 * @author rodking 时间轮, 1.base 秒表， 2.分钟 3.小时 4.天 5.月 6.日 7.年
 */
public class TimeWheelManager {

	// 单利模式
	private enum Singleton {
		INSTANCE;

		private TimeWheelManager singleton;

		Singleton() {
			singleton = new TimeWheelManager();
		}

		private TimeWheelManager GetProcess() {
			return singleton;
		}
	}

	private List<TimeOutLogic>[] secList = new LinkedList[60]; // 秒钟 0 ～ 60
	private List<TimeOutLogic>[] minList = new LinkedList[60]; // 分钟 0 ～ 60
	private List<TimeOutLogic>[] hourList = new LinkedList[24]; // 分钟 0 ～ 24

	private int secCount = 0;
	private int minCount = 0;
	private int hourCount = 0;
	private final int TIME_CONST = 60;

	/**
	 * 添加时间逻辑
	 * 
	 * @param element
	 */
	public void Add(TimeOutLogic element) {
		if (null == element)
			return;

		if (element.getTime() >= 0 && element.getTime() < 60) // 秒时间（0，60）
		{
			if(null == secList[element.getTime()])
				secList[element.getTime()] = new LinkedList();
			
			secList[element.getTime()].add(element); // 秒列表
		} else if (element.getTime() >= 60 && element.getTime() < 3600) // 分钟(60,3600)
		{
			if(null == minList[element.getTime()])
				minList[element.getTime()] = new LinkedList();
			
			minList[element.getTime() / 60].add(element); // 分列表
			element.setTime(element.getTime() % 60);
		} else if (element.getTime() >= 3600 && element.getTime() < 24 * 3600) // 小时（
																				// 3600，24
																				// *
																				// 3600）
		{
			if(null == hourList[element.getTime()])
				hourList[element.getTime()] = new LinkedList();
			
			hourList[element.getTime() / 3600].add(element); // 小时列表
			element.setTime(element.getTime() % 3600);
		}

		return;
	}

	public void AddList(List<TimeOutLogic> elements) {
		if (null == elements || elements.isEmpty())
			return;

		for (TimeOutLogic element : elements) {
			Add(element);
		}
	}

	/**
	 * 时间向下走一秒
	 */
	public void Update() {

		if (secCount >= 60) // 分钟
		{

			if (minCount >= 60) // 小时
			{
			 	List<TimeOutLogic> cells = hourList[hourCount % 60];
			 	if(null != cells && false == cells.isEmpty())
			 	{
			 		for(TimeOutLogic cell:cells){
				 		cell.setTime(cell.getTime() - 3600);
			 			Add(cell);		
			 		}
			 	}
			 	hourList[hourCount % 60] = null;
		 		hourCount++;
			}

			List<TimeOutLogic> cells = minList[minCount % 60];
			if(null != cells && false == cells.isEmpty())
			{
				for(TimeOutLogic cell:cells)
				{
					cell.setTime(cell.getTime() - 60);
					Add(cell);
				}
			}
			
			minList[minCount % 60] = null;
			minCount++;
		}

		secCount = secCount % TIME_CONST;
		List lst = secList[secCount];
		if (null != lst) {
			// 执行逻辑
			Iterator<TimeOutLogic> it = lst.iterator();
			while (it.hasNext()) {
				TimeOutLogic logic = it.next();
				logic.action();// 执行
				it.remove(); // 移除
			}
		}

		++secCount;
	}
}
