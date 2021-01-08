package at.srfg.iot.indexing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
/**
 * SOLR Context for Asset Indexing module. 
 * @author dglachs
 *
 */
@Configuration
@EnableSolrRepositories(basePackages= {
			// enable core SOLR repositories [ClassType | PropertyType | CodedType]Repository
			"at.srfg.iot.common.solr.indexing.core.service.repository",
			// enable asset SOLR repositories
			"at.srfg.iot.indexing.service.repository",
			// TODO: remove again - enable (temporary) HMA Sensor Playground Repository
			//       
			"at.srfg.iot.indexing.service.playground.repository",

		}, 
		schemaCreationSupport=true)
public class SolrContext extends at.srfg.iot.common.solr.indexing.core.solr.SolrContext {
	
	public SolrContext() {
		// 
	}

}
