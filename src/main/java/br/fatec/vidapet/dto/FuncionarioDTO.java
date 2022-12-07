package br.fatec.vidapet.dto;

import javax.validation.constraints.NotNull;

import br.fatec.vidapet.model.Servico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FuncionarioDTO extends UsuarioDTO {
	@NotNull
	private Servico servico;
}
