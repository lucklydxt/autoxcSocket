package com.zidongxiche.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerHandlerExcutePool {
	 private ExecutorService service = null;
	 public ServerHandlerExcutePool(int maxPoolSize, int queueSize) {
	        service = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize,
	                120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
	 }
	 public void execute(Runnable task) {
	        service.execute(task);	 }
}
