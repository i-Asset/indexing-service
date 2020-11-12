package at.srfg.iot.indexing.service.playground.event;

import org.springframework.context.ApplicationEvent;

import at.srfg.indexing.model.playground.ISensorAware;

public class SensorAwareEvent extends ApplicationEvent {

	public SensorAwareEvent(Object source, ISensorAware custom) {
		super(source);

		this.item = custom;
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
