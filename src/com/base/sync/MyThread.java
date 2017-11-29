package com.base.sync;


/**
 * 1 什么是线程安全
 * 线程安全概念：当多个线程访问某一个类（对象或方法）时，这个对象始终都能表现出正确的行为，那么这个类（对象或方法）就是线程安全的。
 * 
 * 2 synchronized：可以在任意对象及方法上加锁，而加锁的这段代码称为"互斥区"或"临界区"
 *    当多个线程进行竞争，获取cpu,获取锁,执行run中的方法
 *    
 *    当不加synchronized
 *    t2 count = 3
 *    t5 count = 0
 *    t1 count = 3
 *    t4 count = 1
 *    t3 count = 2
 *    线程不安全
 *    
 *    加synchronized
 *    t1 count = 4
 *    t5 count = 3
 *    t4 count = 2
 *    t3 count = 1
 *    t2 count = 0  
 * 
 * 3 为什么使用.start  而不是直接使用.run      
 *   直接使用.run 是在主线程简单的调用api
 *   使用.start调用本地方法启动一个新的线程执行run中的方法   
 *   
 * @author 黑猫
 *
 */
public class MyThread extends Thread{
	
	private int count = 5 ;
	
	//synchronized加锁
	public synchronized void run(){
		count--;
		System.out.println(this.currentThread().getName() + " count = "+ count);
	}
	
//	public  void run(){
//		count--;
//		System.out.println(this.currentThread().getName() + " count = "+ count);
//	}
	
	public static void main(String[] args) {
		/**
		 * 分析：当多个线程访问myThread的run方法时，以排队的方式进行处理（这里排对是按照CPU分配的先后顺序而定的），
		 * 		一个线程想要执行synchronized修饰的方法里的代码：
		 * 		1 尝试获得锁
		 * 		2 如果拿到锁，执行synchronized代码体内容；拿不到锁，这个线程就会不断的尝试获得这把锁，直到拿到为止，
		 * 		   而且是多个线程同时去竞争这把锁。（也就是会有锁竞争的问题）
		 */
		MyThread myThread = new MyThread();
		Thread t1 = new Thread(myThread,"t1");
		Thread t2 = new Thread(myThread,"t2");
		Thread t3 = new Thread(myThread,"t3");
		Thread t4 = new Thread(myThread,"t4");
		Thread t5 = new Thread(myThread,"t5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}












