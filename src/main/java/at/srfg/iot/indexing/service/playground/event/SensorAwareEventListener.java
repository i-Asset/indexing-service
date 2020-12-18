package at.srfg.iot.indexing.service.playground.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import at.srfg.iot.common.solr.model.model.playground.ISensorAware;
import at.srfg.iot.common.solr.model.model.playground.Sensor;
import at.srfg.iot.indexing.service.playground.repository.SensorRepository;

@Component
public class SensorAwareEventListener {

	@Autowired
	private SensorRepository sensorRepo;
	@Async
	@EventListener
	public void onRemoveSensorAware(RemoveSensorAwareEvent event) {
		ISensorAware item = event.getItem();
		// find all sensors linked to the sensorAware item
		List<Sensor> subModels = sensorRepo.findBySensorAware(item.getUri());
		if ( ! subModels.isEmpty()) {
			// delete ... only when list of sensorAware items is the item itself
			subModels.stream().forEach(new Consumer<Sensor>() {

				@Override
				public void accept(Sensor t) {
					if (t.getSensorAware().size()== 1) {
						sensorRepo.delete(t);
					}
					else {
						t.getSensorAware().remove(item.getUri());
						sensorRepo.save(t);
					}
					
				}});
		}
	}
	@Async
	@EventListener
	public void onApplicationEvent(SensorAwareEvent event) {

		ISensorAware item = event.getItem();
		if ( item.getSensorMap() != null && !item.getSensorMap().isEmpty()) {
			List<Sensor> existing = sensorRepo.findBySensorAware(item.getUri());
			
			// find all properties based on idxField name
			// keep a map of properties to change
			Map<String, Sensor> changed = new HashMap<String, Sensor>();

			// check whether an existing property lacks a itemFieldName or a label
			existing.forEach(new Consumer<Sensor>() {

				@Override
				public void accept(Sensor c) {
					// try to find the property in the custom property map based
					// on the localNaem
					Sensor change = item.getSensorMap().get(c.getUri());
					// 
					if (change != null) {
						boolean changeDetected = false;
						
						if (!c.getSensorAware().contains(item.getUri())) {
							c.getSensorAware().add(item.getUri());
							changeDetected = true;
						}
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
						item.getSensorMap().remove(change.getUri());
					}
						
				}
			});
			// process the remaining properties - they are not yet indexed!
			item.getSensorMap().forEach(new BiConsumer<String, Sensor>() {

				@Override
				public void accept(String qualifier, Sensor newProp) {
//					submodelRepo.save(newProp);
					// add the new custom property to the index
					if (newProp.getSensorAware()==null) {
						newProp.setSensorAware(new HashSet<String>());
					}
					newProp.getSensorAware().add(item.getUri());
					changed.put(newProp.getUri(), newProp);
				}
			});
//			submodelRepo.saveAll(changed.values());
			// save all changed & new properties
			for (Sensor newPt : changed.values()) {
				sensorRepo.save(newPt);
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
}
