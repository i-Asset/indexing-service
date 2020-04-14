package at.srfg.iot.indexing.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.srfg.indexing.AssetTypeIndexing;
import at.srfg.indexing.ClassTypeIndexing;
import at.srfg.indexing.CodedTypeIndexing;
import at.srfg.indexing.PartyTypeIndexing;
import at.srfg.indexing.PropertyTypeIndexing;
import at.srfg.indexing.asset.AssetType;
import at.srfg.indexing.model.common.ClassType;
import at.srfg.indexing.model.common.CodedType;
import at.srfg.indexing.model.common.PropertyType;
import at.srfg.indexing.model.solr.FacetResult;
import at.srfg.indexing.model.solr.IndexField;
import at.srfg.indexing.model.solr.Search;
import at.srfg.indexing.model.solr.SearchResult;
import at.srfg.indexing.party.PartyType;
import at.srfg.iot.indexing.service.ClassService;
import at.srfg.iot.indexing.service.CodeService;
import at.srfg.iot.indexing.service.PropertyService;
//import eu.nimble.utility.LoggerUtils;
import io.swagger.annotations.Api;

//@CrossOrigin
@RestController
@Api(value = "Indexing Controller", description = "Search API to perform Solr operations on indexed parties (organizations), items, item-properties, "
		+ "property-codes and classes (item categories)")
public class AssetIndexingController extends BasicIndexingAPI implements AssetTypeIndexing, PartyTypeIndexing {


//	@Autowired
//	private IdentityService identityService;

	private static final Logger logger = LoggerFactory.getLogger(AssetIndexingController.class);

	@Override
	public Optional<PartyType> getPartyType(String uri) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IndexField> fieldsForPartyType(Set<String> fieldNames) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchResult<PartyType> searchForPartyType(String query, List<String> filterQuery, List<String> facetFields,
			int facetLimit, int facetMinCount, int start, int rows) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchResult<PartyType> searchForPartyType(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacetResult suggestForPartyType(String query, String fieldName, int limit, int minCount) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartyType setPartyType(PartyType prop) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePartyType(String uri) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<AssetType> getAssetType(String uri) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IndexField> fieldsForAssetType(Set<String> fieldNames) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchResult<AssetType> searchForAssetType(String query, List<String> filterQuery, List<String> facetFields,
			int facetLimit, int facetMinCount, int start, int rows) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchResult<AssetType> searchForAssetType(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacetResult suggestForAssetType(String query, String fieldName, int limit, int minCount) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssetType setAssetType(AssetType prop) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAssetType(String uri) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


}
