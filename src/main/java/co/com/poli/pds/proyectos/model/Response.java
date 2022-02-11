package co.com.poli.pds.proyectos.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
	
	private Integer statusCode;
	private String message;
	private Object data;
	
}
