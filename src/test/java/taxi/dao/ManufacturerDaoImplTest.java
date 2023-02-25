package taxi.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import taxi.model.Manufacturer;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturerDaoImplTest {
    ManufacturerDao manufacturerDao = new ManufacturerDaoImpl();

    Manufacturer manufacturer = new Manufacturer("testName","testCountry");

    @Test
    void manufacturer_create_Ok() {
        Assertions.assertEquals(manufacturer,manufacturerDao.create(manufacturer));
    }
}