package at.srfg.iot.indexing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.srfg.indexing.asset.AssetType;
import at.srfg.indexing.asset.SubmodelType;
import at.srfg.iot.indexing.service.AssetService;
import at.srfg.iot.indexing.service.event.SubmodelAwareEvent;
import at.srfg.iot.indexing.service.repository.SubmodelRepository;

@Service
public class AssetServiceImpl extends SolrServiceImpl<AssetType> implements AssetService{
	@Autowired
	private SubmodelRepository submodelRepo;
	
	@Override
	public String getCollection() {
		return AssetType.COLLECTION;
	}

	@Override
	public Class<AssetType> getSolrClass() {
		return AssetType.class;
	}
	/**
	 * Persist submodels contained in the assettype
	 */
	@Override
	protected void prePersist(AssetType t) {
		// send event
		getEventPublisher().publishEvent(new SubmodelAwareEvent(this, t));
	}
	/**
	 * On single select - read the associated submodels and load 
	 * with the asset
	 */
	protected void postSelect(AssetType t) {
		super.postSelect(t);
		// read the contained submodels
		List<SubmodelType> subList = submodelRepo.findByAsset(t.getUri());
		for (SubmodelType sub : subList) {
			t.addSubmodel(sub);
		}
	}
}
