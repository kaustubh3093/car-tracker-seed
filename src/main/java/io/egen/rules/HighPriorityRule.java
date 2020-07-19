package io.egen.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "High Priority Rule", description = "Will Return whether the priority is HIGH")
public class HighPriorityRule {

	private String message;
	private String priority;
	
	@Condition
	public boolean when(@Fact("high-priority") int[] rpmValue) {
		
		return rpmValue[0] > rpmValue[1];
	}
	
	@Action
	public void then() throws Exception {
		priority = "HIGH";
		message = "Engine RPM is greater than Red Line RPM";
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
}
