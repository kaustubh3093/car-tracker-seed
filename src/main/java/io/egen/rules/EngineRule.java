package io.egen.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "Engine Rule", description = "Will Return whether the priority is LOW")
public class EngineRule {

	private String message;
	private String priority;
	
	@Condition
	public boolean when(@Fact("low-priority-engine") boolean[] engineReading) {
		
		return engineReading[0] == true || engineReading[1] == true;
	}
	
	@Action
	public void then() throws Exception {
		priority = "LOW";
		message = "Please check: Either enginer coolant is low or engine light is ON";
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
