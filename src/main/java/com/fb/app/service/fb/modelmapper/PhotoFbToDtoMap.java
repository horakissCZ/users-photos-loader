package com.fb.app.service.fb.modelmapper;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

import com.fb.app.dto.PhotoDto;
import com.fb.app.dto.ReactionDto;
import com.fb.app.service.fb.types.PhotoWithReactionsType;
import com.restfb.types.Photo.Image;

public class PhotoFbToDtoMap extends PropertyMap<PhotoWithReactionsType, PhotoDto> {

	@Override
	protected void configure() {	
		map(source.getLink(), destination.getFbLink());
		map(source.getAlbum().getName(), destination.getAlbumName());
		using(getOriginalImgUrl)
			.map(source.getImages(), destination.getImageFileUrl());
		using(getNumOfReactionsGroupedByType)
			.map(source, destination.getReactions());
	}
	
	private Converter<List<Image>, String> getOriginalImgUrl = c -> {
		return c.getSource().stream()
				.max(Comparator.comparingInt(Image::getWidth))
				.orElse(new Image())
				.getSource();
    };
    
    private Converter<PhotoWithReactionsType, Set<ReactionDto>> getNumOfReactionsGroupedByType = c -> {
    	Set<ReactionDto> photoReactions = new HashSet<>();
    	
		photoReactions.add(new ReactionDto("none", c.getSource().getNone().getSummary().getTotalCount().intValue()));
		photoReactions.add(new ReactionDto("like", c.getSource().getLike().getSummary().getTotalCount().intValue()));
		photoReactions.add(new ReactionDto("love", c.getSource().getLove().getSummary().getTotalCount().intValue()));
		photoReactions.add(new ReactionDto("wow",  c.getSource().getWow().getSummary().getTotalCount().intValue()));
		photoReactions.add(new ReactionDto("haha", c.getSource().getHaha().getSummary().getTotalCount().intValue()));
		photoReactions.add(new ReactionDto("sad",  c.getSource().getSad().getSummary().getTotalCount().intValue()));
		photoReactions.add(new ReactionDto("angry", c.getSource().getAngry().getSummary().getTotalCount().intValue()));
		photoReactions.add(new ReactionDto("thankful", c.getSource().getThankful().getSummary().getTotalCount().intValue()));
		photoReactions.add(new ReactionDto("pride", c.getSource().getPride().getSummary().getTotalCount().intValue()));
    	photoReactions.removeIf(x -> x.getNumOfReactions() == 0);
    	
    	return photoReactions;
    };
    
}
