package com.dell.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dell.webservice.exception.StorageException;
import com.dell.webservice.service.StorageService;

@Controller
public class FileHandlingController {
	@Autowired
	StorageService storageService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
   public String index() {
	   return "index.html";
   }
	
	@RequestMapping(value="/do-upload", method = RequestMethod.POST, consumes ={"multipart/form-data"})
	public String upload(@RequestParam MultipartFile file) {
		//logic to upload file
		storageService.uploadFile(file);
	return "redirect:/success.html";
		}
	
	@ExceptionHandler(StorageException.class)
	public String handleStorageException() {
		return "redirect:/failure.html";
	}
}
