package com.example.fileuploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@SpringBootApplication
@RestController
public class FileUploaderApplication {

	private File original = new File("C:/Users/CRajapakse/Desktop/salesforces/testng-results.xml");
	private File copied = new File("C:/Users/CRajapakse/Desktop/salesforces/testng-results-copied.xml");

	public static void main(String[] args) {

		SpringApplication.run(FileUploaderApplication.class, args);
	}

	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile() throws IOException {
		InputStream in = new BufferedInputStream(
				new FileInputStream(original));
		OutputStream out = new BufferedOutputStream(
				new FileOutputStream(copied));
		byte[] buffer = new byte[10000000];
		int lengthRead;

		copied.createNewFile();
		while((lengthRead = in.read(buffer))>0){
			out.write(buffer, 0,lengthRead);
			out.close();
		}
		return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
	}


}
