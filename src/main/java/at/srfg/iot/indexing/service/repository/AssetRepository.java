package at.srfg.iot.indexing.service.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import at.srfg.iot.common.solr.model.model.asset.AssetType;

@Repository
public interface AssetRepository  extends SolrCrudRepository<AssetType, String>{
	Optional<AssetType> findBySubmodels(String submodelId);
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
	List<AssetType> findByUriIn(Set<String> uri);
	/**
	 * 
	 * @param namespace
	 * @param localNames
	 * @return
	 */
	List<AssetType> findByNameSpaceAndLocalNameIn(String namespace, Set<String> localNames);
}


