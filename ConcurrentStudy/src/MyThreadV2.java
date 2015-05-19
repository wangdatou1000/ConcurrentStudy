import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class MyThreadV2 extends Thread {

	private volatile boolean suspend = false;
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void setSuspend(boolean suspend) {
		if (!suspend) {
			lock.lock();
			try {
				condition.signalAll();
			} finally {
				lock.unlock();
			}
		}
		this.suspend = suspend;
	}

	public boolean isSuspend() {
		return this.suspend;
	}

	public void run() {
		while (true) {
			lock.lock();
			try {
				if (suspend) {
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} finally {
				lock.unlock();
			}
			this.runLogic();
		}	
	}

	protected abstract void runLogic();

	public static void main(String[] args) throws Exception {
		MyThreadV2 myThread = new MyThreadV2() {
			protected void runLogic() {
				System.out.println("myThread is runing");
			}
		};
		myThread.start();
		Thread.sleep(200);
		myThread.setSuspend(true);
		System.out.println("myThread has stopped");
		Thread.sleep(3000);
		myThread.setSuspend(false);

	}
}