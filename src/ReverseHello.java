/**
 * @author Josfalme
 */
public class ReverseHello extends Thread {

	/** Maximum number of threads */
	static final int MAX_THREADS = 50;
	/** First thread index */
	static final int FIRST_THREAD = 1;
	
	/** Thread counter */
	private int counter;
	
    public ReverseHello(int pCounter) {
    	this.counter = pCounter;
    }
    
    public static void main(String[] args) throws InterruptedException {
		
    	ReverseHello firstThread = new ReverseHello(FIRST_THREAD);
		firstThread.start();
		
	}
    
    @Override
    public void run() {	
    	
    	try{
    		if(counter <= MAX_THREADS){
    			
    			startNewThread(counter++);
    			
    		}
    	}catch(Exception ex){
    		System.out.println(ex.toString());
    	}
    	
    }
    
    /**
     * 
     * @param pCounter
     * @throws InterruptedException
     */
    private void startNewThread(int pCounter) throws InterruptedException{
    	ReverseHello reverseHelloThread = new ReverseHello(counter);
		reverseHelloThread.start();
		reverseHelloThread.join();
		System.out.println(String.format("Hello %s", getName()));
    }
}
