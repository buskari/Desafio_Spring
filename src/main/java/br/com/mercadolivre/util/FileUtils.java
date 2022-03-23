package br.com.mercadolivre.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtils<T> {
	
	ObjectMapper mapper = new ObjectMapper();
	
	public List<T> readObjectsFromFile(String path) throws IOException {
		TypeReference<List<T>> typeReference = new TypeReference<List<T>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream(path);
		if(inputStream.available() == 1) {
			List<T> objs = mapper.readValue(inputStream,typeReference);
			return (objs);
		}
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
