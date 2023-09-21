package com.oauthresource.server.Resources.payloads;


import com.oauthresource.server.Resources.models.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {
	private String uuid;
	private String fileName;
	private String fileType;
	private long size;
	private String description;
	private Double price;


	public ImageResponse(Image image) {
		setUuid(image.getUuid());
		setFileName(image.getFileName());
		setFileType(image.getFileType());
		setSize(image.getSize());
		setDescription(image.getDescription());
		setPrice(image.getPrice());
	}
}