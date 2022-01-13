package com.jewsol.common.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	public String getFilePath(){
		return "/www/jewsol_com/www/jewsol/image";
	}
	
	public String getFilePath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/image");
	}
	
	public String uploadProductImage(MultipartFile uploadFile,
			String filePath) {
		
		String fileName = uploadFile.getOriginalFilename();
		
		if(!fileName.equals("") || fileName.length() != 0){
			File file = new File(filePath, fileName);
			
			try {
				FileCopyUtils.copy(uploadFile.getInputStream(), new FileOutputStream(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			fileName = "noPic.jpg";
		}
		return fileName;
	}
}
