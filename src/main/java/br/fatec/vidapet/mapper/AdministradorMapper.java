package br.fatec.vidapet.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.fatec.vidapet.dto.AdministradorDTO;
import br.fatec.vidapet.model.Administrador;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AdministradorMapper {
	private ModelMapper modelMapper;
	
	public Administrador toEntity(AdministradorDTO objDTO) { 
		return modelMapper.map(objDTO, Administrador.class); 
	}

	public AdministradorDTO toDTO(Administrador obj) {
		return modelMapper.map(obj, AdministradorDTO.class); 
	} 

	public List<AdministradorDTO> toDTO(List<Administrador> adms) {
		return adms.stream().map(this::toDTO).collect(Collectors.toList()); 
	} 

	public List<Administrador> toEntity(List<AdministradorDTO> admsDTO){
		return admsDTO.stream().map(this::toEntity).collect(Collectors.toList()); 
	}
}
