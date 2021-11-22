package by.bsuir.Lizaveta16.task01.main;

import by.bsuir.Lizaveta16.task01.entity.Appliance;
import by.bsuir.Lizaveta16.task01.entity.criteria.Criteria;
import by.bsuir.Lizaveta16.task01.entity.criteria.SearchCriteria;
import by.bsuir.Lizaveta16.task01.service.ApplianceService;
import by.bsuir.Lizaveta16.task01.service.ServiceFactory;

import java.util.Comparator;
import java.util.List;

public class Main {

//	private static List<Appliance> appliancesInitDataList = Arrays.asList(
//			new Kettle("Redmond", 64, 2200, 2, Color.Black),
//			new Kettle("Bosch ", 50.59, 2200, 1.5, Color.White),
//			new Oven("SAMSUNG ", 350, 300, 13.5, 23, 1, 1, 1),
//			new Oven("HORIZONT ", 135, 400, 10.5, 20, 1, 1, 1),
//			new Refrigerator("LG", 1980, 262, 59.5, 107, 341, 2, 1),
//			new Refrigerator("Atlant", 1599, 342, 80, 108, 364, 2, 1),
//			new TabletPC("Samsung Galaxy Tab A7", 769,
//					7040, 10, 8000, 12, Color.Grey),
//			new TabletPC("Xiaomi Pad 7", 1299,
//					8720, 11, 16000, 12, Color.Black)
//	);

	public static void main(String[] args) {
		List<Appliance> appliances;
		ServiceFactory factory = ServiceFactory.getInstance();
		ApplianceService service = factory.getApplianceService();
		Criteria kettleCriteria = new Criteria(SearchCriteria.Kettle.class.getSimpleName());
		appliances = service.find(kettleCriteria);
		if (appliances != null) {
			System.out.println("All kettles: ");
			PrintApplianceInfo.print(appliances);
		}

		Criteria ovenCriteria = new Criteria(SearchCriteria.Oven.class.getSimpleName());
		ovenCriteria.add("powerConsumption", 300);
		List<Appliance> ovens300 = service.find(ovenCriteria);
		if(ovens300 != null) {
			System.out.println("\nOvens with a capacity of 300:: ");
			PrintApplianceInfo.print(ovens300);
		}

		System.out.println();
		appliances = service.getMin(Comparator.comparing(Appliance::getPrice));
		System.out.println("Appliances with minimal price: ");
		PrintApplianceInfo.print(appliances);
	}

}
