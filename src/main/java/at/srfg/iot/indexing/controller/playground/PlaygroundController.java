package at.srfg.iot.indexing.controller.playground;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.srfg.iot.common.datamodel.indexing.playground.ISensorIndexingAPI;
import at.srfg.iot.common.datamodel.indexing.playground.ISensorPlatformIndexingAPI;
import at.srfg.iot.common.solr.indexing.core.controller.BasicIndexingAPI;
import at.srfg.iot.common.solr.model.model.playground.Sensor;
import at.srfg.iot.common.solr.model.model.playground.SensorPlatform;
import at.srfg.iot.common.solr.model.model.solr.FacetResult;
import at.srfg.iot.common.solr.model.model.solr.IndexField;
import at.srfg.iot.common.solr.model.model.solr.Search;
import at.srfg.iot.common.solr.model.model.solr.SearchResult;
import at.srfg.iot.indexing.service.playground.SensorPlatformService;
import at.srfg.iot.indexing.service.playground.SensorService;

@RestController
@RequestMapping(path="hma")
public class PlaygroundController extends BasicIndexingAPI implements ISensorIndexingAPI, ISensorPlatformIndexingAPI {
	@Autowired
	private SensorPlatformService sensorPlatform;
	
	@Autowired
	private SensorService sensor;
	
	@Override
	public Optional<SensorPlatform> getSensorPlatform(String uri) throws Exception {
		return sensorPlatform.get(uri);
	}

	@Override
	public Collection<IndexField> fieldsForSensorPlatform(Set<String> fieldNames) throws Exception {
		return sensorPlatform.fields(fieldNames);
	}

	@Override
	public SearchResult<SensorPlatform> searchForSensorPlatform(String query, List<String> filterQuery,
			List<String> facetFields, int facetLimit, int facetMinCount, int start, int rows) throws Exception {
		return sensorPlatform.select(query, filterQuery, facetFields, facetLimit, facetMinCount, new SolrPageRequest(start, rows));
	}

	@Override
	public SearchResult<SensorPlatform> searchForSensorPlatform(Search search) throws Exception {
		return sensorPlatform.search(search);
	}

	@Override
	public FacetResult suggestForSensorPlatform(String query, String fieldName, int limit, int minCount)
			throws Exception {
		return sensorPlatform.suggest(query, fieldName, limit, minCount);
	}

	@Override
	public SensorPlatform setSensorPlatform(SensorPlatform prop) throws Exception {
		sensorPlatform.set(prop);
		return prop;
	}

	@Override
	public boolean deleteSensorPlatform(String uri) throws Exception {
		sensorPlatform.remove(uri);
		return true;
	}

	@Override
	public Optional<Sensor> getSensor(String uri) throws Exception {
		return sensor.get(uri);
	}

	@Override
	public Collection<IndexField> fieldsForSensor(Set<String> fieldNames) throws Exception {
		return sensor.fields(fieldNames);
	}

	@Override
	public SearchResult<Sensor> searchForSensor(String query, List<String> filterQuery, List<String> facetFields,
			int facetLimit, int facetMinCount, int start, int rows) throws Exception {
		return sensor.select(query, filterQuery, facetFields, facetLimit, facetMinCount, new SolrPageRequest(start, rows));
	}

	@Override
	public SearchResult<Sensor> searchForSensor(Search search) throws Exception {
		return sensor.search(search);
	}

	@Override
	public FacetResult suggestForSensor(String query, String fieldName, int limit, int minCount) throws Exception {
		return sensor.suggest(query, fieldName, limit, minCount);
	}

	@Override
	public Sensor setSensor(Sensor prop) throws Exception {
		sensor.set(prop);
		return prop;
	}

	@Override
	public boolean deleteSensor(String uri) throws Exception {
		sensor.remove(uri);
		return true;
	}

}
