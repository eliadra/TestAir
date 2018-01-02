package com.epam.automation.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Provides functionality to wait for desired condition specified amount of time
 */
public class Waiter {
	
	private int waitTimeout = 30;
	private int attemptsIntervalSeconds = 5;
	private int attemptsIntervalMillis = 0;
	private Map<String, Class<? extends Throwable>> exceptionsToIgnore = new HashMap<>();
	private boolean isNeedToRefreshPage = false;
	
	/**
	 * Default constructor
	 */
	public Waiter() {}
	
	/**
	 * Constructor
	 * @param timeout - number of seconds to wait for condition
	 * @param attemptsInterval
	 * @param toIgnore
	 */
	@SafeVarargs
	public Waiter(int timeout, int attemptsInterval, Class<? extends Throwable>... toIgnore) {
		this(timeout, attemptsInterval, false, toIgnore);
	}

	@SafeVarargs
	public Waiter(int timeout, double attemptsInterval, Class<? extends Throwable>... toIgnore) {
		this(timeout, (int) attemptsInterval, false, toIgnore);
		attemptsIntervalMillis = (int)((attemptsInterval - (int) attemptsInterval) * 1000); // на дробный части
	}

	/**
	 * Constructor
	 * @param timeout - number of seconds to wait for condition
	 * @param attemptsInterval
	 * @param toIgnore
	 */
	@SafeVarargs
	public Waiter(int timeout, int attemptsInterval, boolean isNeedToRefresh, Class<? extends Throwable>... toIgnore) {
		this.attemptsIntervalSeconds = attemptsInterval;
		this.exceptionsToIgnore = Arrays.asList(toIgnore).stream().collect(Collectors.toMap(current -> current.getClass().getName(), current -> current));
		this.waitTimeout = timeout;
		this.isNeedToRefreshPage = isNeedToRefresh;
	}
	
	/**
	 * Constructor
	 * @param toIgnore - exceptions that will be ignored if occurred during wait
	 */
	@SafeVarargs
	public Waiter(Class<? extends Throwable>... toIgnore) {
		this.exceptionsToIgnore = Arrays.asList(toIgnore).stream().collect(Collectors.toMap(current -> current.getClass().getName(), current -> current));
	}
	
	/**
	 * Start waiting
	 * @param waitCondition - supplier with condition to wait, should return true when desired state is reached
	 */
	public final void start(Supplier<Boolean> waitCondition) {
		beforeWait();
		long startTime = System.currentTimeMillis();
		try {
			while (true) {
				try {
					if (startTime + TimeUnit.SECONDS.toMillis(waitTimeout) < System.currentTimeMillis()) {
						throw new WaitTimeoutException("Timed out after " + waitTimeout + " seconds waiting for condition to be true.");
					}
					if (waitCondition.get()) {
						return;
					}
					Thread.sleep(TimeUnit.SECONDS.toMillis(attemptsIntervalSeconds) + attemptsIntervalMillis);
					} catch (Exception e) {
					if (!exceptionsToIgnore.containsKey(e.getClass())) {
						throw new WaiterException(e);
					} else {
						System.out.println("Exception " + e.getMessage() + " occurred while waiting, ignore it");
					}
				}
			}
		} finally {
			afterWait();
		}
	}
	
	/**
	 * Method called before wait starts. Does nothing by default.
	 */
	protected void beforeWait() {}
	
	/**
	 * Method called after wait ends regardless of exceptions occurred while waiting. Does nothing by default.
	 */
	protected void afterWait() {}
	
	public static class WaitTimeoutException extends RuntimeException {
		
		private static final long serialVersionUID = 475940617997985138L;
		
		public WaitTimeoutException(String message) {
			super(message);
		}
	}
	
	public static class WaiterException extends RuntimeException {
		
		private static final long serialVersionUID = -220397906063458529L;
		
		public WaiterException(Throwable cause) {
			super(cause);
		}
	}
}
