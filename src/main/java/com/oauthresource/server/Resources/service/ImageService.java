package com.oauthresource.server.Resources.service;

import com.oauthresource.server.Resources.models.Image;
import com.oauthresource.server.Resources.models.ImageEditRequest;

import org.springframework.stereotype.Service;

@Service
public interface ImageService {

    public Image save(Image image);

    public Image findByFileName(String fileName);
    
    public Image update(ImageEditRequest image);

}
