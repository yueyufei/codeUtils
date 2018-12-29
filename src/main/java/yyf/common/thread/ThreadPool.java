package yyf.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
	/**
	 * 固定大小的线程池，可以指定线程池的大小，该线程池corePoolSize和maximumPoolSize相等，阻塞队列使用的是LinkedBlockingQueue，大小为整数最大值。
	 * 该线程池中的线程数量始终不变，当有新任务提交时，线程池中有空闲线程则会立即执行，如果没有，则会暂存到阻塞队列。对于固定大小的线程池，不存在线程数量的变化。同时使用无界的LinkedBlockingQueue来存放执行的任务。当任务提交十分频繁的时候，LinkedBlockingQueue
	 * 迅速增大，存在着耗尽系统资源的问题。而且在线程池空闲时，即线程池中没有可运行任务时，它也不会释放工作线程，还会占用一定的系统资源，需要shutdown。
	 * 
	 * @param threadNum
	 * @return
	 */
	public static ExecutorService createnewFixedThreadPool(Integer threadNum) {
		ExecutorService threadPool = new ThreadPoolExecutor(threadNum, threadNum, 0l, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>());
		// ExecutorService threadPool = Executors.newFixedThreadPool(threadNum);
		return threadPool;
	}

	public static ExecutorService createnewFixedThreadPool(int threadNum, ThreadFactory factory) {
		// Executors.newFixedThreadPool(threadNum, factory);
		return new ThreadPoolExecutor(threadNum, threadNum, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
				factory);
	}

	/**
	 * 单个线程线程池，只有一个线程的线程池，阻塞队列使用的是LinkedBlockingQueue,若有多余的任务提交到线程池中，则会被暂存到阻塞队列，待空闲时再去执行。按照先入先出的顺序执行任务。
	 * 
	 * @return
	 */
	public static ExecutorService createcreatenewSingleThreadExecutor() {
		// Executors.newSingleThreadExecutor();
		return new ThreadPoolExecutor(1, 1, 0l, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
	}

	public static ExecutorService createnewSingleThreadExecutor(ThreadFactory factory) {
		// Executors.newSingleThreadExecutor(factory);
		return new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), factory);
	}

	/**
	 * 缓存线程池，缓存的线程默认存活60秒。线程的核心池corePoolSize大小为0，核心池最大为Integer.MAX_VALUE,阻塞队列使用的是SynchronousQueue。是一个直接提交的阻塞队列，
	 * 他总会迫使线程池增加新的线程去执行新的任务。在没有任务执行时，当线程的空闲时间超过keepAliveTime（60秒），则工作线程将会终止被回收，当提交新任务时，如果没有空闲线程，则创建新线程执行任务，会导致一定的系统开销。如果同时又大量任务被提交，而且任务执行的时间不是特别快，那么线程池便会新增出等量的线程池处理任务，这很可能会很快耗尽系统的资源。
	 * 
	 * @return
	 */
	public static ExecutorService createnewCachedThreadExecutor() {
		// Executors.newCachedThreadPool();
		return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<>());
	}

	public static ExecutorService createnewCachedThreadExecutor(ThreadFactory factory) {
		// Executors.newCachedThreadPool(factory);
		return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<>(), factory);
	}

	/**
	 * 定时线程池，该线程池可用于周期性地去执行任务，通常用于周期性的同步数据。
	 * scheduleAtFixedRate:是以固定的频率去执行任务，周期是指每次执行任务成功执行之间的间隔。
	 * schedultWithFixedDelay:是以固定的延时去执行任务，延时是指上一次执行成功之后和下一次开始执行的之前的时间
	 * 
	 * @param corePoolSize
	 * @return
	 */
	public static ExecutorService createnewScheduledThreadPool(Integer corePoolSize) {
		// Executors.newScheduledThreadPool(corePoolSize);
		return new ScheduledThreadPoolExecutor(corePoolSize);
	}

	public static ExecutorService createnewScheduledThreadPool(Integer corePoolSize, ThreadFactory factory) {
		// Executors.newScheduledThreadPool(corePoolSize, factory);
		return new ScheduledThreadPoolExecutor(corePoolSize, factory);
	}

}
