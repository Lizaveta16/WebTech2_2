package by.bsuir.Lizaveta16.task01.dao.impl;

import by.bsuir.Lizaveta16.task01.dao.ApplianceDAO;
import by.bsuir.Lizaveta16.task01.entity.criteria.Criteria;
import by.bsuir.Lizaveta16.task01.entity.Appliance;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class ApplianceDAOImpl implements ApplianceDAO {

	private static final String path = "src\\main\\resources\\appliances_db.xml";
	@Override
	public List<Appliance> find(Criteria criteria) {
		List<Appliance> foundAppliances = new ArrayList<>();
		Appliance appliance;

		try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)))) {
			do {
				appliance = (Appliance) decoder.readObject();
				if (fitsCriteria(appliance, criteria)) {
					foundAppliances.add(appliance);
				}

			} while (appliance != null);
		}
		catch (ArrayIndexOutOfBoundsException e) {
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (NoSuchFieldException | IllegalAccessException e) {
			System.out.println(e.getMessage());
		}

		return foundAppliances;
	}

	@Override
	public void save(List<Appliance> appliences) {
		try(XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)))){
			for(Appliance appliance : appliences){
				encoder.writeObject(appliance);
			}
		}
		catch (FileNotFoundException e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Appliance> parseAll() {
		List<Appliance> appliances = new ArrayList<>();
		try(XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)))){
			Appliance result;
			do{
				result = (Appliance)decoder.readObject();
				appliances.add(result);
			}
			while(result != null);
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch (ArrayIndexOutOfBoundsException e){
			//end of xml file;
		}
		return appliances;
	}

	private boolean fitsCriteria(Appliance appliance, Criteria criteria) throws NoSuchFieldException, IllegalAccessException {
		if (!appliance.getClass().getSimpleName().equals(criteria.getGroupSearchName())){
			return false;
		}

		Set<String> properties = criteria.getCriteria().keySet();
		for (String property : properties) {
			Field field = appliance.getClass().getDeclaredField(property);
			Object fieldValue = field.get(appliance);
			if (!fieldValue.toString().equals(criteria.getCriteria().get(property).toString())) {
				return false;
			}
		}
		return true;
	}

}