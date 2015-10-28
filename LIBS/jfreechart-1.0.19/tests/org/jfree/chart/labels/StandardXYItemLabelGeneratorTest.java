/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2013, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * -------------------------------------
 * StandardXYItemLabelGeneratorTest.java
 * -------------------------------------
 * (C) Copyright 2003-2013, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 23-Mar-2003 : Version 1 (DG);
 * 26-Feb-2004 : Updates for new code (DG);
 * 20-Jan-2006 : Renamed StandardXYItemLabelGeneratorTests.java (DG);
 * 25-Jan-2007 : Added independence checks to testCloning() (DG);
 * 23-Apr-2008 : Added testPublicCloneable() (DG);
 *
 */
package org.jfree.chart.labels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.jfree.chart.TestUtilities;

import org.jfree.util.PublicCloneable;
import org.junit.Test;

/**
 * Tests for the {@link StandardXYItemLabelGenerator} class.
 */
public class StandardXYItemLabelGeneratorTest {

    /**
     * A series of tests for the equals() method.
     */
    @Test
    public void testEquals() {

        // some setup...
        String f1 = "{1}";
        String f2 = "{2}";
        NumberFormat xnf1 = new DecimalFormat("0.00");
        NumberFormat xnf2 = new DecimalFormat("0.000");
        NumberFormat ynf1 = new DecimalFormat("0.00");
        NumberFormat ynf2 = new DecimalFormat("0.000");

        StandardXYItemLabelGenerator g1 = null;
        StandardXYItemLabelGenerator g2 = null;

        g1 = new StandardXYItemLabelGenerator(f1, xnf1, ynf1);
        g2 = new StandardXYItemLabelGenerator(f1, xnf1, ynf1);
        assertTrue(g1.equals(g2));
        assertTrue(g2.equals(g1));

        g1 = new StandardXYItemLabelGenerator(f2, xnf1, ynf1);
        assertFalse(g1.equals(g2));
        g2 = new StandardXYItemLabelGenerator(f2, xnf1, ynf1);
        assertTrue(g1.equals(g2));

        g1 = new StandardXYItemLabelGenerator(f2, xnf2, ynf1);
        assertFalse(g1.equals(g2));
        g2 = new StandardXYItemLabelGenerator(f2, xnf2, ynf1);
        assertTrue(g1.equals(g2));

        g1 = new StandardXYItemLabelGenerator(f2, xnf2, ynf2);
        assertFalse(g1.equals(g2));
        g2 = new StandardXYItemLabelGenerator(f2, xnf2, ynf2);
        assertTrue(g1.equals(g2));

        DateFormat xdf1 = new SimpleDateFormat("d-MMM");
        DateFormat xdf2 = new SimpleDateFormat("d-MMM-yyyy");
        DateFormat ydf1 = new SimpleDateFormat("d-MMM");
        DateFormat ydf2 = new SimpleDateFormat("d-MMM-yyyy");

        g1 = new StandardXYItemLabelGenerator(f1, xdf1, ydf1);
        g2 = new StandardXYItemLabelGenerator(f1, xdf1, ydf1);
        assertTrue(g1.equals(g2));
        assertTrue(g2.equals(g1));

        g1 = new StandardXYItemLabelGenerator(f1, xdf2, ydf1);
        assertFalse(g1.equals(g2));
        g2 = new StandardXYItemLabelGenerator(f1, xdf2, ydf1);
        assertTrue(g1.equals(g2));

        g1 = new StandardXYItemLabelGenerator(f1, xdf2, ydf2);
        assertFalse(g1.equals(g2));
        g2 = new StandardXYItemLabelGenerator(f1, xdf2, ydf2);
        assertTrue(g1.equals(g2));

    }

    /**
     * Simple check that hashCode is implemented.
     */
    @Test
    public void testHashCode() {
        StandardXYItemLabelGenerator g1 = new StandardXYItemLabelGenerator();
        StandardXYItemLabelGenerator g2 = new StandardXYItemLabelGenerator();
        assertTrue(g1.equals(g2));
        assertTrue(g1.hashCode() == g2.hashCode());
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        StandardXYItemLabelGenerator g1 = new StandardXYItemLabelGenerator();
        StandardXYItemLabelGenerator g2 = (StandardXYItemLabelGenerator) g1.clone();
        assertTrue(g1 != g2);
        assertTrue(g1.getClass() == g2.getClass());
        assertTrue(g1.equals(g2));

        // check independence
        g1.getXFormat().setMinimumIntegerDigits(2);
        assertFalse(g1.equals(g2));
        g2.getXFormat().setMinimumIntegerDigits(2);
        assertTrue(g1.equals(g2));

        g1.getYFormat().setMinimumIntegerDigits(2);
        assertFalse(g1.equals(g2));
        g2.getYFormat().setMinimumIntegerDigits(2);
        assertTrue(g1.equals(g2));

        // another test...
        g1 = new StandardXYItemLabelGenerator("{0} {1} {2}",
                DateFormat.getInstance(), DateFormat.getInstance());
        g2 = (StandardXYItemLabelGenerator) g1.clone();
        assertTrue(g1 != g2);
        assertTrue(g1.getClass() == g2.getClass());
        assertTrue(g1.equals(g2));

        // check independence
        g1.getXDateFormat().setNumberFormat(new DecimalFormat("0.000"));
        assertFalse(g1.equals(g2));
        g2.getXDateFormat().setNumberFormat(new DecimalFormat("0.000"));
        assertTrue(g1.equals(g2));

        g1.getYDateFormat().setNumberFormat(new DecimalFormat("0.000"));
        assertFalse(g1.equals(g2));
        g2.getYDateFormat().setNumberFormat(new DecimalFormat("0.000"));
        assertTrue(g1.equals(g2));

    }

    /**
     * Check to ensure that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        StandardXYItemLabelGenerator g1 = new StandardXYItemLabelGenerator();
        assertTrue(g1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        StandardXYItemLabelGenerator g1 = new StandardXYItemLabelGenerator();
        StandardXYItemLabelGenerator g2 = (StandardXYItemLabelGenerator) TestUtilities.serialised(g1);
        assertEquals(g1, g2);
    }
}
