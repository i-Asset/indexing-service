package at.srfg.iot.indexing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;


@Configuration
@EnableSolrRepositories(basePackages= {
			"at.srfg.indexing.core.service.repository",
			"at.srfg.iot.indexing.service.repository"
		}, 
		schemaCreationSupport=true)
public class SolrContext extends at.srfg.indexing.core.solr.SolrContext {
//	
//	@Resource
//	private Environment environment;
//	
//	@Bean
//	public SolrClientFactory solrClientFactory() {
//		String host = environment.getProperty("solr.host");
//		String user = environment.getProperty("solr.user");
//		String pass = environment.getProperty("solr.password");
//		if (! (StringUtils.isEmpty(user) || StringUtils.isEmpty(pass))) {
//			return new HttpSolrClientFactory(solrClient(host), new UsernamePasswordCredentials(user, pass), "BASIC");
//		}
//		return new HttpSolrClientFactory(solrClient(host));
//	}
//	@Bean
//	public SolrClient solrClient(@Value("${solr.host}") String solrHost) {
//		if ( solrHost.startsWith("http")) {
////			return new MySolrClient.Builder(solrHost).build();
//			return new HttpSolrClient.Builder(solrHost).build();
//		}
//		else {
////			return new CloudSolrClient(solrHost);
//			return new CloudSolrClient.Builder(Collections.singletonList(solrHost)).build();
//		}
//	}
//	@Bean
//	public SolrTemplate solrTemplate() {
//		return new SolrTemplate(solrClientFactory());
//	}
}
