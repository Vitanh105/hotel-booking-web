package com.backend.service;

import com.backend.model.Hotel;
import com.backend.model.Image;
import com.backend.model.Room;
import com.backend.model.validate.ImageDTO;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    Image saveImageToHotel (ImageDTO imageDTO, Hotel hotel);
    List<Image> saveImagesToHotel(List<ImageDTO> imageDTOs, Hotel hotel);
    Image saveImagetoRoom (ImageDTO imageDTO, Room room);
    List<Image> saveImagesToRoom(List<ImageDTO> imageDTOs, Room room);
    Optional<Image> findImageById (Long id);
    //List<Image> findImageByHotelId(Long hotelId);
    Image updateImage(ImageDTO imageDTO);
    void deleteImage (Long id);
    Image mapImageDtoToImage(ImageDTO imageDTO, Hotel hotel);
    ImageDTO mapImageToImageDto(Image image);
    Image mapImageDtoToImageRoom(ImageDTO imageDTO, Room room);
    Image addImageToHotel(Long hotelId, Image image);
    Image addImageToRoom(Long roomId, Image image);
    Optional<Image> getImageById(Long id);
    List<Image> getImageByHotel(Long hotelId);

    boolean deleteImageById(Long id);
}
