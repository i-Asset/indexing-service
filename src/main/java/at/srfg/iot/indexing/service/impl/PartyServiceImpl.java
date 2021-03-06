package at.srfg.iot.indexing.service.impl;

import org.springframework.stereotype.Service;

import at.srfg.indexing.party.PartyType;
import at.srfg.iot.indexing.service.PartyService;

@Service
public class PartyServiceImpl extends SolrServiceImpl<PartyType> implements PartyService {

	@Override
	public String getCollection() {
		return PartyType.COLLECTION;
	}

	@Override
	public Class<PartyType> getSolrClass() {
		return PartyType.class;
	}


}
