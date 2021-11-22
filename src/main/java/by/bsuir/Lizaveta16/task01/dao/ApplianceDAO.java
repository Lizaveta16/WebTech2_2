package by.bsuir.Lizaveta16.task01.dao;

import by.bsuir.Lizaveta16.task01.entity.criteria.Criteria;
import by.bsuir.Lizaveta16.task01.entity.Appliance;

import java.util.List;

public interface ApplianceDAO {
	List<Appliance> find(Criteria criteria);

	void save(List<Appliance> appliences);

	List<Appliance> parseAll();
}
