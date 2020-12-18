package at.srfg.iot.indexing.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import at.srfg.iot.common.solr.indexing.core.service.impl.SolrServiceImpl;
import at.srfg.iot.common.solr.model.model.asset.SubmodelType;
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
