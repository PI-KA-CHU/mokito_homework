package parking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Calendar;

import static org.mockito.Matchers.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {ParkingLot.class})
public class VipParkingStrategyPowerMockTest {

    @Test
    public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
        * by using PowerMock to mock static method */
        mockStatic(ParkingLot.class);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(25);

        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        Integer price = vipParkingStrategy.calculateHourlyPrice();

        Assert.assertEquals(Integer.valueOf(50), price);
    }

    @Test
    public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
         * by using PowerMock to mock static method */


    }
}
