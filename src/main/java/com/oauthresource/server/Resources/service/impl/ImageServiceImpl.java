package com.oauthresource.server.Resources.service.impl;

import com.oauthresource.server.Resources.models.Image;
import com.oauthresource.server.Resources.models.ImageEditRequest;
import com.oauthresource.server.Resources.repository.ImageRepository;
import com.oauthresource.server.Resources.service.ImageService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Override
    public Image save(Image image) throws NullPointerException {
    	log.debug("Image details to be uploaded",image);
        if (image == null)
            throw new NullPointerException("Image Data NULL");
        Image imageResponse= imageRepository.save(image);
        log.debug("Image uploaded successfully", image);
        return imageResponse;
    }

    @Override
    public Image findByFileName(String fileName) {
    	log.debug("Image to be fetched",fileName);
        Image image= this.imageRepository.findByFileName(fileName);
        log.debug("Image details fetched",image);
        return image;
    }
    
    @Override
    public Image update(ImageEditRequest imageRequest) {
    	log.debug("Image details to be updated", imageRequest);
    	Image image=findByUUID(imageRequest.getUuid());
    	image.setFileName(imageRequest.getFileName());
    	image.setDescription(imageRequest.getFileDescription());
    	image.setPrice(imageRequest.getPrice());
    	Image imageResponse= this.imageRepository.save(image);
    	log.debug("Updated Image details",imageResponse);
    	return imageResponse;
    }
    
    public Image findByUUID(String UUID) {
    	log.debug("Image to be fetched using UUID");
    	Image image= this.imageRepository.findByuuid(UUID);
    	log.debug("Image fetched",image);
    	return image;
    }
}
