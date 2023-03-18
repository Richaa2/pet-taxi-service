package taxi.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taxi.dao.impl.CarDaoImpl;
import taxi.dao.impl.DriverDaoImpl;
import taxi.dao.impl.ManufacturerDaoImpl;
import taxi.model.Car;
import taxi.model.Driver;
import taxi.model.Manufacturer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CarDaoImplTest {
    private static CarDao carDao;
    private static Manufacturer manufacturer;
    private static Driver driver;

    private static ManufacturerDao manufacturerDao;
    private static DriverDao driverDao;
    private static Car car;


    @BeforeAll
    static void beforeAll() {
        carDao = new CarDaoImpl();
        manufacturerDao = new ManufacturerDaoImpl();
        driverDao = new DriverDaoImpl();
    }

    @BeforeEach
    void setUp() {
        manufacturer = new Manufacturer("name", "uk");
        driver = new Driver("testName", "111111", "testLogin", "testPassword");
        car = new Car("model", manufacturer);
    }

    @Test
    void carDao_Create_Ok() {
        manufacturerDao.create(manufacturer);
        Assertions.assertEquals(car, carDao.create(car));
    }

    @Test
    void carDao_get_Ok() {
        Car carTest = carDao.getAll().get(0);
        Assertions.assertEquals(carTest, carDao.get(carTest.getId()).get());
    }

    @Test
    void carDao_update_Ok() {
        driverDao.create(driver);
        Car carTest = carDao.getAll().get(0);
        carTest.setModel("changed");
        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver);
        carTest.setDrivers(drivers);
        Assertions.assertEquals(carTest, carDao.update(carTest));
    }

    @Test
    void carDao_getAll_Ok() {
        carDao.getAll();
    }

    @Test
    void carDao_getAllByDrivers_Ok() {
        driverDao.create(driver);
        carDao.getAllByDriver(driver.getId());
    }

    @Test
    void carDao_delete_Ok() {
        Car carTest = carDao.getAll().get(0);
        carDao.delete(carTest.getId());
        Assertions.assertEquals(Optional.empty(), carDao.get(carTest.getId()));
    }

    @Test
    void carDao_Create_ManufacturerNull_notOk() {
        car.setManufacturer(null);
        Assertions.assertThrows(RuntimeException.class, () -> carDao.create(car));
    }

    @Test
    void carDao_Create_ModelNull_notOk() {
        car.setModel(null);
        Assertions.assertThrows(RuntimeException.class, () -> carDao.create(car));
    }

    @Test
    void carDao_Create_DriversNull_notOk() {
        car.setDrivers(null);
        Assertions.assertThrows(RuntimeException.class, () -> carDao.create(car));
    }

    @Test
    void carDao_getIdNull_notOk() {
        Assertions.assertThrows(RuntimeException.class, () -> carDao.get(null));
    }

    @Test
    void carDao_getAllByDriverNull_notOk() {
        Assertions.assertThrows(RuntimeException.class, () -> carDao.getAllByDriver(null));
    }


    @Test
    void carDao_updateManufacturerNull_notOk() {
        Car carTest = carDao.getAll().get(0);
        carTest.setManufacturer(null);
        Assertions.assertThrows(RuntimeException.class, () -> carDao.update(carTest));
    }

    @Test
    void carDao_updateModelNull_notOk() {
        Car carTest = carDao.getAll().get(0);
        carTest.setModel(null);
        Assertions.assertThrows(RuntimeException.class, () -> carDao.update(carTest));
    }

    @Test
    void carDao_updateDriversNull_notOk() {
        Car carTest = carDao.getAll().get(0);
        carTest.setDrivers(null);
        Assertions.assertThrows(RuntimeException.class, () -> carDao.update(carTest));
    }

    @Test
    void carDao_deleteNull_notOk() {
        Assertions.assertThrows(RuntimeException.class, () -> carDao.delete(null));
    }

    @Test
    void carDao_deleteWrongId_notOk() {
        Assertions.assertFalse(carDao.delete(2371837123781237L));
    }
}