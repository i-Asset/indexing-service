package at.srfg.iot.indexing.service.playground.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.srfg.iot.common.solr.indexing.core.service.impl.SolrServiceImpl;
import at.srfg.iot.common.solr.model.model.playground.ISensorPlatform;
import at.srfg.iot.common.solr.model.model.playground.Sensor;
import at.srfg.iot.common.solr.model.model.playground.SensorPlatform;
import at.srfg.iot.indexing.service.playground.SensorPlatformService;
import at.srfg.iot.indexing.service.playground.event.RemoveSensorAwareEvent;
import at.srfg.iot.indexing.service.playground.event.SensorAwareEvent;
import at.srfg.iot.indexing.service.playground.repository.SensorRepository;

@Service
public class SensorPlatformServiceImpl extends SolrServiceImpl<SensorPlatform> implements SensorPlatformService {
	@Autowired
	private SensorRepository sensorRepo;
	
	@Override
	public String getCollection() {
		return ISensorPlatform.COLLECTION;
	}

	@Override
	public Class<SensorPlatform> getSolrClass() {
		return SensorPlatform.class;
	}

	@Override
	protected void prePersist(SensorPlatform t) {
		// send event
		super.prePersist(t);
		getEventPublisher().publishEvent(new SensorAwareEvent(this, t));
	}
	/**
	 * On single select - read the associated sensors and load 
	 * with the asset
	 */
	protected void postSelect(SensorPlatform t) {
		super.postSelect(t);
		// read the contained sensors
		List<Sensor> subList = sensorRepo.findBySensorAware(t.getUri());
		for (Sensor sub : subList) {
			t.addSensor(sub);
		}
	}
	protected void postRemove(SensorPlatform t) {
		super.postRemove(t);
		// send event
		getEventPublisher().publishEvent(new RemoveSensorAwareEvent(this, t));
	}

}
