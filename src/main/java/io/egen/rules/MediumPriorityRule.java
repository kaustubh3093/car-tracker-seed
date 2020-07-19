package io.egen.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "Medium Priority Rule", description = "Will Return whether the priority is MEDIUM")
public class MediumPriorityRule {

	private String message;
	private String priority;
	
	@Condition
	public boolean when(@Fact("medium-priority") float[] fuelValue) {
		
		return fuelValue[0] < 0.1 * fuelValue[1];
	}
	
	@Action
	public void then() throws Exception {
		priority = "MEDIUM";
		message = "Fuel Volume is less than 10 percent of max fuel volume";
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
