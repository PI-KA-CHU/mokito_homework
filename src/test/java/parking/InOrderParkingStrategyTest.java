package parking;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {



	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("car1");
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn("oocl");

        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLot, car);

        Assert.assertEquals("car1", receipt.getCarName());
        Assert.assertEquals("oocl", receipt.getParkingLotName());
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("car1");

        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(car);

        Assert.assertEquals("car1", receipt.getCarName());
        Assert.assertEquals(ParkingStrategy.NO_PARKING_LOT, receipt.getParkingLotName());
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        doReturn(new Receipt()).when(inOrderParkingStrategy).createNoSpaceReceipt(any(Car.class));

        inOrderParkingStrategy.park(null,null);

        verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(any(Car.class));
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */

        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        doReturn(new Receipt()).when(inOrderParkingStrategy).createReceipt(any(ParkingLot.class),any(Car.class));

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot("oocl", 10));
        inOrderParkingStrategy.park(parkingLots,new Car("Jim"));

        verify(inOrderParkingStrategy, times(1)).createReceipt(any(ParkingLot.class),any(Car.class));
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        doReturn(new Receipt()).when(inOrderParkingStrategy).createReceipt(any(ParkingLot.class),any(Car.class));

        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot("oocl", 0);
        ParkingLot parkingLot2 = new ParkingLot("cargo", 1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Car car = new Car("Jim");
        inOrderParkingStrategy.park(parkingLots,car);

        verify(inOrderParkingStrategy, times(1)).createReceipt(parkingLot2,car);
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
