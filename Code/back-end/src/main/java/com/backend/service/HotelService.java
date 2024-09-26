package com.backend.service;

import com.backend.dto.HotelDto;
import com.backend.model.Hotel;
import com.backend.form.HotelCreateForm;
import com.backend.form.HotelFilterForm;
import com.backend.repository.IHotelRepository;
import com.backend.specification.HotelSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class HotelService implements  IHotelService{
    private final IHotelRepository repository;
    private final ModelMapper modelMapper;
@Autowired
    public HotelService(IHotelRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Page<HotelDto> findAll(HotelFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir) {
        Specification<Hotel> spec = HotelSpecification.buildSpec(form);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Hotel> hotelPage = repository.findAll(spec,pageable );
        return hotelPage.map(hotel -> modelMapper.map(hotel, HotelDto.class));
    }

    @Override
    public HotelDto create(HotelCreateForm form) {
        var hotel =modelMapper.map(form, Hotel.class);
        var saveHotel=repository.save(hotel);
        return  modelMapper.map(saveHotel, HotelDto.class);
    }

    @Override
    public HotelDto findById(Long id) {
        return repository.findById(id)
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .orElse(null);
    }

    @Override
    public HotelDto update(Long id, HotelCreateForm form) {
        var hotel =repository.findById(id).orElse(null);
        modelMapper.map(form,hotel);
        var saveHotel=repository.save(hotel);
        return  modelMapper.map(saveHotel, HotelDto.class);
    }

    @Override
    public void deleteById(Long id) {
    repository.deleteById(id);

    }
}
