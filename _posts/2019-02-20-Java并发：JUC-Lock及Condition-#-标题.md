---
layout:     post   				        	# 使用的布局
title:      Java并发：JUC Lock及Condition				# 标题 
subtitle:   探究JUC下Lock的设计和实现原理          			#副标题
date:       2019-02-20 				      		# 时间
author:     Lih 						# 作者
header-img: img/office04.jpg 					#这篇文章标题背景图片
catalog: true 							# 是否归档
tags:								#标签
    - Java
    - 多线程


---

# Lock

**JUC下的接口，可以显式的获取锁和释放锁，实现的原理依赖JNI的CAS操作，与Synchronized的JVM实现完全不同。**

## ReentrantLock

**Lock实现的可重入锁，有lock()方法和unlock()方法，**

查看lock()的实现，其内部维护了一个Sync类型的对象

```java
public void lock() {
    //调用了sync的lock方法
	sync.lock();
}
```

查看sync，发现其继承了AbstractQueuedSynchronizer（AQS），队列同步器

# 队列同步器AQS

AQS使用使用一个int型变量state表示锁的状态，state > 1表示锁被持有，state = 0表示锁被释放。

state的修改使用CAS操作，保证了并发时的安全性。

公平锁：

AQS内部还维护了一个双向链表作为等待队列。

一个线程想要获取锁的话，会先尝试获取，线程获取锁失败时，会将线程引用+当前state信息+waitState状态封装为一个Node，通过CAS操作将Node插入队尾，此时会再尝试获取锁，但是会检查前面是否有等待的线程，保证了执行顺序，并且阻塞线程。当锁被释放时，AQS会唤醒所有的节点，处于等待队列的节点会不断检测自己是否是head的下一个节点，如果是，则尝试获取锁，获取成功则将自己设为head，表示那么下一次就是下一个线程获取锁（公平锁的关键）。

非公平锁：

一个线程想要获取锁的话，会直接CAS修改state，抢占失败则入队列。当锁被释放时，AQS会唤醒所有的节点，但是与公平锁不同的是，每个线程会直接CAS修改state，不会关心队列中线程的顺序。



```java
abstract static class Sync extends AbstractQueuedSynchronizer{
    ...
    abstract void lock();
    ...
}
// Sync有两个实现类，分别是公平锁FairSync和非公平锁NonfairSync，默认为NonfairSync
static final class NonfairSync extends Sync {
	final void lock() {
        // 此处调用了CAS操作，检查state，若为 0，则置为 1
		if (compareAndSetState(0, 1))
            // 将ExclusiveOwnerThread设为当前线程
			setExclusiveOwnerThread(Thread.currentThread());
		else
            //获取锁失败时，执行acquire
			acquire(1);
	}
}
```

其先执行compareAndSetState，成功则执行setExclusiveOwnerThread

​						    失败则执行acquire

先看compareAndSetState，其是AbstractQueuedSynchronizer的方法

```java
// AQS内部维护了一个双向链表和一个状态state
// 双向链表是waitList，存储着被阻塞的线程，state表示线程的状态
private transient volatile Node head;
private transient volatile Node tail;
private volatile int state;

protected final boolean compareAndSetState(int expect, int update) {
	// 调用了unsafe对象的compareAndSwapInt方法
    // this是一个双向链表的Node，stateOffset是
	return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
}

// 查看compareAndSwapInt方法，其是Unsafe类提供的一系列native方法
public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
```

再查看acquire方法，它也是AbstractQueuedSynchronizer的方法

```java
public final void acquire(int arg) {
    // 再次获取锁，将其加入到waitList末尾，addWaiter方法使用了CAS和自旋操作
	if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
		selfInterrupt();
}
```

## Condition

**Condition可以理解为一个针对各种情况的wait()和notify()，非常适合线程间通信。**

Condition是绑定lock的，通过lock.newCondition()方法得到，底层仍然是AQS的newCondition()。

### 实习原理：

Condition的实现逻辑与AQS类似，都是维护一个链表。当使用Condition时，会将Node在AQS的链表和Condition的链表中切换，来实现阻塞和唤醒。

### 运行例子：

1、当线程A执行lock.lock()方法时，会将线程封装成Node，移动到AQS的链表中

2、当线程A执行condition.await()方法时，会将线程从AQS的链表移出，同时释放锁，然后将Node移入Condition的链表中。

3、当线程B执行condition.signal()时，会将Condition的链表中的线程A移动到AQS的链表中，但是此时线程A并未被立即唤醒，而是等待锁释放时，AQS来唤醒全部节点。