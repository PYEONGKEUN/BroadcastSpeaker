package com.poseungcar.broadcastspeaker.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;


@Controller
@PropertySource({"classpath:profiles/${spring.profiles.active}/application.properties"})
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Value("${tts.location}")
	private String ttsLocation;
	@Value("${tts.uri_path}")
	private String ttsUriPath;

	//	@PostMapping(value = "/stream", consumes = "application/json", produces = "application/json")
	//	public ResponseEntity<InputStreamResource> stream ( 
	//			@RequestAttribute(name = "filename")String fileName
	//			) throws Exception{				
	//
	//		File file = new File("/opt/clovatest/"+fileName + ".mp3");
	//			
	//		
	//		// 스트리밍 생성
	//		HttpHeaders headers = new HttpHeaders();
	//		headers.add("Content-disposition", "attachment; filename="+fileName+".mp3");
	//		return ResponseEntity.ok().headers(headers).contentLength(file.length())
	//				.contentType(MediaType.parseMediaType("application/octet-stream"))
	//				.body(new InputStreamResource(new FileInputStream(file)));
	//	}
	//	@PostMapping(value = "/stream", consumes = "application/json", produces = "application/json")
	//	public ResponseEntity<InputStreamResource> download ( 
	//			@RequestAttribute(name = "filename")String fileName
	//			
	//			) throws Exception{				
	//
	//		File file = new File("/opt/clovatest/"+fileName + ".mp3");
	//			
	//		
	//		// 스트리밍 생성
	//		HttpHeaders headers = new HttpHeaders();
	//		headers.add("Content-disposition", "attachment; filename="+fileName+".mp3");
	//		return ResponseEntity.ok().headers(headers).contentLength(file.length())
	//				.contentType(MediaType.parseMediaType("application/octet-stream"))
	//				.body(new InputStreamResource(new FileInputStream(file)));
	//	}


	@GetMapping(value = "/stream/{id}/{fileName}")
	public ResponseEntity<InputStreamResource> stream (
			@PathVariable("id") String id,
			@PathVariable("fileName") String fileName
			){				
		try {
			File file = new File(ttsLocation+"/"+id+"/"+fileName + ".mp3");
			

			// 스트리밍 생성
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-disposition", "attachment; filename="+fileName+".mp3");
			return ResponseEntity.ok().headers(headers).contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/octet-stream"))
					.body(new InputStreamResource(new FileInputStream(file)));
		}catch(FileNotFoundException e) {
			logger.info(e.getStackTrace().toString());
			e.printStackTrace();
		}
		return null;

	}
	
	
//	@GetMapping(value = "/download/{fileName}")
//	public ResponseEntity<InputStreamResource> download ( 
//			@PathVariable("fileName") String fileName
//			) throws Exception{				
//
//		File file = new File(ttsLocation + fileName + ".mp3");
//		
//		// 스트리밍 생성
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-disposition", "attachment; filename="+fileName+".mp3");
//		return ResponseEntity.ok().headers(headers).contentLength(file.length())
//				.contentType(MediaType.parseMediaType("application/octet-stream"))
//				.body(new InputStreamResource(new FileInputStream(file)));
//		
//
//	}

}
