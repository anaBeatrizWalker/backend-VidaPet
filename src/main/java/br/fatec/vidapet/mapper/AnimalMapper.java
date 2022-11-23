package br.fatec.vidapet.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.fatec.vidapet.dto.AnimalDTO;
import br.fatec.vidapet.model.Animal;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AnimalMapper {
	private ModelMapper modelMapper;
	
	public Animal toEntity(AnimalDTO objDTO) { 
		return modelMapper.map(objDTO, Animal.class); 
	}

	public AnimalDTO toDTO(Animal obj) {
		return modelMapper.map(obj, AnimalDTO.class);
	} 

	public List<AnimalDTO> toDTO(List<Animal> animais) {
		return animais.stream().map(this::toDTO).collect(Collectors.toList()); 
	} 

	public List<Animal> toEntity(List<AnimalDTO> animaisDTO){
		return animaisDTO.stream().map(this::toEntity).collect(Collectors.toList()); 
	}
}
