package io.egen.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AlertServiceImplTest.class, VehicleServiceImplTest.class, ReadingServiceImplTest.class})
public class JunitSuiteTest {

}
