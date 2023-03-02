package taxi.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taxi.model.Car;
import taxi.model.Manufacturer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturerDaoImplTest {
    private static ManufacturerDao manufacturerDao;

    private static Manufacturer manufacturer;

    @BeforeAll
    static void beforeAll() {
        manufacturerDao = new ManufacturerDaoImpl();
    }

    @BeforeEach
    void setUp() {
        manufacturer = new Manufacturer("testName", "testCountry");
    }

    @Test
    void manufacturer_create_Ok() {
        Assertions.assertEquals(manufacturer, manufacturerDao.create(manufacturer));
    }

    @Test
    void manufacturer_get_Ok() {
        Manufacturer testManufacturer = manufacturerDao.getAll().get(0);
        Assertions.assertEquals(testManufacturer, manufacturerDao.get(testManufacturer.getId()).get());
    }

    @Test
    void manufacturer_update_Ok() {
        Manufacturer testManufacturer = manufacturerDao.getAll().get(0);
        Assertions.assertEquals(testManufacturer, manufacturerDao.get(testManufacturer.getId()).get());
    }

    @Test
    void manufacturer_getAll_Ok() {
        manufacturerDao.getAll();
    }

    @Test
    void manufacturer_delete_Ok() {
        Manufacturer testManufacturer = manufacturerDao.getAll().get(0);
        manufacturerDao.delete(testManufacturer.getId());
        Assertions.assertEquals(Optional.empty(), manufacturerDao.get(testManufacturer.getId()));
    }

    @Test
    void ManufacturerDao_Create_nameNull_notOk() {
        manufacturer.setName(null);
        Assertions.assertThrows(RuntimeException.class, () -> manufacturerDao.create(manufacturer));
    }

    @Test
    void manufacturerDao_Create_countryNull_notOk() {
        manufacturer.setCountry(null);
        Assertions.assertThrows(RuntimeException.class, () -> manufacturerDao.create(manufacturer));
    }


    @Test
    void ManufacturerDao_getIdNull_notOk() {
        Assertions.assertThrows(RuntimeException.class, () -> manufacturerDao.get(null));
    }

    @Test
    void manufacturerDao_delete_idNull_notOk() {
        Assertions.assertThrows(RuntimeException.class, () -> manufacturerDao.delete(null));
    }


    @Test
    void manufacturer_updateCountryNull_notOk() {
        Manufacturer manufacturerTest = manufacturerDao.getAll().get(0);
        manufacturerTest.setCountry(null);
        Assertions.assertThrows(RuntimeException.class, () -> manufacturerDao.update(manufacturerTest));
    }

    @Test
    void ManufacturerDao_updateNameNull_notOk() {
        Manufacturer manufacturerTest = manufacturerDao.getAll().get(0);
        manufacturerTest.setName(null);
        Assertions.assertThrows(RuntimeException.class, () -> manufacturerDao.update(manufacturerTest));
    }

    @Test
    void manufacturerDao_deleteWrongId_notOk() {
        Assertions.assertFalse(manufacturerDao.delete(2371837123781237L));
    }
}