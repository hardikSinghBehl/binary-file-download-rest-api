package com.hardik.krepkaya.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/download-logo")
public class DownloadFileController {

	@GetMapping("/http-response")
	public void logoFileDownloadThroughHttpResponseHandler(final HttpServletResponse httpServletResponse)
			throws IOException {
		httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=project-logo.png");

		final var outputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
		final var inputStream = new BufferedInputStream(
				new FileInputStream(new File("./src/main/resources/templates/project-logo.png")));

		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		outputStream.flush();
		inputStream.close();

	}

	@GetMapping("/response-entity")
	public ResponseEntity<InputStreamResource> logoFileDownloadThroughResponseEntityHandler() throws IOException {
		final var resource = new InputStreamResource(
				new FileInputStream(new File("./src/main/resources/templates/projectLogo.png")));
		return ResponseEntity.status(HttpStatus.OK)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=project-logo.png").body(resource);
	}
}
