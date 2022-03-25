package br.com.mercadolivre.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.mercadolivre.util.FileUtils;

@Service
public class FileService<T> {

	/**
	 * Faz a leitura do arquivo json de dados
	 * @param path arquivo json
	 * @return uma lista de objetos genericos lidos do json
	 * @throws IOException exceção no caso de não conseguir ler a lista json
	 */
	public List<T> findAll(String path) throws IOException {
		FileUtils<T> fileUtils = new FileUtils<>();
		return fileUtils.readObjectsFromFile(path);
	}
}
