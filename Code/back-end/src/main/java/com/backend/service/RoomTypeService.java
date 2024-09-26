package com.backend.service;

import com.backend.dto.RoomTypeDto;
import com.backend.model.RoomType;
import com.backend.form.RoomTypeCreateForm;
import com.backend.form.RoomTypeFilterForm;
import com.backend.repository.IRoomTypeRepository;
import com.backend.specification.RoomTypeSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService implements  IRoomTypeService{

    private  final IRoomTypeRepository repository;
    private  final ModelMapper modelMapper;
@Autowired
    public RoomTypeService(IRoomTypeRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<RoomTypeDto> findAll(RoomTypeFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir) {
        Specification<RoomType> spec = RoomTypeSpecification.buildSpec(form);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<RoomType> roomPage =repository.findAll(spec,pageable);
        return roomPage.map(room -> modelMapper.map(room, RoomTypeDto.class));

    }

    @Override
    public RoomTypeDto create(RoomTypeCreateForm form) {
        var roomType =modelMapper.map(form, RoomType.class);
        var saverRoomType= repository.save(roomType);
        return modelMapper.map(saverRoomType, RoomTypeDto.class);
    }

    @Override
    public RoomTypeDto findById(Long id) {
        return repository.findById(id)
                .map(room -> modelMapper.map(room, RoomTypeDto.class))
                .orElse(null);
    }

    @Override
    public RoomTypeDto update(Long id, RoomTypeCreateForm form) {
        var room =repository.findById(id).orElse(null);
        modelMapper.map(form,room);
        var saveRoom=repository.save(room);
        return  modelMapper.map(saveRoom, RoomTypeDto.class);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
