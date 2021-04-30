package at.srfg.iot.indexing.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.web.bind.annotation.RestController;

import at.srfg.iot.common.datamodel.indexing.AssetTypeIndexing;
import at.srfg.iot.common.datamodel.indexing.PartyTypeIndexing;
import at.srfg.iot.common.datamodel.indexing.SubmodelTypeIndexing;
import at.srfg.iot.common.solr.indexing.core.controller.BasicIndexingAPI;
import at.srfg.iot.common.solr.model.model.asset.AssetType;
import at.srfg.iot.common.solr.model.model.asset.SubmodelType;
import at.srfg.iot.common.solr.model.model.party.PartyType;
import at.srfg.iot.common.solr.model.model.solr.FacetResult;
import at.srfg.iot.common.solr.model.model.solr.IndexField;
import at.srfg.iot.common.solr.model.model.solr.Search;
import at.srfg.iot.common.solr.model.model.solr.SearchResult;
import at.srfg.iot.indexing.service.AssetService;
import at.srfg.iot.indexing.service.PartyService;
import at.srfg.iot.indexing.service.SubmodelService;
//import eu.nimble.utility.LoggerUtils;
import io.swagger.annotations.Api;

//@CrossOrigin
@RestController
@Api(value = "Indexing Controller", description = "Search API to perform Solr operations on indexed parties (organizations), items, item-properties, "
		+ "property-codes and classes (item categories)")
public class AssetIndexingController extends BasicIndexingAPI implements AssetTypeIndexing, PartyTypeIndexing, SubmodelTypeIndexing {

	@Autowired
	protected AssetService assetService;
	
	@Autowired
	protected SubmodelService modelService;
	
	@Autowired
	protected PartyService partyService;

//	@Autowired
//	private IdentityService identityService;

	private static final Logger logger = LoggerFactory.getLogger(AssetIndexingController.class);

	@Override
	public Optional<PartyType> getPartyType(String uri) throws Exception {
		return partyService.get(uri);
	}

	@Override
	public Collection<IndexField> fieldsForPartyType(Set<String> fieldNames) throws Exception {
		return partyService.fields(fieldNames);
	}

	@Override
	public SearchResult<PartyType> searchForPartyType(String query, List<String> filterQuery, List<String> facetFields,
			int facetLimit, int facetMinCount, int start, int rows) throws Exception {
		return partyService.select(query, filterQuery, facetFields, facetLimit, 
				facetMinCount, new SolrPageRequest(start, rows));
	}

	@Override
	public SearchResult<PartyType> searchForPartyType(Search search) throws Exception {
		return partyService.search(search);
	}

	@Override
	public FacetResult suggestForPartyType(String query, String fieldName, int limit, int minCount) throws Exception {
		return partyService.suggest(query, fieldName, limit, minCount);
	}

	@Override
	public PartyType setPartyType(PartyType prop) throws Exception {
		partyService.set(prop);
		return prop;
	}

	@Override
	public boolean deletePartyType(String uri) throws Exception {
		// 
		partyService.remove(uri);
		return true;
	}

	@Override
	public Optional<AssetType> getAssetType(String uri) throws Exception {
		return assetService.get(uri);
	}

	@Override
	public Collection<IndexField> fieldsForAssetType(Set<String> fieldNames) throws Exception {
		return assetService.fields(fieldNames);
	}

	@Override
	public SearchResult<AssetType> searchForAssetType(String query, List<String> filterQuery, List<String> facetFields,
			int facetLimit, int facetMinCount, int start, int rows) throws Exception {
		return assetService.select(query, filterQuery, facetFields, facetLimit, 
				facetMinCount, new SolrPageRequest(start, rows));
	}

	@Override
	public SearchResult<AssetType> searchForAssetType(Search search) throws Exception {
		return assetService.search(search);
	}

	@Override
	public FacetResult suggestForAssetType(String query, String fieldName, int limit, int minCount) throws Exception {
		return assetService.suggest(query, fieldName, limit, minCount);
	}

	@Override
	public AssetType setAssetType(AssetType prop) throws Exception {
		assetService.set(prop);
		return prop;
	}

	@Override
	public boolean deleteAssetType(String uri) throws Exception {
		assetService.remove(uri);
		return true;
	}

	@Override
	public long deleteConcepts(String nameSpace) {
		long deleted = super.deleteConcepts(nameSpace);
		deleted += assetService.deleteNameSpace(nameSpace);
		deleted += partyService.deleteNameSpace(nameSpace);
		return deleted;
	}

	@Override
	public Optional<SubmodelType> getSubmodelType(String uri) throws Exception {
		return modelService.get(uri);
	}

	@Override
	public Collection<IndexField> fieldsForSubmodelType(Set<String> fieldNames) throws Exception {
		return modelService.fields(fieldNames);
	}

	@Override
	public SearchResult<SubmodelType> searchForSubmodelType(String query, List<String> filterQuery,
			List<String> facetFields, int facetLimit, int facetMinCount, int start, int rows) throws Exception {
		return modelService.select(query, filterQuery, facetFields, facetLimit, 
				facetMinCount, new SolrPageRequest(start, rows));
	}

	@Override
	public SearchResult<SubmodelType> searchForSubmodelType(Search search) throws Exception {
		return modelService.search(search);
	}

	@Override
	public FacetResult suggestForSubmodelType(String query, String fieldName, int limit, int minCount)
			throws Exception {
		return modelService.suggest(query, fieldName, limit, minCount);
	}

	@Override
	public SubmodelType setSubmodelType(SubmodelType prop) throws Exception {
		modelService.set(prop);
		return prop; 
	}

	@Override
	public boolean deleteSubmodelType(String uri) throws Exception {
		modelService.remove(uri);
		return true;
	}
	
}
