package com.backend.service;

import com.backend.model.Address;
import com.backend.model.validate.AddressDTO;

public interface AddressService {
    Address saveAddress (AddressDTO addressDTO);

    AddressDTO findAddressById(Long id);
    Address updateAddress(AddressDTO addressDTO);
    void deleteAddress(Long id);
    Address mapAddressDtoToAddress(AddressDTO dto);
    AddressDTO mapAddressToAddressDto(Address address);
}
