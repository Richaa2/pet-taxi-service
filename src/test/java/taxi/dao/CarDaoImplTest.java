package taxi.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import taxi.model.Car;
import taxi.model.Driver;
import taxi.model.Manufacturer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CarDaoImplTest {
    CarDao carDao = new CarDaoImpl();
    static Manufacturer manufacturer = new Manufacturer("name", "uk");
    static Driver driver = new Driver("testName", "111111", "testLogin", "testPassword");

    Car car = new Car("model", manufacturer);


    @BeforeAll
    static void beforeAll() {
        ManufacturerDao manufacturerDao = new ManufacturerDaoImpl();
        DriverDao driverDao = new DriverDaoImpl();
        List<Manufacturer> manufacturers = manufacturerDao.getAll();
        List<Driver> drivers = driverDao.getAll();
        boolean isExistManufacturers = manufacturers.stream()
                .anyMatch(x -> x.getName().equals(manufacturer.getName())
                        && x.getCountry().equals(manufacturer.getCountry()));
        boolean isExistDrivers = drivers.stream()
                .anyMatch(x -> x.getName().equals(driver.getName())
                        && x.getLicenseNumber().equals(driver.getLicenseNumber())
                        && x.getLogin().equals(driver.getLogin())
                        && x.getPassword().equals(driver.getPassword()));
        if (!isExistManufacturers) {
            manufacturerDao.create(manufacturer);
        } else {
            manufacturer.setId(manufacturers.stream().filter(x -> x.getName().equals(manufacturer.getName())
                    && x.getCountry().equals(manufacturer.getCountry())).findFirst().get().getId());
        }
        if (!isExistDrivers) {
            driverDao.create(driver);
        } else {
            driver.setId(drivers.stream().filter(x -> x.getName().equals(driver.getName())
                    && x.getLicenseNumber().equals(driver.getLicenseNumber())
                    && x.getLogin().equals(driver.getLogin())
                    && x.getPassword().equals(driver.getPassword())).findFirst().get().getId());
        }
    }

    @Test
    void carDao_Create_Ok() {
        Assertions.assertEquals(car, carDao.create(car));
    }

    @Test
    void carDao_get_Ok() {
        Car carTest = carDao.getAll().get(0);
        Assertions.assertEquals(carTest, carDao.get(carTest.getId()).get());
    }

    @Test
    void carDao_update_Ok() {
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