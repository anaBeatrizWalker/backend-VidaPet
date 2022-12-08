package br.fatec.vidapet.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Email
	private String to;
	private String subject;
	private String text;
}
