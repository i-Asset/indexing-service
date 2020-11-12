package at.srfg.iot.indexing.service.playground.repository;

import java.util.Set;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import at.srfg.indexing.model.playground.SensorPlatform;

@Repository
public interface SensorPlatformRepository extends SolrCrudRepository<SensorPlatform, String>{
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
}
