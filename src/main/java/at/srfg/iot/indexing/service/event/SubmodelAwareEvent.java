package at.srfg.iot.indexing.service.event;

import org.springframework.context.ApplicationEvent;

import at.srfg.indexing.asset.ISubmodelAware;

public class SubmodelAwareEvent extends ApplicationEvent {

	public SubmodelAwareEvent(Object source, ISubmodelAware custom) {
		super(source);

		this.item = custom;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ISubmodelAware item;
	
	public ISubmodelAware getItem() {
		return item;
	}
	
	

}
