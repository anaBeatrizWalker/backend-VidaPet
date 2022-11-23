package br.fatec.vidapet.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.fatec.vidapet.dto.ServicoDTO;
import br.fatec.vidapet.model.Servico;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ServicoMapper {
	private ModelMapper modelMapper;
	
	public Servico toEntity(ServicoDTO objDTO) { 
		return modelMapper.map(objDTO, Servico.class); 
	}

	public ServicoDTO toDTO(Servico obj) {
		return modelMapper.map(obj, ServicoDTO.class);
	} 

	public List<ServicoDTO> toDTO(List<Servico> servicos) {
		return servicos.stream().map(this::toDTO).collect(Collectors.toList()); 
	} 

	public List<Servico> toEntity(List<ServicoDTO> servicosDTO){
		return servicosDTO.stream().map(this::toEntity).collect(Collectors.toList()); 
	}
}
