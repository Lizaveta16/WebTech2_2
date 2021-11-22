package by.bsuir.Lizaveta16.task01.service.impl;

import by.bsuir.Lizaveta16.task01.dao.ApplianceDAO;
import by.bsuir.Lizaveta16.task01.dao.DAOFactory;
import by.bsuir.Lizaveta16.task01.entity.Appliance;
import by.bsuir.Lizaveta16.task01.entity.criteria.Criteria;
import by.bsuir.Lizaveta16.task01.service.ApplianceService;
import by.bsuir.Lizaveta16.task01.service.validation.Validator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ApplianceServiceImpl implements ApplianceService{

	@Override
	public List<Appliance> find(Criteria criteria) {
		if (Validator.validCriteria(criteria)) {
			ApplianceDAO applianceDAO = DAOFactory.getInstance().getApplianceDAO();
			return applianceDAO.find(criteria);
		}
		else
			return null;
	}

	@Override
	public List<Appliance> getMin(Comparator<Appliance> comparator) {
		DAOFactory factory = DAOFactory.getInstance();
		List<Appliance> appliances = factory.getApplianceDAO().parseAll();
		Appliance min = appliances.stream().min(comparator).orElse(null);
		if (min != null) {
			return appliances.stream()
					.filter(p -> comparator.compare(p, min) == 0)
					.collect(Collectors.toList());
		}

		return new ArrayList<>();
	}

	@Override
	public List<Appliance> getSorted(Comparator<Appliance> comparator) {
		DAOFactory factory = DAOFactory.getInstance();
		List<Appliance> appliances = factory.getApplianceDAO().parseAll();
		appliances.sort(comparator);
		return appliances;
	}

	@Override
	public void save(List<Appliance> appliances) {
		DAOFactory factory = DAOFactory.getInstance();
		factory.getApplianceDAO().save(appliances);
	}

}
