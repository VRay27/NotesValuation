package com.noteanalyzer.appraisal.exceptions;


/**
 * This Exception will be thrown when detail from address source was not found in any apprisal source.
 * @author Arvind Ray
 *
 */
public class AddressNotAvailableException extends Exception {
    private static final long serialVersionUID = 3705043083010304496L;

    public AddressNotAvailableException(String msg) {
        super(msg);
    }
}
