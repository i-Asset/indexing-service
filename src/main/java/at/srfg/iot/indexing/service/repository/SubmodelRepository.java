package at.srfg.iot.indexing.service.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import at.srfg.iot.common.solr.model.model.asset.SubmodelType;

@Repository
public interface SubmodelRepository extends SolrCrudRepository<SubmodelType, String>{
	List<SubmodelType> findByAsset(String asset);
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
