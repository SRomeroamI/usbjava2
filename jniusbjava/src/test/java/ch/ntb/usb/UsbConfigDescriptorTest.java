/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.ntb.usb;

import junit.framework.TestCase;

/**
 *
 * @author ste
 */
public class UsbConfigDescriptorTest extends TestCase {

    private final UsbInterface[] INTERFACES1 = new UsbInterface[] {
        new UsbInterface(),
        new UsbInterface(),
        new UsbInterface()
    };
    private final UsbInterface[] INTERFACES2 = new UsbInterface[] {
        new UsbInterface(),
        new UsbInterface(),
        new UsbInterface()
    };
    private final UsbInterfaceDescriptor[] INT_DESC1 = new UsbInterfaceDescriptor[] {
        new UsbInterfaceDescriptor()
    };
    private final UsbInterfaceDescriptor[] INT_DESC2 = new UsbInterfaceDescriptor[] {
        new UsbInterfaceDescriptor(),
        new UsbInterfaceDescriptor()
    };
    
    public UsbConfigDescriptorTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private UsbConfigDescriptor buildConfigDescriptor(
        boolean interfaces, boolean descriptors
    ) {
        UsbConfigDescriptor desc = new UsbConfigDescriptor();
        
        if (interfaces) {
            desc.setInterfaces((descriptors) ? INTERFACES1 : INTERFACES2);
        }

        if (descriptors) {
            INTERFACES1[0].setAlternateSetting(INT_DESC1);
            INTERFACES1[1].setAlternateSetting(INT_DESC2);
        }

        return desc;
    }

    /**
     * Test of getInterfaces method, of class UsbConfigDescriptor.
     */
    public void testGetInterfaceInvalidIndex() {
        UsbConfigDescriptor desc = buildConfigDescriptor(true, true);
        try {
            desc.getInterface(-1);
            fail("index out of bound must be caught");
        } catch (IllegalArgumentException e) {
            //
            // OK
            //
        }

        try {
            desc.getInterface(3);
            fail("index out of bound must be caught");
        } catch (IllegalArgumentException e) {
            //
            // OK
            //
        }
    }

    public void testGetInterfaceNull() {
        UsbConfigDescriptor desc = buildConfigDescriptor(false, false);

        try {
            desc.getInterface(0);
            fail("null interfaces must be caught");
        } catch (IllegalArgumentException e) {
            //
            // OK
            //
        }
    }

    public void testGetInterfaceValidIndex() {
        UsbConfigDescriptor desc = buildConfigDescriptor(true, true);

        assertEquals(INTERFACES1[0], desc.getInterface(0));
        assertEquals(INTERFACES1[1], desc.getInterface(1));
        assertEquals(INTERFACES1[2], desc.getInterface(2));

    }

    public void testGetInterfaceWithInvalidAltSettingsIndex() {
        UsbConfigDescriptor desc = buildConfigDescriptor(true, true);
        
        try {
            desc.getInterface(0, -1);
            fail("index out of bound must be caught");
        } catch (IllegalArgumentException e) {
            //
            // OK
            //
        }

        try {
            desc.getInterface(0, 1);
            fail("index out of bound must be caught");
        } catch (IllegalArgumentException e) {
            //
            // OK
            //
        }
    }


}