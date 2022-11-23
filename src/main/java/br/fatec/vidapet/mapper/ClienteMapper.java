package br.fatec.vidapet.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.fatec.vidapet.dto.ClienteDTO;
import br.fatec.vidapet.model.Cliente;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ClienteMapper {
	private ModelMapper modelMapper;
	
	public Cliente toEntity(ClienteDTO objDTO) { 
		return modelMapper.map(objDTO, Cliente.class); 
	}

	public ClienteDTO toDTO(Cliente obj) {
		return modelMapper.map(obj, ClienteDTO.class);
	} 

	public List<ClienteDTO> toDTO(List<Cliente> clientes) {
		return clientes.stream().map(this::toDTO).collect(Collectors.toList()); 
	} 

	public List<Cliente> toEntity(List<ClienteDTO> clientesDTO){
		return clientesDTO.stream().map(this::toEntity).collect(Collectors.toList()); 
	}
}
