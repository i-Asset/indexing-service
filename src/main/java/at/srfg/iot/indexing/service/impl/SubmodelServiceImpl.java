package at.srfg.iot.indexing.service.impl;

import org.springframework.stereotype.Service;

import at.srfg.indexing.core.service.impl.SolrServiceImpl;
import at.srfg.indexing.model.asset.SubmodelType;
import at.srfg.iot.indexing.service.SubmodelService;

@Service
public class SubmodelServiceImpl extends SolrServiceImpl<SubmodelType> implements SubmodelService {

	@Override
	public String getCollection() {
		return SubmodelType.COLLECTION;
	}

	@Override
	public Class<SubmodelType> getSolrClass() {
		return SubmodelType.class;
	}


}
