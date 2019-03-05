---
layout:     post   				          # 使用的布局（不需要改）
title:      Spring IoC与DI				   # 标题 
subtitle:   学习SpringIoC与DI的思想及实现          #副标题
date:       2019-01-22 				      # 时间
author:     Lih 						  # 作者
header-img: img/view02.jpg 	#这篇文章标题背景图片
catalog: true 						# 是否归档
tags:								#标签
    - Spring
---

#  Spring IoC，控制反转

控制反转，一种思想，优点是松耦合，

原先是程序员在一个类的内部new一个对象，然后使用。即：程序员控制对象。

控制反转前：用户控制对象。

控制反转后：IoC容器控制对象。

IoC控制对象的什么：生命周期（创建、销毁）、对象间依赖关系（通过DI）。

## IoC的实现——DI，依赖注入

IoC与DI的关系：

A组件依赖B组件，B组件被IoC容器注入A组件。

<https://www.cnblogs.com/xdp-gacl/p/4249939.html>

 

## IoC的过程

https://www.cnblogs.com/ITtangtang/p/3978349.html#a1

## IoC使用的设计模式

1、工厂模式 

2、单例模式 

3、策略模式 

4、装饰器模式

# Spring AOP

## AOP：降低耦合度，提高代码复用率

面向切面编程，将业务逻辑和系统逻辑（横切逻辑）分开，系统逻辑封装成模块。

例子：业务逻辑：登录、购物

​              横切逻辑：日志、权限检查

## 作用：低耦合，高复用

无AOP：日志系统要改，则要改所有的业务

有AOP：只改动日志系统

## AOP的实现：静态织入、动态代理

静态织入：AspectJ，编译时生成一个新的类，包含业务+切面。

动态代理：运行时增强，不会产生新的类

## Spring的AOP实现：动态代理

JDK动态代理——需要代理类实现接口：通过反射实现业务逻辑接口的实例，

CGLib动态代理——需要代理类不能是final：代理类=业务逻辑的子类，但是重写了方法，在方法前后加入了横切逻辑