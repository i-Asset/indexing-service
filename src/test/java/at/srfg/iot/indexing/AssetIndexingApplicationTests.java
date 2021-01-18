package at.srfg.iot.indexing;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import at.srfg.iot.common.solr.indexing.core.service.PropertyService;
import at.srfg.iot.common.solr.model.model.asset.AssetType;
import at.srfg.iot.common.solr.model.model.asset.SubmodelType;
import at.srfg.iot.common.solr.model.model.common.PropertyType;
import at.srfg.iot.common.solr.model.model.common.ValueQualifier;
import at.srfg.iot.indexing.service.AssetService;
import at.srfg.iot.indexing.service.SubmodelService;

@SpringBootTest
public class AssetIndexingApplicationTests {
	@Autowired
	private PropertyService propertyService;

	
	@Autowired
	private AssetService assetService;
	
	@Autowired
	private SubmodelService submodelService;
	
	@Test
	public void contextLoads() {
	}
	@Test
	public void testSubmodel() {
		AssetType asset = new AssetType();
		asset.setUri("urn:asset:1");
		asset.setLabel(Locale.ENGLISH, "MyAsset");
		asset.setLocalName("1");
		asset.setNameSpace("urn:asset:");
		asset.setCode("1");
		asset.addDescription(Locale.ENGLISH, "This is my first asset description");
		SubmodelType submodelType = new SubmodelType();
		submodelType.setUri("urn:asset:submodel:1");
		submodelType.setLabel(Locale.ENGLISH, "submodel");
		submodelType.setLocalName("1");
		submodelType.setNameSpace("urn:asset:");
		submodelType.setCode("1");
		submodelType.addDescription(Locale.ENGLISH, "This is my first submodel description");
		submodelType.addProperty("myValue", "container", "param");
		asset.addSubmodel(submodelType);
		assetService.set(asset);
		try {
			// allow async to complete
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 
		Optional<SubmodelType> opt = submodelService.get(submodelType.getUri());
		assertTrue(opt.isPresent());
		SubmodelType read = opt.get();
		assertTrue(read.getAsset().equals(asset.getUri()));
		List<String> containerParam = read.getProperties(String.class,"container", "param");
		// 
		assetService.remove(asset.getUri());
		try {
			// allow async to complete
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Optional<SubmodelType> postDelete = submodelService.get(submodelType.getUri());
		assertFalse(postDelete.isPresent());

		
	}
	@Test
	public void testClassification() {
		PropertyType labelProperty = new PropertyType();
		labelProperty.setLabel(Locale.GERMAN, "AssetProperty X");
		labelProperty.setCode("assetPropertyX");
		labelProperty.setUri("urn:test:assetPropertyX");
		labelProperty.setLocalName("urn:test:assetPropertyX");
		labelProperty.setNameSpace("urn:test:");
		labelProperty.setValueQualifier(ValueQualifier.STRING);
		propertyService.set(labelProperty);
		AssetType asset = new AssetType();
		asset.setUri("urn:test:asset1");
		asset.setLabel(Locale.GERMAN,  "Asset");
		asset.setLabel(Locale.ENGLISH, "Asset");
		// use the idShort of the model (hierarchy)
		asset.addProperty(10.0, "operation", "mix", "demo");
		asset.addProperty(150, labelProperty, "operation", "mix", "demo2" );
		asset.setCode("asset1");
		assetService.set(asset);
		try {
			// allow async to complete
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Optional<PropertyType> cust = propertyService.findCustomProperty(AssetType.class, "operationMixDemo2");
		
		assertTrue(cust.isPresent());
		PropertyType custProp = cust.get();
		assertTrue(custProp.getItemFieldNames().contains("operationMixDemo2"));
		assertTrue(custProp.getLabel(Locale.GERMAN).equals("AssetProperty X"));
		//
		Optional<AssetType> read = assetService.get(asset.getUri());
		assertTrue(read.isPresent());
		AssetType a = read.get();
		assertTrue(a.getProperties(Integer.class, "operation", "mix", "demo2").contains(Integer.valueOf(150)));
		propertyService.remove(labelProperty.getUri());
		// removing the asset will also remove the custom property
		assetService.remove(asset.getUri());
//		
		try {
			// allow async to complete
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Optional<PropertyType> custF = propertyService.findCustomProperty(AssetType.class, "operationMixDemo2");
		assertFalse(custF.isPresent());
		
	}

}
