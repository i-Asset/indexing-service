package at.srfg.iot.indexing.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import at.srfg.indexing.core.service.impl.SolrServiceImpl;
import at.srfg.indexing.model.party.PartyType;
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

	@Override
	public long deleteNameSpace(String nameSpace) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteNameSpaces(Set<String> nameSpace) {
		// TODO Auto-generated method stub
		return 0;
	}


}
