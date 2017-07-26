package com.bxd.day11;

class Ticket implements Runnable{
	private int num = 100;
	
	//public void run(){
	public synchronized void run(){
		while(num>0){
			try{Thread.sleep(10);}catch(Exception e){}
			num--; 
			System.out.println(Thread.currentThread()+" : Sold one ticket , now left : "+num);
		}
	}
}

class Ticket2 extends Thread {
	private static int num = 100;
	
	private static boolean flag = true;
	
	//Object obj = new Object();
	
	@SuppressWarnings("static-access")
	public Ticket2(boolean flag){
		this.flag = flag;
	}
	
	public void run(){
		while(true){
			if(flag==true){
				if(num>0){
					//synchronized(this){
					//synchronized(obj){
					synchronized(Ticket2.class){
						if(num>0){
							try{Thread.sleep(10);}catch(Exception e){}
					 	    num--;
					    	System.out.println(Thread.currentThread()+" : Sold one ticket , now left : "+num);
					    }
				    }
				}
			}else{
				run2();
			}
		}
	}
	
	public synchronized static void run2(){
		if(num>0){
			try{Thread.sleep(10);}catch(Exception e){}
			num--;
			System.out.println(Thread.currentThread()+" : Sold one ticket , now left : "+num);
		}
	}
}

class LazySingle{
	private static LazySingle s = null;
	
	private LazySingle(){
	
	}
	
	public static LazySingle getInstance(){
		if(s == null)
			synchronized(LazySingle.class){
				if(s == null)
					s = new LazySingle(); 
			}
			
		return s;
	}
}

class LazySingleRun implements Runnable{
	
	public void run(){
		while(true){
			try{Thread.sleep(100);}catch(Exception e){}
			System.out.println(LazySingle.getInstance().hashCode());
		}	
	}
}

class DeathLock extends Thread {
	
	static class MyLock{
		static Object o1 = new Object();
		static Object o2 = new Object();
	}

	private boolean flag=false;
	
	public DeathLock(boolean flag){
		this.flag=flag;
	}
	
	public void run(){
		if(flag){
			do1();
		}else{
			do2();
		}
	}
	
	public void do1(){
		while(true){
			synchronized(MyLock.o1){
				System.out.println("do1 sync o1");
				try{Thread.sleep((int)(Math.random()*(0+100)+0));}catch(Exception e){}
				synchronized(MyLock.o2){
					System.out.println("do1 sync o2");
					try{Thread.sleep((int)(Math.random()*(0+100)+0));}catch(Exception e){}
				}
			}
		}
	}
	
	public void do2(){
		while(true){
			synchronized(MyLock.o2){
				System.out.println("do2 sync o2");
				try{Thread.sleep((int)(Math.random()*(0+100)+0));}catch(Exception e){}
				synchronized(MyLock.o1){
					System.out.println("do2 sync o1");
					try{Thread.sleep((int)(Math.random()*(0+100)+0));}catch(Exception e){}
				}
			}
		}

	}

}

class TimeClass extends Thread{
	private static byte hour=0;
	private static byte minute=0;
	private static byte second=0;
	
	public void run(){
		while(true){
			second++;
			
			if(second==60){
				minute++;
				second=0;
			}
			
			if(minute==60){
				hour++;
				minute=0;
			}
			
			if(hour==12){
				hour=0;
			}
			
			show();
			
		}
	}
	
	public void show(){
		try{Thread.sleep(1000);}catch(Exception e){}
		System.out.println("Time: "+((hour<10)?"0":"")+hour
						  +":"+((minute<10)?"0":"")+minute
						  +":"+((second<10)?"0":"")+second);
	}

}

class ThreadTest {
	public static void main(String[] args){
		
		/*
		Ticket r = new Ticket();
		
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		Thread t3 = new Thread(r);
		t1.start();
		t2.start();
		t3.start();
		/*
		
		
		/*
		Thread t1=new Ticket2(true);
		Thread t2=new Ticket2(false);
		Thread t3=new Ticket2(true);
		((Ticket2)t1).start();
		((Ticket2)t2).start();
		((Ticket2)t3).start();
		*/
		
		/*
		LazySingleRun s = new LazySingleRun();
		
		Thread t1=new Thread(s);
		Thread t2=new Thread(s);
		Thread t3=new Thread(s);
		t1.start();
		t2.start();
		t3.start();
		*/
		
		/*
		DeathLock d1 = new DeathLock(true);
		DeathLock d2 = new DeathLock(false);
		d1.start();
		d2.start();
		*/
		
		TimeClass t = new TimeClass();
		t.start();
		
	}
}
