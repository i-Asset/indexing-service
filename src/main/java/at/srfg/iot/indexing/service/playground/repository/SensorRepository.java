package at.srfg.iot.indexing.service.playground.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import at.srfg.indexing.model.playground.Sensor;

@Repository
public interface SensorRepository  extends SolrCrudRepository<Sensor, String>{
	List<Sensor> findBySensorAware(String platform);
	/**
	 * Remove all classes of the provided namespace
	 * @param namespace
	 * @return
	 */
	long deleteByNameSpace(String namespace);
	/**
	 * Delete all classes of the provided namespaces
	 * @param namespaces
	 * @return
	 */
	long deleteByNameSpaceIn(Set<String> namespaces);
	/**
	 * Retrieve all classes based on their id (uri)
	 * @param uri
	 * @return
	 */
	List<Sensor> findByUriIn(Set<String> uri);
	/**
	 * 
	 * @param namespace
	 * @param localNames
	 * @return
	 */
	List<Sensor> findByNameSpaceAndLocalNameIn(String namespace, Set<String> localNames);
}


