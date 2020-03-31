package at.srfg.iot.indexing.service.impl;

import org.springframework.stereotype.Service;

import at.srfg.indexing.asset.AssetType;
import at.srfg.iot.indexing.service.AssetService;
import at.srfg.iot.indexing.service.impl.SolrServiceImpl;

@Service
public class AssetServiceImpl extends SolrServiceImpl<AssetType> implements AssetService{
	@Override
	public String getCollection() {
		return AssetType.COLLECTION;
	}

	@Override
	public Class<AssetType> getSolrClass() {
		return AssetType.class;
	}

}
