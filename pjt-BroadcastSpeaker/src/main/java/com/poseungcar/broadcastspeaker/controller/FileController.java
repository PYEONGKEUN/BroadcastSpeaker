package com.poseungcar.broadcastspeaker.controller;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

public class FileController {

	
	
	@PostMapping(value = "/download", consumes = "application/json", produces = "application/json")
	public ResponseEntity<InputStreamResource> stream ( 
			@RequestAttribute(name = "filename")String fileName
			) throws Exception{				

		File file = new File("/opt/clovatest/"+fileName + ".mp3");
		
		
		
		// 스트리밍 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-disposition", "attachment; filename="+fileName+".mp3");
		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(new FileInputStream(file)));
	}

}
