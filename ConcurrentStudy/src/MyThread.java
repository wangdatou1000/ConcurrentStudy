public abstract class MyThread extends Thread {

	private volatile boolean suspend = false;
    private byte[] control = new byte[0]; // 只是需要一个对象而已，这个对象没有实际意义
    
	public void setSuspend(boolean suspend) {
		if (!suspend) {
			synchronized (control) {
				control.notifyAll();	
			}	
		}
		this.suspend = suspend;
	}

	public boolean isSuspend() {
		return this.suspend;
	}

	public void run() {
		while (true) {
			synchronized (control) {
				if (suspend) {
					try {
						control.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
			this.runPersonelLogic();	
		}
	}

	protected abstract void runPersonelLogic();
	
	public static void main(String[] args) throws Exception {
		MyThread myThread = new MyThread() {
			protected void runPersonelLogic() {
			}
		};
		myThread.start();
		
		Thread.sleep(200);
		myThread.setSuspend(true);
		System.out.println("\nmyThread1 has stopped");
		Thread.sleep(30000);
		//myThread.setSuspend(false);

		
	}
}