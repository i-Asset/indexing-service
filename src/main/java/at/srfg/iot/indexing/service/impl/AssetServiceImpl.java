package at.srfg.iot.indexing.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.srfg.indexing.core.service.impl.SolrServiceImpl;
import at.srfg.indexing.model.asset.AssetType;
import at.srfg.indexing.model.asset.SubmodelType;
import at.srfg.iot.indexing.service.AssetService;
import at.srfg.iot.indexing.service.event.RemoveSubmodelAwareEvent;
import at.srfg.iot.indexing.service.event.SubmodelAwareEvent;
import at.srfg.iot.indexing.service.repository.AssetRepository;
import at.srfg.iot.indexing.service.repository.SubmodelRepository;

@Service
public class AssetServiceImpl extends SolrServiceImpl<AssetType> implements AssetService {
	@Autowired
	AssetRepository assetRepo;
	
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
	/**
	 * On remove - also remove all submodels of the asset 
	 */
	@Override
	protected void postRemove(AssetType t) {
		super.postRemove(t);
		// send remove event
		getEventPublisher().publishEvent(new RemoveSubmodelAwareEvent(this, t));
	}

	@Override
	public long deleteNameSpace(String nameSpace) {
		long deleted = submodelRepo.deleteByNameSpace(nameSpace);
		deleted += assetRepo.deleteByNameSpace(nameSpace); 
		return deleted;
	}

	@Override
	public long deleteNameSpaces(Set<String> nameSpace) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
