package at.srfg.iot.indexing.service.repository;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import at.srfg.indexing.model.asset.SubmodelType;

@Repository
public interface SubmodelRepository extends SolrCrudRepository<SubmodelType, String>{
	List<SubmodelType> findByAsset(String asset);
}
