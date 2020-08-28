package parking;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;

public class VipParkingStrategyTest {

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(new Receipt()).when(vipParkingStrategy).createReceipt(any(ParkingLot.class),any(Car.class));

        CarDao carDao = mock(CarDao.class);
        when(carDao.isVip(anyString())).thenReturn(true);
        vipParkingStrategy.carDao = carDao;

        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot2 = new ParkingLot("cargo", 0);
        parkingLots.add(parkingLot2);
        Car car = createMockCar("AJim");

        vipParkingStrategy.park(parkingLots,car);

        verify(vipParkingStrategy, times(1)).createReceipt(parkingLot2, car);
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(new Receipt()).when(vipParkingStrategy).createReceipt(any(ParkingLot.class),any(Car.class));

        CarDao carDao = mock(CarDao.class);
        when(carDao.isVip(anyString())).thenReturn(false);
        vipParkingStrategy.carDao = carDao;

        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot2 = new ParkingLot("cargo", 0);
        parkingLots.add(parkingLot2);
        Car car = createMockCar("AJim");

        vipParkingStrategy.park(parkingLots,car);

        verify(vipParkingStrategy, times(1)).createNoSpaceReceipt(car);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(new Receipt()).when(vipParkingStrategy).createReceipt(any(ParkingLot.class),any(Car.class));

        CarDao carDao = mock(CarDao.class);
        when(carDao.isVip(anyString())).thenReturn(true);
        vipParkingStrategy.carDao = carDao;

        Car car = createMockCar("AJim");

        boolean isAllow = vipParkingStrategy.isAllowOverPark(car);

        Assert.assertTrue(isAllow);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(new Receipt()).when(vipParkingStrategy).createReceipt(any(ParkingLot.class),any(Car.class));

        CarDao carDao = mock(CarDao.class);
        when(carDao.isVip(anyString())).thenReturn(true);
        vipParkingStrategy.carDao = carDao;

        Car car = createMockCar("Jim");

        boolean isAllow = vipParkingStrategy.isAllowOverPark(car);

        Assert.assertFalse(isAllow);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(new Receipt()).when(vipParkingStrategy).createReceipt(any(ParkingLot.class),any(Car.class));

        CarDao carDao = mock(CarDao.class);
        when(carDao.isVip(anyString())).thenReturn(false);
        vipParkingStrategy.carDao = carDao;

        Car car = createMockCar("AJim");

        boolean isAllow = vipParkingStrategy.isAllowOverPark(car);

        Assert.assertFalse(isAllow);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(new Receipt()).when(vipParkingStrategy).createReceipt(any(ParkingLot.class),any(Car.class));

        CarDao carDao = mock(CarDao.class);
        when(carDao.isVip(anyString())).thenReturn(false);
        vipParkingStrategy.carDao = carDao;

        Car car = createMockCar("Jim");

        boolean isAllow = vipParkingStrategy.isAllowOverPark(car);

        Assert.assertFalse(isAllow);
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
