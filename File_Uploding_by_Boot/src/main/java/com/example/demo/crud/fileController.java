package com.example.demo.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.xml.sax.InputSource;

import com.example.demo.crud.helper.Uplodefile;

@RestController
public class fileController {

	@Autowired
	public Uplodefile uplodefile;
	
	@PostMapping("/uplode-file")
	public ResponseEntity<String> addFile(@RequestParam("file") MultipartFile file) {
		
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		
		boolean isuploded=false;
		try {
			if(file.isEmpty()) {
				
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file is empity");
			}
			if(!file.getContentType().equalsIgnoreCase("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only jpeg file are allowed");
				
			}
			
			//now file uplode code
			isuploded = uplodefile.fileuUplode(file);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(isuploded) {
			
			//( ye hame =  ServletUriComponentsBuilder.fromCurrentContextPath() =localhost:8080 tk ka path return kare gaa;
			
			//total path hame ye mile ge =     http://localhost:8080/image/choosable file name 
			
			return ResponseEntity
					.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());

		}else{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file not uploded successfully");
			
		}
		
		
	}
}
