package br.fatec.vidapet.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.fatec.vidapet.dto.AgendamentoDTO;
import br.fatec.vidapet.model.Agendamento;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AgendamentoMapper {
	
	private ModelMapper modelMapper;
	
	public Agendamento toEntity(AgendamentoDTO objDTO) { 
		return modelMapper.map(objDTO, Agendamento.class); 
	}

	public AgendamentoDTO toDTO(Agendamento obj) {
		return modelMapper.map(obj, AgendamentoDTO.class); 
	} 

	public List<AgendamentoDTO> toDTO(List<Agendamento> agendamentos) {
		return agendamentos.stream().map(this::toDTO).collect(Collectors.toList()); 
	} 

	public List<Agendamento> toEntity(List<AgendamentoDTO> agendamentosDTO){
		return agendamentosDTO.stream().map(this::toEntity).collect(Collectors.toList()); 
	}
}
