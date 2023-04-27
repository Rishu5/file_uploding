package com.example.demo.crud.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Uplodefile {

//	public final String uplodedir = "D:\\S_T_S\\File_Uploding_by_Boot\\src\\main\\resources\\static\\image";
//yah dynamic image folder ka path de gaa (D:\\S_T_S\\File_Uploding_by_Boot\\src\\main\\resources\\static\\image)
	public final String uplodedir = new ClassPathResource("static/image/").getFile().getAbsolutePath();
public Uplodefile()throws Exception  {
	
}
	
	
	public boolean fileuUplode(MultipartFile file) {

		boolean uploded = false;
		
		System.out.println("this is file path " + uplodedir);

		try {
			InputStream is = file.getInputStream();
			byte data[] = new byte[is.available()];
			is.read(data);

			FileOutputStream fos = new FileOutputStream(uplodedir + File.separator + file.getOriginalFilename());
			fos.write(data);
			fos.flush();
			fos.close();
			
			
			//nio wala package for file uploding in one line
			//Files.copy(file.getInputStream(), Paths.get(uplodedir+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			
			
			
			
			
			
			uploded = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return uploded;

	}

}
