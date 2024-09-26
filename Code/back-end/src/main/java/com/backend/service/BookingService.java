package com.backend.service;

import com.backend.dto.BookingDto;
import com.backend.model.Booking;
import com.backend.form.BookingCreateForm;
import com.backend.form.BookingFilterForm;
import com.backend.repository.IBookingRepository;
import com.backend.specification.BookingSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service

public class BookingService implements IBookingService {

    private  final IBookingRepository repository;
    private  final ModelMapper modelMapper;
@Autowired
    public BookingService(IBookingRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<BookingDto> findAll(BookingFilterForm form, int pageNo, int pageSize, String sortBy, String sortDir) {
        Specification<Booking> spec = BookingSpecification.buildSpec(form);
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Booking> bookingPage = repository.findAll(spec,pageable );
        return bookingPage.map(booking -> modelMapper.map(booking, BookingDto.class));
    }


    @Override
    public BookingDto create(BookingCreateForm form) {
        var booking =modelMapper.map(form, Booking.class);
        var saveBooking=repository.save(booking);
        return  modelMapper.map(saveBooking, BookingDto.class);
    }

    @Override
    public BookingDto findById(Long id) {
        return repository.findById(id)
                .map(booking -> modelMapper.map(booking,BookingDto.class))
                .orElse(null);
    }

    @Override
    public BookingDto update(Long id, BookingCreateForm form) {
        var booking =repository.findById(id).orElse(null);
        modelMapper.map(form,booking);
        var saverBooking=repository.save(booking);
        return  modelMapper.map(saverBooking, BookingDto.class);
    }

    @Override
    public void deleteById(Long id) {
    repository.deleteById(id);

    }
}
