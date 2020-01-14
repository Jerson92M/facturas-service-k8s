package com.facturacion.utils;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

public class Comunes {
	
	private static Comunes comunes;
	
	private Comunes() {
		
	}
	
	public static Comunes getComunes() {
		
		if (comunes == null) {
			comunes = new Comunes();
		}
		
		return comunes;
	}

	public String convertirBase64(MultipartFile imagen) throws IOException {
		
		String encoded64 = "";
		
		if (imagen != null) {
			
			byte[] foto = imagen.getBytes();
			
			 encoded64 = Base64.getEncoder().encodeToString(foto);
			
		}
		
		return encoded64;
	}
}
