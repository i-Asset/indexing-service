package at.srfg.iot.indexing.service.playground.event;

import org.springframework.context.ApplicationEvent;

import at.srfg.indexing.model.playground.ISensorAware;

public class RemoveSensorAwareEvent extends ApplicationEvent {

	public RemoveSensorAwareEvent(Object source, ISensorAware sensorAware) {
		super(source);

		this.item = sensorAware;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ISensorAware item;
	
	public ISensorAware getItem() {
		return item;
	}
	
	

}
