import java.util.Random;

/**
 * @author Josfalme
 */
public class MaxValue extends Thread {
	
	/** Maximum number of threads */
	static final int MAX_THREADS = 4;
	/** Array length */
	static final int ARR_LENGTH = 4;
	/** Maximum possible random number */
	static final int MAX_RANDOM_NUMBER = 200;
	
	/** Lower index */
	private int low;
	/** Higher index */
	private int high;
	/** Array of values */
	private int[] values;
	/** Maximum value result */
	private int maxValue = 0;
	
    public MaxValue(int[] pValues, int pLow, int pHigh) {
       
    	this.low = pLow;
        this.high = pHigh;
        this.values = pValues;
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        
		int[] arr = new int[ARR_LENGTH];
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getRandomNumber();
        }
        
        int maxNumberInArray = getMaxValue(arr);
        
        System.out.println(String.format("The max value is: %1$d", maxNumberInArray));
 
	}
    
    @Override
    public void run() {		
    	
    	try {
    		maxValue = getMaxValue(values, low, high);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
    	
    }

    /**
     * Gets the maximum value from a given array
     * @param pArray Array of values
     * @return The maximum value
     * @throws InterruptedException
     */
	private static int getMaxValue(int[] pArray) throws InterruptedException {
		
		int maxValue = 0;
		int lenght = pArray.length;
		
		MaxValue[] maxValueThreadArray = new MaxValue[MAX_THREADS];
		int [] finalValues = new int [MAX_THREADS];

        for (int _i = 0; _i < MAX_THREADS; _i++) {
        	
    		int lowValue = (_i * lenght) / MAX_THREADS;
    		int highValue = ((_i + 1) * lenght / MAX_THREADS);
    		
        	maxValueThreadArray[_i] = new MaxValue(pArray, lowValue, highValue);
        	maxValueThreadArray[_i].start();
        }
        
        for (int _i = 0; _i < MAX_THREADS; _i++) {
        	maxValueThreadArray[_i].join();
        	finalValues[_i] = maxValueThreadArray[_i].maxValue;
        }
        
        maxValue = getMaxValue(finalValues,0,MAX_THREADS);
        
        return maxValue;
	}
	
	/**
	 * Gets the maximum value from a given array and range
	 * @param pValues Array of values
	 * @param pLow Lower index
	 * @param pHigh Higher index
	 * @return The maximum value
	 * @throws InterruptedException
	 */
	private static int getMaxValue(int[] pValues,int pLow, int pHigh) throws InterruptedException {
		
		int maxValue = 0;
		
		for (int _i = pLow; _i < pHigh; _i++)
    	{
    		 if (pValues[_i] > maxValue)
    	     {
    			 maxValue = pValues[_i];
    	     }
    	}
		
		return maxValue;
		
	}
	
	/**
	 * Gets a random number 
	 * @return the random number
	 */
	private static int getRandomNumber(){
		
		Random random = new Random(); 
		return random.nextInt(MAX_RANDOM_NUMBER);
		
	}
}
