package com.example.util;

import java.util.*;

//公共属性
public class Constants
{
	//保存Session的集合
	public static List<String> user=new ArrayList<String>();
	//保存消息的集合
	public static List<Map<String,String>> messageList=new ArrayList<Map<String,String>>();
	//Session和Thread映射集合
	public static Map<String,Thread> sessionThreadMap=new HashMap<String,Thread>();
	
	public static void addThread(String sessionId,Thread thread){
		sessionThreadMap.put(sessionId, thread);
	}

	public static void notifyAllThread(){
		//迭代器
		Iterator<Map.Entry<String, Thread>> it=sessionThreadMap.entrySet().iterator();
		//如果序列中还有元素就继续执行
			while(it.hasNext()){
			Map.Entry<String, Thread> entry=it.next();
			Thread thread=entry.getValue();
			synchronized(thread){
				thread.notify();
			}
		}
	}

	public static String getMessage(){
		//获取最后一个聊天记录
		Map<String,String> map=messageList.get(messageList.size()-1);
		String message=map.get("message");
		String time=map.get("time");
		return message;
	}
	public synchronized static void addMessage(String message,String time){
		Map<String,String> map=new HashMap<String,String>();
		map.put("message", message);
		map.put("time", time);
		messageList.add(map);
	}



}
