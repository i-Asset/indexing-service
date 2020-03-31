package at.srfg.iot.indexing.service.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import at.srfg.indexing.asset.AssetType;

@Repository
public interface AssetRepository extends SolrCrudRepository<AssetType, String>{
	
}
