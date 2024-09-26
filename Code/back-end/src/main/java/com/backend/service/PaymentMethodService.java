package com.backend.service;

import com.backend.dto.PaymentMethodDto;
import com.backend.model.PaymentMethod;
import com.backend.form.PaymentMethodCreateForm;
import com.backend.repository.IPaymentMethodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service

public class PaymentMethodService implements IPaymentMethodService{

    private  final IPaymentMethodRepository repository;
    private final ModelMapper modelMapper;
@Autowired
    public PaymentMethodService(IPaymentMethodRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<PaymentMethodDto> getAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<PaymentMethod> paymentMethodPage = repository.findAll(pageable);
        return paymentMethodPage.map(paymentMethod -> modelMapper.map(paymentMethod, PaymentMethodDto.class));
    }

    @Override
    public PaymentMethodDto create(PaymentMethodCreateForm form) {
        var paymentMethod =modelMapper.map(form, PaymentMethod.class);
        var savePaymentMethod=repository.save(paymentMethod);
        return  modelMapper.map(savePaymentMethod, PaymentMethodDto.class);
    }

    @Override
    public PaymentMethodDto findById(Long id) {
        return repository.findById(id)
                .map(paymentMethod -> modelMapper.map(paymentMethod, PaymentMethodDto.class))
                .orElse(null);
    }

    @Override
    public PaymentMethodDto update(Long id, PaymentMethodCreateForm form) {
        var paymentMethod =repository.findById(id).orElse(null);
        modelMapper.map(form,paymentMethod);
        var savePaymentMethod=repository.save(paymentMethod);
        return  modelMapper.map(savePaymentMethod, PaymentMethodDto.class);
    }


    @Override
    public void deleteById(Long id) {
    repository.deleteById(id);

    }
}
