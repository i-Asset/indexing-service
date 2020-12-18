package at.srfg.iot.indexing.service.playground.impl;

import org.springframework.stereotype.Service;

import at.srfg.iot.common.solr.indexing.core.service.impl.SolrServiceImpl;
import at.srfg.iot.common.solr.model.model.playground.ISensor;
import at.srfg.iot.common.solr.model.model.playground.Sensor;
import at.srfg.iot.indexing.service.playground.SensorService;


@Service
public class SersorServiceImpl extends SolrServiceImpl<Sensor> implements SensorService {

	@Override
	public String getCollection() {
		return ISensor.COLLECTION;
	}

	@Override
	public Class<Sensor> getSolrClass() {
		return Sensor.class;
	}

	

}
