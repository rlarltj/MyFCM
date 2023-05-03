package com.example.myfcm.exception;

public class FcmException extends RuntimeException{
	public FcmException() {
		super();
	}

	public FcmException(String message) {
		super(message);
	}
}
