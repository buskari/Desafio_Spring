package br.com.mercadolivre.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtils<T> {

	ObjectMapper mapper = new ObjectMapper();

	public List<T> readObjectsFromFile(String path) throws IOException {
		TypeReference<List<T>> typeReference = new TypeReference<List<T>>() {
		};
		File file = new File(path);
		if (file.exists()) {
			List<T> objs = mapper.readValue(new File(path), typeReference);
			return (objs);
		}
		file.createNewFile();
		return new ArrayList<>();
	}

	public void writeObjectToFile(List<T> list, String path) {
		try {
			mapper.writeValue(new File(path), list);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
