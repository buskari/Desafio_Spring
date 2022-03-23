package br.com.mercadolivre.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtils<T> {
	
	ObjectMapper mapper = new ObjectMapper();
	
	public List<T> readObjectsFromFile(String path) throws IOException {
		TypeReference<List<T>> typeReference = new TypeReference<List<T>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream(path);
			List<T> objs = mapper.readValue(inputStream,typeReference);
			return (objs);
	}
	
	public void writeObjectToFile(T object, String path) {
        try {
        	ObjectMapper objectMapper = new ObjectMapper();
        	objectMapper.writeValue(new File(path), object);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
