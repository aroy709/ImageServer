package com.oauthresource.server.Resources.controllers;

import com.oauthresource.server.Resources.helpers.FileNameHelper;
import com.oauthresource.server.Resources.models.Image;
import com.oauthresource.server.Resources.models.ImageEditRequest;
import com.oauthresource.server.Resources.payloads.ImageResponse;
import com.oauthresource.server.Resources.service.ImageService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api")
public class ImageController {

    @Autowired
    private ImageService imageService;

    private FileNameHelper fileHelper = new FileNameHelper();
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/upload")
    public ImageResponse uploadSingleFile(@RequestParam MultipartFile file) {
        Image image = Image.buildImage(file, fileHelper);
        imageService.save(image);
        return new ImageResponse(image);
    }
    
    /**
	 * Upload multiple files to database.
	 * 
	 * @param files files data
	 * @return return saved images info list with ImageResponse class.
	 */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/uploads")
	public List<ImageResponse> uploadMultiFiles(@RequestParam MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadSingleFile(file)).collect(Collectors.toList());
	}

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
    @GetMapping("/show/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) throws Exception {
        Image image = getImageByName(fileName);
        return ResponseEntity.ok().contentType(MediaType.valueOf(image.getFileType())).body(image.getData());
    }
    
//    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
//    @GetMapping("/show/{fileName}/v2")
//    public Image getImageDetails(@PathVariable String fileName) throws Exception {
//        Image image = getImageByName(fileName);
//        return image;
//    }
    
    

    public Image getImageByName(String name) throws Exception {
        Image image = imageService.findByFileName(name);
        if (image == null) {
            return Image.defaultImage();
        }
        return image;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/edit/image")
    public ImageResponse editImage(@RequestBody ImageEditRequest request) {
    	Image image= this.imageService.update(request);
    	return new ImageResponse(image);
    }
    
    
}
