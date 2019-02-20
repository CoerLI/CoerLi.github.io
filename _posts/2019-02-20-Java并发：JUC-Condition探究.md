---
layout:     post   				        		# 使用的布局
title:      Java并发：JUC CountDownLatch、CyclicBarrier、Semaphore	# 标题 
subtitle:   探究JUC下的 CountDownLatch、CyclicBarrier、Semaphore 		#副标题
date:       2019-02-20 				      			# 时间
author:     Lih 							# 作者
header-img: img/office04.jpg 						#这篇文章标题背景图片
catalog: true 								# 是否归档
tags:									#标签
    - Java
    - 多线程


---

## CountDownLatch

功能：设置一个值，让线程等待其他线程将这个值归0，核心是操作count和等待count

设置初始值Count

### await()

await()会在count != 0时阻塞，当count==0时继续运行。await()可以设置时间，超过时间也可以继续运行。

### countDown()

将count-1

### 实现原理：



## CyclicBarrier

功能：规定一个count，当count个线程都达到await()时继续运行，否则阻塞。核心是有count个线程到达await()。

### await()

await()会在到达await()的线程不足count时阻塞，当达到count时继续运行。await()可以设置时间，超过时间也可以继续运行。

### 实现原理：



## Semaphore

功能：设置资源数目，线程资源足够时运行，否则阻塞

设置初始值Count

###  acquire()

 acquire()、 acquire( num ) 会要求一定资源，当空闲资源足够时会占有资源并继续运行，否则阻塞。

### release()

release()、release( num ) 会释放一定资源。

### 实现原理：