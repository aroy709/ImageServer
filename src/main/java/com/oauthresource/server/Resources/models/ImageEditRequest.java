package com.oauthresource.server.Resources.models;

import lombok.Data;

@Data
public class ImageEditRequest {
	
	private String fileName;
	
	private String fileDescription;
	
	private Double price;
	
	private String uuid; 
	

}
