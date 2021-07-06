package medios.cmmn.util;

import java.util.concurrent.Future;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class ExecutorUtil {
	private static ThreadPoolTaskExecutor threadPoolTaskExecutor = null;

	private static synchronized ThreadPoolTaskExecutor getExecutor() {
		if(threadPoolTaskExecutor == null) {
			WebApplicationContext context	 = ContextLoader.getCurrentWebApplicationContext();
			threadPoolTaskExecutor  		= (ThreadPoolTaskExecutor) context.getBean("threadPoolTaskExecutor");
		}
		
		return threadPoolTaskExecutor;
	}
	
	private static int sleepTime = 500;
	private static void checkThreadPoolExcutor(int timeout, int threadCount) {
		int sleepedTime = 0;
		while(!(checkThreadPool(threadCount) || (timeout != 0 && sleepedTime > timeout))) {
			sleepedTime += sleepTime;
			try { Thread.sleep(sleepTime); } catch(Exception e) {}
		}
	}
	
	private static int threadCount = 1;
	private static boolean checkThreadPool(int threadCount) {
		return getExecutor().getCorePoolSize() >= getExecutor().getActiveCount() + (threadCount>0?threadCount:ExecutorUtil.threadCount);
	}
	
	public static Future<?> submit(Runnable task) {
		return submit(0, task);
	}
	
	public static Future<?> submit(int timeout, Runnable task) {
		checkThreadPoolExcutor(timeout, threadCount);
		Future<?> future = getExecutor().submit(task);
		if(task instanceof ExcutorRunnable) {
			((ExcutorRunnable) task).future = future;
		}
		return future;
	}
	
	public static Runnable makeRunnable(Runnable runnable) {
		return new ExcutorRunnable(runnable);
	}
	
	private static class ExcutorRunnable implements Runnable {
		
		private Future<?> future;
		private Runnable runnable;

		public ExcutorRunnable(Runnable runnable) {
			this.runnable = runnable;
		}

		@Override
		public void run() {
			try {
				if(runnable != null) runnable.run();
			} catch (Exception e) {
				if(future != null ) future.cancel(true);
			}
		}
		
	}
}
