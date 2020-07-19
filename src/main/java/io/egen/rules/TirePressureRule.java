package io.egen.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "Tire Pressure Rule", description = "Will Return whether the priority is LOW")
public class TirePressureRule {

	private String message;
	private String priority;
	
	@Condition
	public boolean when(@Fact("low-priority-tire") int[] tireReading) {
		
		
		return (tireReading[0] < 32 || tireReading[0] > 36) || (tireReading[1] < 32 || tireReading[1] > 36)
				|| (tireReading[2] < 32 || tireReading[2] > 36)
					|| (tireReading[3] < 32 || tireReading[3] > 36);
	}
	
	@Action
	public void then() throws Exception {
		priority = "LOW";
		message = "Tire pressure is not within the set standard limit";
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
