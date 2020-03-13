package com.poseungcar.broadcastspeaker.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ArticleDaoTest
 */
@RunWith(MockitoJUnitRunner.class)
public class CekControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CekControllerTest.class);

//	@InjectMocks
//	private CekController cekController;
//	private MockMvc MockMVC;
//	@Autowired
//	protected ResourceLoader resourceLoader;
//	
//	@Before
//	public void setup() {
//		MockMVC = MockMvcBuilders.standaloneSetup(cekController).build();
//	}
//	
//	
//
////	@Test
////	public void json_파일로테스트() throws Exception {
////		//given
////		final String requestBody = readJson("classpath:PLACE_NUMBER.json");
////		LOGGER.info("requestBody : "+requestBody);
////		//when
////		final ResultActions resultActions = MockMVC.perform(post("/cek")
////				.contentType(MediaType.APPLICATION_JSON_UTF8)
////				.content(requestBody))
////				.andDo(print());
////		//then
////		resultActions.andExpect(status().isOk());
////	}
//	protected String readJson(final String path) throws IOException {
//		final InputStream inputStream = resourceLoader.getResource(path).getInputStream();
//		return IOUtils.toString(inputStream, "UTF-8");
//	}


}
