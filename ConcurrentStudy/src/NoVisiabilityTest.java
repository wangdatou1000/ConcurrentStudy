
public class NoVisiabilityTest extends Thread {

	private   boolean ready;

	private int number;

	public void run() {
		while (!ready) {
			int a=0;
//			number++;
//			//Thread.yield();
			//System.out.println(number++);
		}
		System.out.println(ready);
	}

	public void readyOn() {
			this.ready = true;
	}

	public static void main(String[] args) throws InterruptedException {
		NoVisiabilityTest readThread = new NoVisiabilityTest();
		readThread.start();
		Thread.sleep(2000);
//		for(int i=0;i<20000;i++)
		   System.out.println("====" + readThread.ready);
		readThread.readyOn();
	}
}