package by.bsuir.Lizaveta16.task01.service;

import by.bsuir.Lizaveta16.task01.service.impl.ApplianceServiceImpl;

/**
 * Factory for creating ApplianceService.
 */
public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private final ApplianceService applianceService = new ApplianceServiceImpl();
	
	private ServiceFactory() {}

	public ApplianceService getApplianceService() {

		return applianceService;
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

}
