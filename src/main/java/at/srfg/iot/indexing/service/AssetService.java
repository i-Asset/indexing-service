package at.srfg.iot.indexing.service;

import java.util.Set;

import at.srfg.iot.common.solr.indexing.core.service.SolrService;
import at.srfg.iot.common.solr.model.model.asset.AssetType;

public interface AssetService extends SolrService<AssetType> {
	/**
	 * Delete all documents of the provided nameSpace
	 * @param nameSpace
	 */
	public long deleteNameSpace(String nameSpace);
	/**
	 * Delete all documents of the provided nameSpaces
	 * @param nameSpace List of nameSpaces to remove
	 */
	public long deleteNameSpaces(Set<String> nameSpace);
}
