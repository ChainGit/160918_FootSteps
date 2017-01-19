package com.bxd.day12;

import java.util.concurrent.locks.*;

class Res{
	
	private static Res r;
	
	private static int id;
	private static String res;
	
	private static boolean flag;
	
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition conSet  = lock.newCondition(); 
	private final Condition conGet  = lock.newCondition(); 
	
	private Res(){}
	
	@SuppressWarnings("static-access")
	public void set(int id,String res) throws InterruptedException{
		lock.lock();
		try{
			while(flag)
				conSet.await();
			this.id=id;
			this.res=res;
			System.out.println(Thread.currentThread().getName()+" : set : "+id+" "+res);
			flag=true;
			conGet.signal();
		}finally{
			lock.unlock();
		}
	}
	
	@SuppressWarnings("static-access")
	public void get() throws InterruptedException{
		lock.lock();
		try{
			while(!flag)
				conGet.await();
			System.out.println(Thread.currentThread().getName()+" : get : "+id+" "+res);
			this.id=-1;
			this.res=null;
			flag=false;
			conSet.signal();
		}finally{
			lock.unlock();
		}
	}
	
	public static Res getInstance(){
		if(r==null)
			synchronized(Res.class){
				if(r==null)
					r=new Res();
			}
		return r;
	}

}

class Producer implements Runnable{
	
	private static Res r;
	
	@SuppressWarnings("static-access")
	public Producer(){
		this.r = Res.getInstance();
	}
	
	public void run(){
		int n=0;
		while(true){
			try{
				Thread.sleep(100);
			}catch(Exception e){
				System.out.println(Thread.currentThread().getName()+" : "+"Thread.sleep() Exception");
			}
			try{
				r.set(n,"Box"+n);
			}catch(InterruptedException e){
				System.out.println(Thread.currentThread().getName()+" : "+"set() InterruptedException");
			}
			n++;
		}
	}

}

class Consumer implements Runnable{
	
	private static Res r;
	
	@SuppressWarnings("static-access")
	public Consumer(){
		this.r = Res.getInstance();
	}
	
	public void run(){
		while(true){
			try{
				Thread.sleep(100);
			}catch(Exception e){
				System.out.println(Thread.currentThread().getName()+" : "+"Thread.sleep() Exception");
			}
			try{
				r.get();
			}catch(InterruptedException e){
				System.out.println(Thread.currentThread().getName()+" : "+"get() InterruptedException");
			}
		}
	}

}

public class ConcurrentTest{

	public static void main(String[] args){
		Producer pro = new Producer();
		Consumer con = new Consumer();
		
		Thread pt1 = new Thread(pro);
		Thread pc1 = new Thread(con);
		
		pt1.setDaemon(true);
		pc1.setDaemon(true);
		
		pt1.start();
		pc1.start();
		
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			System.out.println(Thread.currentThread().getName()+" : "+"Thread.sleep() Exception");
		}
		
		pt1.interrupt();
		pc1.interrupt();
		
		while(true){
			try{
				Thread.sleep(10000);
			}catch(Exception e){
				System.out.println(Thread.currentThread().getName()+" : "+"Thread.sleep() Exception");
			}		
		}
		
		//System.out.println(Thread.currentThread().getName()+" Over");
		
	}

}