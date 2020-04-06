package at.srfg.iot.indexing.service.event;

import org.springframework.context.ApplicationEvent;

import at.srfg.indexing.asset.ISubmodelAware;

public class RemoveSubmodelAwareEvent extends ApplicationEvent {

	public RemoveSubmodelAwareEvent(Object source, ISubmodelAware submodelAware) {
		super(source);

		this.item = submodelAware;
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
