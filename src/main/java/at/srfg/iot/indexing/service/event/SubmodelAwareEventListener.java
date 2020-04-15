package at.srfg.iot.indexing.service.event;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.jena.vocabulary.XSD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import at.srfg.indexing.core.service.repository.PropertyRepository;
import at.srfg.indexing.model.asset.ISubmodelAware;
import at.srfg.indexing.model.asset.SubmodelType;
import at.srfg.indexing.model.common.ICustomPropertyAware;
import at.srfg.indexing.model.common.PropertyType;
import at.srfg.indexing.model.common.ValueQualifier;
import at.srfg.iot.indexing.service.repository.SubmodelRepository;

@Component
public class SubmodelAwareEventListener {

	@Autowired
	private SubmodelRepository submodelRepo;
	@Async
	@EventListener
	public void onRemoveCustomPropertyAware(RemoveSubmodelAwareEvent event) {
		ISubmodelAware item = event.getItem();
		// find all submodels linked to the submodel aware item
		List<SubmodelType> subModels = submodelRepo.findByAsset(item.getUri());
		submodelRepo.deleteAll(subModels);
		
	}
	@Async
	@EventListener
	public void onApplicationEvent(SubmodelAwareEvent event) {

		ISubmodelAware item = event.getItem();
		if ( item.getSubmodelMap() != null && !item.getSubmodelMap().isEmpty()) {
			List<SubmodelType> existing = submodelRepo.findByAsset(item.getUri());
			
			// find all properties based on idxField name
			// keep a map of properties to change
			Map<String, SubmodelType> changed = new HashMap<String, SubmodelType>();

			// check whether an existing property lacks a itemFieldName or a label
			existing.forEach(new Consumer<SubmodelType>() {

				@Override
				public void accept(SubmodelType c) {
					// try to find the property in the custom property map based
					// on the localNaem
					SubmodelType change = item.getSubmodelMap().get(c.getUri());
					// link the submodel to the current submodel aware
					c.setAsset(item.getUri());
					// 
					if (change != null) {
						boolean changeDetected = false;
						// 
						// harmonize labels
						if ( harmonizeLabels(c.getLabel(), change.getLabel())) {
							changeDetected = true;
						}
						if ( harmonizeLabels(c.getComment(), change.getComment()) ) {
							changeDetected = true;
						}
						if ( harmonizeLabels(c.getDescription(), change.getDescription()) ) {
							changeDetected = true;
						}
						// remove any existing property so that is not added twice
						if ( changeDetected ) {
							changed.put(c.getUri(), c);
						}
						// in any case remove the checked property (based on the key) from the item
						item.getSubmodelMap().remove(change.getUri());
					}
						
				}
			});
			// process the remaining properties - they are not yet indexed!
			item.getSubmodelMap().forEach(new BiConsumer<String, SubmodelType>() {

				@Override
				public void accept(String qualifier, SubmodelType newProp) {
//					submodelRepo.save(newProp);
					// add the new custom property to the index
					newProp.setAsset(item.getUri());
					changed.put(newProp.getUri(), newProp);
				}
			});
//			submodelRepo.saveAll(changed.values());
			// save all changed & new properties
			for (SubmodelType newPt : changed.values()) {
				submodelRepo.save(newPt);
			}
		}
	}
	/**
	 * Helper method 
	 * @param toAdd
	 * @param from
	 */
	private boolean harmonizeLabels(Map<String, String> toAdd, Map<String, String> from) {
		boolean changeDetected = false;
		if (toAdd != null && from != null) {
			for (String lang : from.keySet()) {
				String old = toAdd.get(lang);
				if ( old == null || !old.equals(from.get(lang))) {
					toAdd.put(lang, from.get(lang));
					changeDetected = true;
				}
			}
		}
		return changeDetected;
	}
	private Optional<String> findInItem(ICustomPropertyAware item, Collection<String> idxField) {
		return idxField.stream()
			.filter(new Predicate<String>() {

				@Override
				public boolean test(String t) {
					return item.getCustomProperties().containsKey(t);
				}
				
			})
			.findFirst();
	}
}
