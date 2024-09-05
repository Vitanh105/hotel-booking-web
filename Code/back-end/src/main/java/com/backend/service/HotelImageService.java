package com.backend.service;

import com.backend.dto.HotelImageDto;
import com.backend.model.HotelImage;
import com.backend.form.HotelImageCreateForm;
import com.backend.repository.IHotelImageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HotelImageService implements IHotelImageService {

    @Autowired
    private IHotelImageRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void create(HotelImageCreateForm form) {
        HotelImage image = modelMapper.map(form, HotelImage.class);
        repository.save(image);

    }

    @Override
    public HotelImageDto getById(Long id) {
        return modelMapper.map(repository.findById(id).orElse(null), HotelImageDto.class);
    }

    @Override
    public void updateById(Long id, HotelImageDto form) {
        HotelImage image = modelMapper.map(form, HotelImage.class);
        image.setId(id);
        repository.save(image);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
