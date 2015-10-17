package org.spo.svc2.shared;

public interface JsonMessageItf<T> {
	
	
	public String getHeader() ;

	public T getPayload() ;

	
	
}
