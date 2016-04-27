package com.dk.digitalstore.action;

/**
 * Created by dkulkarni on 26/04/16.
 */
public interface Action<T> {

    T invoke();
}
