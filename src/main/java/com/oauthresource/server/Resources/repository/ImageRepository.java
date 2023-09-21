package com.oauthresource.server.Resources.repository;

import com.oauthresource.server.Resources.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Image findByFileName(String fileName);
    
    Image findByuuid(String UUID);

}