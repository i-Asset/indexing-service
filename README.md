# indexing-service
Spring-Boot Service providing search capabilites with Apache SOLR. This servicve extends the generic [solr-indexing module](https://github.com/i-Asset/solr-indexing) with additional services and SOLR-data models

## requirements

A running Apache SOLR service is required. If necessary download from the [Apache Solr Website](https://lucene.apache.org/solr/). Extracting the binary zip file should be sufficient. For starting Apache SOLR run

```
<solr.home>/bin/solr start -cloud
```
Once Apache Solr is running point the browser to the [Solr Admin Page](http://localhost:8983/solr/) to verify the available collections.

in order to stop the SOLR service, run 

```
<solr.home>/bin/solr stop -all
```

### Service build and startup

 ```
 mvn clean spring-boot:run
 ```

 During startup, the service tries to create new collections for
 
 - All solr-data classes for Concept Classes, Properties and Coded Lists
 - Assets, Submodels and Organizations (parties)

 
 
