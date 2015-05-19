import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信例子(基于可重入锁ReentrantLock，一对Condition实现)
 * */
public class PC2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Q2 q = new Q2();
		new Customer2(q);
		new Producer2(q);
		

	}
}

/**
 * 阻塞队列
 * */
class Q2 {
	int n;
	final Lock lock = new ReentrantLock();
	final Condition canGet = lock.newCondition();
	final Condition canPut = lock.newCondition();
	boolean valueSet = false;

	int get() {
		// 实现可响应中断的锁
		lock.lock();
		try {

			// 值未设置好，循环等待
			while (!valueSet) {
				canGet.await();
			}
			// 取值成功
			System.out.println("Got：" + n);
			// 将设置标志置为false
			valueSet = false;
			// Thread.currentThread().sleep(1000);
			// 通知设值线程
			canPut.signal();
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			lock.unlock();
		}
		return n;
	}

	void put(int n) {
		lock.lock();
		try {
			// 实现可响应中断的锁
			// lock.lockInterruptibly();
			// 已经设置好了值，而且未被取走，循环等待
			while (valueSet) {
				canPut.await();
			}
			this.n = n;
			// 将设置标志设置为true
			valueSet = true;
			System.out.println("Put：" + n);
			// Thread.currentThread().sleep(1000);
			// 通知取值线程，可以取值
			canGet.signal();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

/**
 * 生产者
 * */
class Producer2 implements Runnable {
	Q2 q;

	public Producer2(Q2 q) {
		this.q = q;
		new Thread(this, "Producer").start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while (true) {
			q.put(i++);
		}
	}
}

/**
 * 消费者
 * */
class Customer2 implements Runnable {
	Q2 q;

	public Customer2(Q2 q) {
		this.q = q;
		new Thread(this, "Customer").start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			q.get();
		}
	}
}
