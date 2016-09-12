import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public final class rpDiode {

    private GpioPinDigitalOutput pin;
    private GpioController gpio;
    private boolean boolinitialisierungerfolgt;
    
	private static final Pin[] pinArray = new Pin[] {RaspiPin.GPIO_00, 
	RaspiPin.GPIO_01, RaspiPin.GPIO_02, RaspiPin.GPIO_03, RaspiPin.GPIO_04, 
	RaspiPin.GPIO_05, RaspiPin.GPIO_06, RaspiPin.GPIO_07, RaspiPin.GPIO_08, 
	RaspiPin.GPIO_09, RaspiPin.GPIO_10, RaspiPin.GPIO_11, RaspiPin.GPIO_12, 
	RaspiPin.GPIO_13, RaspiPin.GPIO_14, RaspiPin.GPIO_15, RaspiPin.GPIO_16, 
	RaspiPin.GPIO_17, RaspiPin.GPIO_18, RaspiPin.GPIO_19, RaspiPin.GPIO_20};

    private int intPin;

    rpDiode() {
        gpio = GpioFactory.getInstance();
        boolinitialisierungerfolgt = false;
    }

    public void setPin(int pPin) {
        pin = null;

        System.out.println("Output-Pin gesetzt:");

        try {
			pin = gpio.provisionDigitalOutputPin(pinArray[pin]);
			pin.setShutdownOptions(true, PinState.LOW);
			System.out.println("Pin " + pPin + " gesetzt");

            boolinitialisierungerfolgt = true;
            
            intPin = pPin;

        } catch (NullPointerException f){
            System.out.println("Error: Pin nicht definiert? (NullPointerException)");
        }
    }
    
    
      public int gibPin() {
    	return intPin;
    }
    
    
       public void an() {
        if (boolinitialisierungerfolgt == true){
            try{
                pin.setState(PinState.HIGH);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }
    

    public void blinke() {
        if (boolinitialisierungerfolgt == true){
            try{
                for (int i = 0; i <= 5; i++) {
                    pin.toggle();
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                System.out.println("Error: Timeout (InterruptedException)");
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }
    
  

 

    public void aus() {
        if (boolinitialisierungerfolgt == true){
            try{
                pin.setState(PinState.LOW);
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }


    public void wechsel() {
        if (boolinitialisierungerfolgt == true){
            try{
                pin.toggle();
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
        }
    }


    public boolean istAn() {
        if (boolinitialisierungerfolgt == true){
            try{
                if (pin.getState() == PinState.HIGH) {
                    System.out.println("Ja, Diode ist an");
                    return true;
                } else {
                    System.out.println("Nein, Diode ist aus");
                    return false; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
            return false;
        }    
    }

    public boolean istAus() {
        if (boolinitialisierungerfolgt == true){
            try{
                if (pin.getState() == PinState.LOW) {
                    System.out.println("Ja, Diode ist aus");
                    return true;
                } else {
                    System.out.println("Nein, Diode ist an");
                    return false; 
                }
            } catch (NullPointerException f){
                System.out.println("Error: Pin nicht definiert? (NullPointerException)");
                return false;
            }
        } else {
            System.out.println("Zuerst Pin fuer die Diode angeben");
            return false;
        }    
    }

    public void destroy() {
		gpio.shutdown();
        gpio = null;
        pin = null;
    }

    public static void main(String[] args){
        System.out.println("start");
    }
}
