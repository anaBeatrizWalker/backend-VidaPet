package br.fatec.vidapet.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.fatec.vidapet.dto.FuncionarioDTO;
import br.fatec.vidapet.model.Funcionario;

public class FuncionarioMapper {
	private ModelMapper modelMapper;
	
	public Funcionario toEntity(FuncionarioDTO objDTO) { 
		return modelMapper.map(objDTO, Funcionario.class); 
	}

	public FuncionarioDTO toDTO(Funcionario obj) {
		return modelMapper.map(obj, FuncionarioDTO.class); 
	} 

	public List<FuncionarioDTO> toDTO(List<Funcionario> funcionarios) {
		return funcionarios.stream().map(this::toDTO).collect(Collectors.toList()); 
	} 

	public List<Funcionario> toEntity(List<FuncionarioDTO> funcionariosDTO){
		return funcionariosDTO.stream().map(this::toEntity).collect(Collectors.toList()); 
	}

}
