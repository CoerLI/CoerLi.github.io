---
layout:     post   				        	# 使用的布局
title:      Java并发：并发入门与Synchronized探究				  		# 标题 
subtitle:   探究Java Synchronized的设计和实现原理          			#副标题
date:       2019-02-23 				      		# 时间
author:     Lih 						# 作者
header-img: img/office08.jpg 					#这篇文章标题背景图片
catalog: true 							# 是否归档
tags:								#标签
    - Java
    - JVM
    - 多线程
---

# 线程

**从一个经典问题开始：什么是线程？线程与进程的区别？**

进程：程序的一次执行过程，拥有自己的内存空间，是操作系统资源分配的最小单位。进程侧重于资源。一个进程拥有多个线程。

线程：是操作系统能够进行运算调度的最小单位，被包含在进程之中，是进程的实际运算单位。线程侧重于CPU，在单核CPU情况下，同时最多有一个线程执行。只有在多核CPU中才能真正实现线程的并发执行。一个进程可以有多个线程并发执行。在一些操作系统中也被称为轻量级进程。多个线程共享进程的资源，但是独占自己的线程栈。

**为什么区分进程和线程？**

CPU运算很快，系统的性能受制于其他资源（内存，IO），当只有进程（或者说进程只有一个线程）存在时，若进行一个长IO等待的任务，进程就会完全暂停，等待响应。若有多个线程，则只会阻塞一个线程，其他线程正常运转。另外，线程的切换较快，

**并行与并发：关注是否同时，并行是一种能力，并发是一种策略**

并行：**同时**处理两个任务，单核CPU无法同时执行两个任务，即无法并行

并发：**一段时间内**两个任务交替执行，但是同一时间只有一个任务执行，单核CPU可以轮流执行任务，即可以并发。

#  线程安全

## 什么是线程安全？

**如果一段代码，在多线程环境下执行时，不需要调用方进行额外的同步就能保证结果正确，那么这段代码就是线程安全的。**

## 如何实现线程安全？

**线程安全需要解决的核心问题就是共享变量的安全，保证安全的方法就是保证对共享变量的互斥访问。**

**Java中提供了一些机制来保证线程的互斥访问。**

# synchronized

## synchronized的作用

Synchronized关键字保证了同一个对象只能同时被一个线程所获取，当其他线程访问被占有的锁时，会被阻塞。

Synchronized针对的是对象，有多种使用方式

### synchronized + 普通方法

```java
public synchronized void fun(){	···	}	//锁住的是实例对象
```

### synchronized + static方法

```java
public static synchronized void fun (){	···	}	//锁住的是class对象
```

### synchronized 代码块

```java
synchronized (obj) { ··· }		//锁住的是obj对象
```



## synchronized的实现原理

每个对象都拥有一个Monitor对象，对象的对象头中记录了Monitor的地址，当一个线程想要访问对象时，需要通过对象头获取Monitor对象。

Monitor对象维护了两个队列，一个EntryList，一个WaitSet和一个对象Owner。

![屏幕快照 2019-02-18 下午4.11.59](/Users/lihang/Desktop/屏幕快照 2019-02-18 下午4.11.59.png)

当线程想要获取对象时，进入EntryList。当Owner为空时，选取一个EntryList中的线程作为Owner，当线程执行wait()方法时，会进入等待序列waitSet，等待notify()。线程执行完毕释放锁后，Owner置空。

### 执行synchronized代码块时的实现原理

synchronized代码块是显示获取锁的，java在编译同步代码块，会在进入和退出方法时加上monitorenter和monitorexit。当线程进入代码块时会执行命令，去获取monitor对象。离开代码块时则会释放monitor对象。

### 执行synchronized方法时的实现原理

synchronized方法的实现是隐式的，java在编译方法时会在方法表结构中设置ACC_SYNCHRONIZED字段。当执行方法时会检查字段，如果发现ACC_SYNCHRONIZED字段，则会在进入方法和结束方法时获取和释放monitor。

## Java对synchronzied的优化

对象的锁状态有：无锁、偏向锁、轻量级锁、（自旋锁）、重量级锁。对应着锁的升级过程。

java针对锁还有一些优化：自旋锁，锁消除

## wait()/notify()/notifyAll()

这三个方法是Object类的方法，它们就是将线程存入Monitor对象的waitSet和取出waitSet方法，因此它们必须在获取锁的状态时才能执行。

wait()方法会释放锁，并且进入waitSet

notify()/notifyAll()方法会唤醒waitSet中的线程

