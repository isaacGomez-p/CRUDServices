package com.empresa.exception;

public class ObjectAlreadyFoundHandler extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8286324943931445658L;

	public ObjectAlreadyFoundHandler(String mensaje) {
		super(mensaje);
	}

}
