package br.fatec.vidapet.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
	
	private final Path rootLocation;
	private static final String location = "uploads";
	
	public UploadService() throws IOException {
		rootLocation = Paths.get(location);
		try {
			if(!Files.exists(rootLocation)) {
				Files.createDirectories(rootLocation);
			}
		} catch (IOException e) {
			throw new IOException("Não foi possível criar o diretório" + location);
		}
	}
	public URI storeFile(MultipartFile arquivo) throws IOException, URISyntaxException {
		try {
			if(arquivo.isEmpty()) {
				throw new IOException("Erro: o arquivo está vazio.");
			}
			Path destionationFile = this.rootLocation.resolve(Paths.get(arquivo.getOriginalFilename())).normalize().toAbsolutePath();
			if(!destionationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new IOException("Não é possível salvar o arquivo fora do diretório atual.");
			}
			try (InputStream inputStream = arquivo.getInputStream()){
				Files.copy(inputStream, destionationFile, StandardCopyOption.REPLACE_EXISTING);
				return new URI(destionationFile.toString());
			}
		} catch (IOException e) {
			throw new IOException("Erro ao salvar o arquivo", e);
		}
	}

}
