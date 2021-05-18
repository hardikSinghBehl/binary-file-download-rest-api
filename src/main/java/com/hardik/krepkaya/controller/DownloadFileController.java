package com.hardik.krepkaya.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadFileController {

	@GetMapping("/download-logo")
	public void logoFileDownloadHandler(final HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=ProjectLogo.png");

		final var outputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
		final var inputStream = new BufferedInputStream(
				new FileInputStream(new File("./src/main/resources/templates/projectLogo.png")));

		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		outputStream.flush();
		inputStream.close();

	}
}
