package at.srfg.iot.indexing.service.playground;

import java.util.Set;

import at.srfg.indexing.core.service.SolrService;
import at.srfg.indexing.model.party.PartyType;

public interface PartyService extends SolrService<PartyType> {
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
