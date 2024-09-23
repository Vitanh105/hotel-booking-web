package com.backend.service;

import com.backend.model.Room;
import com.backend.model.Service;
import com.backend.model.validate.ServiceDTO;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    Service saveServiceToRoom (ServiceDTO serciceDTO, Room room);
    List<Service> saveServices(List<ServiceDTO> serviceDTOs, Room room);
    Optional<Service> findServiceById (Long id);
    Service updateService(ServiceDTO serviceDTO);
    void deleteService (Long id);
    Service mapServiceDtoToService(ServiceDTO serviceDTO, Room room);
    ServiceDTO mapServiceToServiceDto(Service service);
    

    
}