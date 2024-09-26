package com.backend.service;

import com.backend.model.Hotel;
import com.backend.model.HotelImage;
import com.backend.repository.HotelImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class HotelImageService {

    @Autowired
    private HotelImageRepository hotelImageRepository;

    public HotelImage createHotelImage(MultipartFile file, Hotel hotel) throws IOException {
        HotelImage hotelImage = new HotelImage();
        hotelImage.setHotel(hotel);
        hotelImage.setImage(file.getBytes());

        return hotelImageRepository.save(hotelImage);
    }

    public Optional<HotelImage> getHotelImageById(Long id) {
        return hotelImageRepository.findById(id);
    }

    public HotelImage updateHotelImage(Long id, HotelImage newHotelImage) {
        return hotelImageRepository.findById(id)
                .map(hotelImage -> {
                    hotelImage.setImage(newHotelImage.getImage());
                    return hotelImageRepository.save(hotelImage);
                })
                .orElse(null);
    }

    public void deleteHotelImage(Long id) {
        hotelImageRepository.deleteById(id);
    }
}
