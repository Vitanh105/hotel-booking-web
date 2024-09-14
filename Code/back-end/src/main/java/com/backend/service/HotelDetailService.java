package com.backend.service;

import com.backend.dto.HotelDetailDto;

import com.backend.entity.Hotel;
import com.backend.entity.HotelDetail;
import com.backend.form.HotelDetailCreateForm;
import com.backend.form.HotelDetailFilterForm;
import com.backend.repository.IHotelDetailRepository;
import com.backend.specification.HotelDetailSpecification;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service

public class HotelDetailService implements IHotelDetailService {
    @Autowired
    public HotelDetailService(IHotelDetailRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    private final IHotelDetailRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public Page<HotelDetailDto> findAll(HotelDetailFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir) {
        Specification<Hotel> specification = HotelDetailSpecification.buildSpec(form);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<HotelDetail> hotelDetailPage = repository.findAll(specification, pageable);
        return hotelDetailPage.map(hotelDetail -> modelMapper.map(hotelDetail, HotelDetailDto.class));

    }

    @Override
    public HotelDetailDto create(HotelDetailCreateForm form) {
        var hotelDetail = modelMapper.map(form, HotelDetail.class);
        var saveHotelDetail = repository.save(hotelDetail);
        return modelMapper.map(saveHotelDetail, HotelDetailDto.class);
    }

    @Override
    public HotelDetailDto findById(Long id) {
        return repository.findById(id)
                .map(hotelDetail -> modelMapper.map(hotelDetail, HotelDetailDto.class))
                .orElse(null);
    }

    @Override
    public HotelDetailDto update(Long id, HotelDetailCreateForm form) {
        var hotelDetail = repository.findById(id).orElse(null);
        modelMapper.map(form, hotelDetail);
        var saveHotelDetail = repository.save(hotelDetail);
        return modelMapper.map(saveHotelDetail, HotelDetailDto.class);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
