package com.brightengold.util;

import java.io.Serializable;

public enum LogType implements Serializable{
	/**
	 *添加
	 */
	ADD("添加"),
	/**
	 *删除
	 */
	DEL("删除"),
	/**
	 *编辑
	 */
	EDIT("编辑"),
	/**
	 *重置密码
	 */
	RESETPASSWORD("重置密码"),
	/**
	 *分配权限
	 */
	DISTRIBUTE("分配权限"),
	/**
	 *分配权限
	 */
	EXPORT("导出"),
	/**
	 *注销
	 */
	NSUBSCTIBE("注销"), 
	/**
	 * 发布
	 */
	PUBLISH("发布");

	private String name;
	private LogType(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
