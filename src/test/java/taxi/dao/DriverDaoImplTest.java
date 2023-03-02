package taxi.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import taxi.model.Driver;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DriverDaoImplTest {
    private static DriverDao driverDao;

    private static Driver driver;

    @BeforeAll
    static void beforeAll() {
        driverDao = new DriverDaoImpl();
        driver = new Driver("testName", "443242"
                , "testLogin", "testPassword");
    }

    @Test
    void driverDao_create_Ok() {
        Assertions.assertEquals(driver, driverDao.create(driver));
    }

    @Test
    void driverDao_get_Ok() {
        Driver testDriver = driverDao.getAll().get(0);
        Assertions.assertEquals(testDriver, driverDao.get(testDriver.getId()).get());
    }

    @Test
    void driverDao_update_Ok() {
        Driver testDriver = driverDao.getAll().get(0);
        testDriver.setName("updated");
        Assertions.assertEquals(testDriver, driverDao.update(testDriver));
    }

    @Test
    void driverDao_getAll_Ok() {
        driverDao.getAll();
    }

    @Test
    void driverDao_findByLogin_Ok() {
        Driver testDriver = driverDao.getAll().get(0);
        Assertions.assertEquals(testDriver, driverDao.findByLogin(testDriver.getLogin()).get());
    }

    @Test
    void driverDao_delete_Ok() {
        Driver testDriver = driverDao.getAll().get(0);
        driverDao.delete(testDriver.getId());
        Assertions.assertEquals(Optional.empty(), driverDao.get(testDriver.getId()));
    }

    @Test
    void driverDao_Create_driverNull_notOk() {
        driver.setName(null);
        Assertions.assertThrows(RuntimeException.class, () -> driverDao.create(null));
    }

    @Test
    void driverDao_Create_nameNull_notOk() {
        driver.setName(null);
        Assertions.assertThrows(RuntimeException.class, () -> driverDao.create(driver));
    }

    @Test
    void driverDao_Create_licenseNull_notOk() {
        driver.setLicenseNumber(null);
        Assertions.assertThrows(RuntimeException.class, () -> driverDao.create(driver));
    }

    @Test
    void driverDao_Create_loginNull_notOk() {
        driver.setLogin(null);
        Assertions.assertThrows(RuntimeException.class, () -> driverDao.create(driver));
    }

    @Test
    void driverDao_Create_passwordNull_notOk() {
        driver.setPassword(null);
        Assertions.assertThrows(RuntimeException.class, () -> driverDao.create(driver));
    }

    @Test
    void driverDao_get_idNull_notOk() {
        Assertions.assertThrows(RuntimeException.class, () -> driverDao.get(null));
    }

    @Test
    void driverDao_update_driverNull_notOk() {
        Assertions.assertThrows(RuntimeException.class, () -> driverDao.update(null));
    }

    @Test
    void driverDao_update_nameNull_notOk() {
        driver.setName(null);
        Assertions.assertThrows(RuntimeException.class, () -> driverDao.update(driver));
    }

    @Test
    void driverDao_update_licenseNull_notOk() {
        driver.setLicenseNumber(null);
        Assertions.assertThrows(RuntimeException.class, () -> driverDao.update(driver));
    }

    @Test
    void driverDao_findByLogin_loginNull_notOk() {
        Assertions.assertEquals(Optional.empty(), driverDao.findByLogin(null));
    }

    @Test
    void driverDao_findByLogin_loginEmpty_notOk() {
        Assertions.assertEquals(Optional.empty(), driverDao.findByLogin(""));

    }

    @Test
    void driverDao_findByLogin_loginWrong_notOk() {
        Assertions.assertEquals(Optional.empty(), driverDao.findByLogin("asdnkajndsadfl"));
    }

    @Test
    void driverDao_delete_idNull_notOk() {
        Assertions.assertThrows(RuntimeException.class, () -> driverDao.delete(null));
    }

    @Test
    void driverDao_delete_idWrong_notOk() {
        Assertions.assertFalse(driverDao.delete(1231231231L));
    }

}