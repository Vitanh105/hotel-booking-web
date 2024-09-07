package com.backend.service;

import com.backend.dto.HotelDto;
import com.backend.dto.RoomDto;
import com.backend.entity.Hotel;
import com.backend.entity.Room;
import com.backend.form.RoomCreateForm;
import com.backend.form.RoomFilterForm;
import com.backend.repository.IRoomRepository;

import com.backend.specification.RoomSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class RoomService  implements  IRoomService{
    private  final IRoomRepository repository;
    private final ModelMapper modelMapper;
@Autowired
    public RoomService(IRoomRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<RoomDto> findAll(RoomFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir) {
        Specification<Room> spec = RoomSpecification.buildSpec(form);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Room> roomPage =repository.findAll(spec,pageable);
        return roomPage.map(room -> modelMapper.map(room, RoomDto.class));

}

    @Override
    public RoomDto create(RoomCreateForm form) {
        var room =modelMapper.map(form, Room.class);
        var saveRoom=repository.save(room);
        return  modelMapper.map(saveRoom, RoomDto.class);
    }

    @Override
    public RoomDto findById(Long id) {
        return repository.findById(id)
                .map(room -> modelMapper.map(room, RoomDto.class))
                .orElse(null);
    }

    @Override
    public RoomDto update(Long id, RoomCreateForm form) {
        var room =repository.findById(id).orElse(null);
        modelMapper.map(form,room);
        var saveRoom=repository.save(room);
        return  modelMapper.map(saveRoom, RoomDto.class);
    }

    @Override
    public void deleteById(Long id) {
    repository.deleteById(id);

    }
}
