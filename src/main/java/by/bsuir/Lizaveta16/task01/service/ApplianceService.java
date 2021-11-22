package by.bsuir.Lizaveta16.task01.service;

import by.bsuir.Lizaveta16.task01.entity.Appliance;
import by.bsuir.Lizaveta16.task01.entity.criteria.Criteria;

import java.util.Comparator;
import java.util.List;

public interface ApplianceService {

	List<Appliance> find(Criteria criteria);
	List<Appliance> sort(Comparator<Appliance> comparator);
	List<Appliance> getMin(Comparator<Appliance> comparator);
	List<Appliance> getMax(Comparator<Appliance> comparator);
	
}
