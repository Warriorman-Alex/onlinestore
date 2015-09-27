/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;

import javax.servlet.http.HttpServletRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Alex
 */
public class ValidatorTest {

    public ValidatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of valiadteQuantity method, of class Validator.
     */
    @Test
    public void testValiadteQuantity() {
        boolean expResult = false;
        String productId = "1";
        String quantity = "1";
        Validator instance = new Validator();

        boolean result = instance.validateQuantity(productId, quantity);
        assertEquals(expResult, result);
    }

    @Test
    public void testValiadteQuantityFalse() {
        boolean expResult = true;
        String productId = "1";
        String quantity = "qwerty";
        Validator instance = new Validator();
        
        boolean result = instance.validateQuantity(productId, quantity);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValiadteQuantityOutOfBounds() {
        boolean expResult = true;
        String productId = "1";
        String quantity = "-10";
        Validator instance = new Validator();
        
        boolean result = instance.validateQuantity(productId, quantity);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateForm method, of class Validator.
     */
    @Test
    public void testValidateForm() {
        boolean expResult = false;
        String name = "Alex";
        String email = "alex@ukr.net";
        String phone = "0503303278";
        String address = "Kirova, 51";
        String cityRegion = "Regular shipping";
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidateFormNameError() {
        boolean expResult = true;
        String name = "";
        String email = "alex@ukr.net";
        String phone = "0503303278";
        String address = "Kirova, 51";
        String cityRegion = "Regular shipping";
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);        
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testValidateFormNameErrorNull() {
        boolean expResult = true;
        String name = null;
        String email = "alex@ukr.net";
        String phone = "0503303278";
        String address = "Kirova, 51";
        String cityRegion = "Regular shipping";
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);        
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testValidateFormNameErrorToLong() {
        boolean expResult = true;
        String name = "Alexaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String email = "alex@ukr.net";
        String phone = "0503303278";
        String address = "Kirova, 51";
        String cityRegion = "Regular shipping";
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);        
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testValidateFormEmailError() {
        boolean expResult = true;
        String name = "Alex";
        String email = "alexukr.net";
        String phone = "0503303278";
        String address = "Kirova, 51";
        String cityRegion = "Regular shipping";
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);        
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testValidateFormEmailErrorNull() {
        boolean expResult = true;
        String name = "Alex";
        String email = null;
        String phone = "0503303278";
        String address = "Kirova, 51";
        String cityRegion = "Regular shipping";
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);        
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testValidateFormEmailErrorEmpty() {
        boolean expResult = true;
        String name = "Alex";
        String email = "";
        String phone = "0503303278";
        String address = "Kirova, 51";
        String cityRegion = "Regular shipping";
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);        
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testValidateFormPhoneError() {
        boolean expResult = true;
        String name = "Alex";
        String email = "alex@ukr.net";
        String phone = "0503303";
        String address = "Kirova, 51";
        String cityRegion = "Regular shipping";
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);        
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testValidateFormPhoneErrorNull() {
        boolean expResult = true;
        String name = "Alex";
        String email = "alex@ukr.net";
        String phone = null;
        String address = "Kirova, 51";
        String cityRegion = "Regular shipping";
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);        
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testValidateFormPhoneErrorEmpty() {
        boolean expResult = true;
        String name = "Alex";
        String email = "alex@ukr.net";
        String phone = "";
        String address = "Kirova, 51";
        String cityRegion = "Regular shipping";
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);        
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testValidateFormAddressError() {
        boolean expResult = true;
        String name = "Alex";
        String email = "alex@ukr.net";
        String phone = "0503303278";
        String address = "";
        String cityRegion = "Regular shipping";
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);        
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testValidateFormAddressErrorNull() {
        boolean expResult = true;
        String name = "Alex";
        String email = "alex@ukr.net";
        String phone = "0503303278";
        String address = null;
        String cityRegion = "Regular shipping";
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);        
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testValidateFormAddressErrorToLong() {
        boolean expResult = true;
        String name = "Alex";
        String email = "alex@ukr.net";
        String phone = "0503303278";
        String address = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String cityRegion = "Regular shipping";
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);        
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testValidateFormCityRegionErrorNull() {
        boolean expResult = true;
        String name = "Alex";
        String email = "alex@ukr.net";
        String phone = "0503303278";
        String address = "Kirova, 51";
        String cityRegion = null;
        String ccNumber = "1111222233334444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);
        assertEquals(expResult, result);
    } 
        
    @Test
    public void testValidateFormCCNumberErrorNull() {
        boolean expResult = true;
        String name = "Alex";
        String email = "alex@ukr.net";
        String phone = "0503303278";
        String address = "Kirova, 51";
        String cityRegion = "Regular shipping";
        String ccNumber = null;
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidateFormCCNumberErrorEmpty() {
        boolean expResult = true;
        String name = "Alex";
        String email = "alex@ukr.net";
        String phone = "0503303278";
        String address = "Kirova, 51";
        String cityRegion = "Regular shipping";
        String ccNumber = "";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidateFormCCNumberError() {
        boolean expResult = true;
        String name = "Alex";
        String email = "alex@ukr.net";
        String phone = "0503303278";
        String address = "Kirova, 51";
        String cityRegion = "Regular shipping";
        String ccNumber = "111122223333444";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Validator instance = new Validator();

        boolean result = instance.validateForm(name, email, phone, address, cityRegion, ccNumber, request);
        assertEquals(expResult, result);
    }

}
