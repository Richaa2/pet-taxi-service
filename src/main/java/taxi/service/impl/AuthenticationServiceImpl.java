package taxi.service.impl;

import taxi.dao.DriverDao;
import taxi.exception.AuthenticationException;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Driver;
import taxi.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverDao driverDao;

    @Override
    public Driver login(String username, String password) throws AuthenticationException {
        Driver driver = driverDao.findByLogin(username).orElseThrow(()
                -> new AuthenticationException("Username or password was incorrect"));
        if (!driver.getPassword().equals(password)) {
            throw new AuthenticationException("Username or password was incorrect");
        }
        return driver;
    }
}
