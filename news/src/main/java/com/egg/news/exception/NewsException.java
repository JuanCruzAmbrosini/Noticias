/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.news.exception;

/**
 *
 * @author Juan Cruz
 */
public class NewsException extends Exception {

    /**
     * Creates a new instance of <code>NewsException</code> without detail
     * message.
     */
    public NewsException() {
    }

    /**
     * Constructs an instance of <code>NewsException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NewsException(String msg) {
        super(msg);
    }
}
