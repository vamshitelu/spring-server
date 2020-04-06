package com.serverapp.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public interface IService {

	default<I, O> void map(I source, O destination) {
		if(source == null)
			return;
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.map(source, destination);
	}
	
	default <I, O> O map(I source, Type listType){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(source, listType);
	}
	
	default <I, O> O map(I source, Class<O> clazz) {
		ModelMapper modelMapper =new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(source, clazz);
	}
	
	default <I, O> List<O> mapList(List<I> list, Class<O> clazz){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<O> oList = new ArrayList<>();
		if(list != null) {
			for(I i: list) {
				oList.add(modelMapper.map(i, clazz));
			}
		}
		return oList;
	}
}
