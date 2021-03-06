package at.srfg.iot.indexing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import at.srfg.indexing.asset.AssetType;
import at.srfg.indexing.model.common.PropertyType;
import at.srfg.indexing.model.common.ValueQualifier;
import at.srfg.iot.indexing.service.AssetService;
import at.srfg.iot.indexing.service.PropertyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssetIndexingApplicationTests {
	@Autowired
	private PropertyService propertyService;

	
	@Autowired
	private AssetService assetService;
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testClassification() {
		PropertyType labelProperty = new PropertyType();
		labelProperty.setLabel("AssetProperty X", Locale.GERMAN);
		labelProperty.setCode("assetPropertyX");
		labelProperty.setUri("urn:test:assetPropertyX");
		labelProperty.setLocalName("urn:test:assetPropertyX");
		labelProperty.setNameSpace("urn:test:");
		labelProperty.setValueQualifier(ValueQualifier.STRING);
		labelProperty.addItemFieldName("label");
		propertyService.set(labelProperty);
		AssetType asset = new AssetType();
		asset.setUri("urn:test:asset1");
		asset.setLabel("Asset",Locale.GERMAN);
		asset.setLabel("Asset", Locale.ENGLISH);
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
		assertTrue(a.getIntPropertyValues("operation", "mix", "demo2").contains(Integer.valueOf(150)));
		propertyService.remove(labelProperty.getUri());
		// removing the asset will also remove the custom property
		assetService.remove(asset.getUri());
//		
		try {
			// allow async to complete
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Optional<PropertyType> custF = propertyService.findCustomProperty(AssetType.class, "operationMixDemo2");
		assertFalse(custF.isPresent());
		
	}

}
