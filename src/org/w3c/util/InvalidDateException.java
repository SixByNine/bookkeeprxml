// InvalidDateException.java
// $Id: InvalidDateException.java,v 1.2 2008/10/08 07:15:11 sixbynine Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 2000.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.util;

/**
 * @version $Revision: 1.2 $
 * @author  Benot Mah (bmahe@w3.org)
 */
public class InvalidDateException extends Exception {

    public InvalidDateException(String msg) {
	super(msg);
    }
    
}
