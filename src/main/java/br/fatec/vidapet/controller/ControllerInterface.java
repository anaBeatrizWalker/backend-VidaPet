package br.fatec.vidapet.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ControllerInterface<T> {
	ResponseEntity<List<T>> getAll();
	ResponseEntity<?> getOne(Long id);
	ResponseEntity<T> post(T obj)throws URISyntaxException;
	ResponseEntity<?> put(T obj);
	ResponseEntity<?> delete(Long id);
}
