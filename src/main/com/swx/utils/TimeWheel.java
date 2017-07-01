package com.swx.utils;

import java.util.*;

/**
 * @author rodking
 * 时间轮 (单元)
 * 自定义时间轮 
 */
public class TimeWheel {
	/** 时间刻度 */
	private int timeScale; 
	
	private int currTime;
	
	/** 时间列表 */
	private List<TimeOutLogic>[] logicLists;
	
	public TimeWheel(int timeScale)
	{
		this.timeScale = timeScale;
		this.logicLists = new ArrayList[this.timeScale];
	}
	
	/**
	 * 
	 * @param element
	 * 添加时间
	 */
	public void Add(TimeOutLogic element) 
	{
		if (null == element)
			return;
		
		int time = element.getTime();
		if(time <= 0 || time>= timeScale) // 小于或者大于 时间刻度返回
			return;
		
		if(this.logicLists[time] == null)
			this.logicLists[time] = new ArrayList();
		
		this.logicLists[time].add(element);
	}
	
	/**
	 *  更新
	 */
	public void Update() 
	{
		currTime = currTime % timeScale;
		List<TimeOutLogic> lst = logicLists[currTime];
		
		if (null != lst && false == lst.isEmpty()) {
			// 执行逻辑
			Iterator<TimeOutLogic> it = lst.iterator();
			while (it.hasNext()) {
				TimeOutLogic logic = it.next();
				logic.action();// 执行
				it.remove(); // 移除
			}
			
			// 清理这个时间轮
			if(true == lst.isEmpty())
				logicLists[currTime] = null;
		}

		++currTime;
	}
	
}
