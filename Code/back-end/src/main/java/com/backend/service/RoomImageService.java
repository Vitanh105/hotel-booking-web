package com.backend.service;

import com.backend.dto.RoomDto;
import com.backend.dto.RoomImageDto;
import com.backend.entity.Room;
import com.backend.entity.RoomImage;
import com.backend.form.RoomCreateForm;
import com.backend.form.RoomFilterForm;
import com.backend.form.RoomImageCreateForm;
import com.backend.form.RoomImageFilterForm;
import com.backend.repository.IRoomImageRepository;
import com.backend.specification.RoomImageSpecification;
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
public class RoomImageService implements IRoomImageService {
  private  final IRoomImageRepository repository;
  private final ModelMapper modelMapper;
@Autowired
    public RoomImageService(IRoomImageRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<RoomImageDto> findAll(RoomImageFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir) {
        Specification<RoomImage> spec = RoomImageSpecification.buildSpec(form);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<RoomImage> roomImagePage =repository.findAll(spec,pageable);
        return roomImagePage.map(roomImage -> modelMapper.map(roomImage, RoomImageDto.class));
    }

    @Override
    public RoomImageDto create(RoomImageCreateForm form) {
        var roomImage =modelMapper.map(form, RoomImage.class);
        var saveRoomImage=repository.save(roomImage);
        return  modelMapper.map(saveRoomImage, RoomImageDto.class);
    }

    @Override
    public RoomImageDto findById(Long id) {
        return repository.findById(id)
                .map(room -> modelMapper.map(room, RoomImageDto.class))
                .orElse(null);
    }

    @Override
    public RoomImageDto update(Long id, RoomImageCreateForm form) {
        var roomImage =repository.findById(id).orElse(null);
        modelMapper.map(form,roomImage);
        var saveRoomImage=repository.save(roomImage);
        return  modelMapper.map(saveRoomImage, RoomImageDto.class);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }
}
