package br.fatec.vidapet.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {
	
	@Autowired
	private AmazonS3 s3client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	public URI upload(MultipartFile multipartFile) {
		try {
			String filename = multipartFile.getOriginalFilename();
			InputStream input = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return upload(input, filename, contentType);
		} catch(IOException e) {
			throw new RuntimeException("Erro de IO: " + e.getMessage());
		}
	}
	
	public URI upload(InputStream input, String filename, String contentType) {
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);
			s3client.putObject(bucketName, filename, input, metadata);
			return s3client.getUrl(bucketName, filename).toURI();
		} catch(URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI.");
		}
	}
}
