import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPrinter2 implements Runnable {

	private String name;
	private Object prev;
	private Object self;

	private MyThreadPrinter2(String name, Object prev, Object self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	@Override
	public void run() {
		int count = 10;
		while (count > 0) {
			synchronized (prev) {
				synchronized (self) {
					System.out.print(name);
					count--;
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					self.notify();
				}
				try {
					prev.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void main(String[] args) throws Exception {
//		Object a = new Object();
//		Object b = new Object();
//		Object c = new Object();
//		MyThreadPrinter2 pa = new MyThreadPrinter2("A", c, a);
//		MyThreadPrinter2 pb = new MyThreadPrinter2("B", a, b);
//		MyThreadPrinter2 pc = new MyThreadPrinter2("C", b, c);
//
//		new Thread(pa).start();
//		Thread.sleep(10);
//		new Thread(pb).start();
//		Thread.sleep(10);
//		new Thread(pc).start();
//		Thread.sleep(10);

		ReentrantLock lock = new ReentrantLock();
		Condition a = lock.newCondition();
		Condition b = lock.newCondition();
		Condition c = lock.newCondition();
		MyThreadPrinterV2 pa = new MyThreadPrinterV2("A",lock,c, a);
		MyThreadPrinterV2 pb = new MyThreadPrinterV2("B",lock, a, b);
		MyThreadPrinterV2 pc = new MyThreadPrinterV2("C",lock, b, c);
		new Thread(pa).start();
		//Thread.sleep(10);
		new Thread(pb).start();
		//Thread.sleep(10);
		new Thread(pc).start();
		Thread.sleep(10);
	}
}

class MyThreadPrinterV2 implements Runnable {

	private String name;
	private Condition self;
	private Condition prev;
	private ReentrantLock lock;

	public  MyThreadPrinterV2(String name, ReentrantLock lock, Condition prev,
			Condition self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
		this.lock = lock;
	}

	@Override
	public void run() {
		int count = 10;
		while (count > 0) {
			lock.lock();
			try {
				System.out.print(name);
				count--;
				self.signalAll();	
				try {
					if(count>0)prev.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			} finally {
				lock.unlock();
			}
		}
	}
}
