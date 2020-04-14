package at.srfg.iot.indexing.service.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import at.srfg.indexing.model.party.PartyType;

@Repository
public interface PartyRepository extends SolrCrudRepository<PartyType, String>{
	
}
