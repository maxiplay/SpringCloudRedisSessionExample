package com.springcloud.sessionexample.common.service;

import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import net.bull.javamelody.MonitoredWithSpring;

@MonitoredWithSpring
@Service
public class FuturService {

	/**
	 * Wait until they are all done
	 * 
	 * @param futures
	 * @throws InterruptedException
	 */
	public void waitFuturesDone(Future<?>... futures) throws InterruptedException {

		boolean isDone = false;
		while (!isDone) {
			Thread.sleep(10);
			isDone = true;
			for (Future<?> future : futures) {
				if (future != null) {
					if (!future.isDone()) {
						isDone = false;
						break;
					}
				}
			}
		}

	}
}
