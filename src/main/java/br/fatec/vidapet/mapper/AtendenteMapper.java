package br.fatec.vidapet.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.fatec.vidapet.dto.AtendenteDTO;
import br.fatec.vidapet.model.Atendente;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AtendenteMapper {
	private ModelMapper modelMapper;
	
	public Atendente toEntity(AtendenteDTO objDTO) { 
		return modelMapper.map(objDTO, Atendente.class); 
	}

	public AtendenteDTO toDTO(Atendente obj) {
		return modelMapper.map(obj, AtendenteDTO.class); 
	} 

	public List<AtendenteDTO> toDTO(List<Atendente> atendente) {
		return atendente.stream().map(this::toDTO).collect(Collectors.toList()); 
	} 

	public List<Atendente> toEntity(List<AtendenteDTO> atendenteDTO){
		return atendenteDTO.stream().map(this::toEntity).collect(Collectors.toList()); 
	}

}
