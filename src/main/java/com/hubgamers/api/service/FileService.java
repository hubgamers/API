package com.hubgamers.api.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class FileService {
	
	@Autowired
	private Environment environment;
	
	public Map addImageCloudinary(MultipartFile file, Map params) {
		Cloudinary cloudinary = new Cloudinary(environment.getProperty("cloudinary_url"));
		try {
			return cloudinary.uploader().upload(file.getBytes(), params);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
