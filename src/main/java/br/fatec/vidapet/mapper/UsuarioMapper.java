package br.fatec.vidapet.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.fatec.vidapet.dto.UsuarioDTO;
import br.fatec.vidapet.model.Usuario;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UsuarioMapper {
	private ModelMapper modelMapper;
	
	public Usuario toEntity(UsuarioDTO objDTO) { 
		return modelMapper.map(objDTO, Usuario.class); 
	}

	public UsuarioDTO toDTO(Usuario obj) {
		return modelMapper.map(obj, UsuarioDTO.class);
	} 

	public List<UsuarioDTO> toDTO(List<Usuario> usuarios) {
		return usuarios.stream().map(this::toDTO).collect(Collectors.toList()); 
	} 

	public List<Usuario> toEntity(List<UsuarioDTO> usuariosDTO){
		return usuariosDTO.stream().map(this::toEntity).collect(Collectors.toList()); 
	}
}
