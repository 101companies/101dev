package sumwordlength.parallel;
/* 
 * Inspired by the following blog post:
 * http://blogs.sun.com/CoreJavaTechTips/entry/get_netbeans_6
 * 
 * This is a good example for using functors (see Callable).
 * At the same time, it peeks at the use of threads, thread pools, and futures.
 * So to say, this is a "hello world" of parallel computing.
 * 
 */

import java.util.*;
import java.util.concurrent.*;

/*
 * Compute the length of an array of words
 */
public class Program {

	/*
	 * Test input
	 */
	private final static String words[] = {"let", "us", "sum", "up", "the", "length", "of", "all", "these", "words"};

	/*
	 * A parallel implementation using a thread pool
	 */
	private static int SumOfLengths()
		throws
			InterruptedException,
			ExecutionException 
	{

		/*
		 * We will use a thread pool to compute the length of words.
		 */
		ExecutorService pool = Executors.newFixedThreadPool(5);
	  	  
		/* 
		 * Create one Callable per word in the input array.
		 * Submit each Callable to the thread pool.
		 * Each submission returns a Future.
		 * The result of the call can be retrieved later from the Future.
		 * Hence, we store all Futures until somewhat later.
		 * So the idea is that the threads will progress in the meantime.
		 */
		Set<Future<Integer>> set = new HashSet<Future<Integer>>();
		for (final String word: words) {
			Callable<Integer> callable = new Callable<Integer>() {
		    	public Integer call() {
		    		return Integer.valueOf(word.length());
		    	}				
			};
			Future<Integer> future = pool.submit(callable);
			set.add(future);
		}
		
		/*  
		 * Go over Futures to sum up the results.
		 * The get method will block until the result is available.
		 */
		int sum = 0;
		for (Future<Integer> future : set) {
			sum += future.get();
		}
		
		// Done with this thread pool
		pool.shutdown();
		
		return sum;
	}
	
	/*
	 * Test the computation
	 */
	public static void main(String args[]) throws Exception {
		System.out.printf("The sum of lengths is %s.%n", SumOfLengths());
  	}
}
