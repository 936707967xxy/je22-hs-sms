package com.hoomsun.util.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolManager {
	
volatile int count=0;
	public void runTemp(){
		ExecutorService pool=Executors.newFixedThreadPool(3);
		try {
			for (int i = 0; i <6; i++) {
				Future<Integer> task = pool.submit(new Callable<Integer>() {
	                public Integer call() throws Exception {
	                	System.out.println("id " + Thread.currentThread().getName() +"---start>"+count); 
						  count++;
						  try {
							  if(count==5){
									Integer sa=Integer.parseInt("aaaaa");
								}
							} catch (Exception e) {
								// TODO: handle exception
								return -1;
							}
	                    return 10;  
	                }
	            });
				
				if(task.get()==-1){
					throw new RuntimeException("回滚0");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("===========================");
			throw new RuntimeException("回滚1");
		}
	}
	
	public static void main(String[] args) {
		ThreadPoolManager ma=new ThreadPoolManager();
		ma.runTemp();
	}
}
