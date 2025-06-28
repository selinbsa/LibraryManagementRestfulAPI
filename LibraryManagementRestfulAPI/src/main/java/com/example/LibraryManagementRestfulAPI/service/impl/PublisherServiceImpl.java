package com.example.LibraryManagementRestfulAPI.service.impl;

import com.example.LibraryManagementRestfulAPI.core.exception.NotFoundException;
import com.example.LibraryManagementRestfulAPI.dto.request.PublisherSaveRequest;
import com.example.LibraryManagementRestfulAPI.dto.response.PublisherResponse;
import com.example.LibraryManagementRestfulAPI.entity.Publisher;
import com.example.LibraryManagementRestfulAPI.repository.PublisherRepository;
import com.example.LibraryManagementRestfulAPI.service.interfaces.IPublisherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements IPublisherService {

    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;

    @Override
    public PublisherResponse save(PublisherSaveRequest request) {
        Publisher publisher = modelMapper.map(request, Publisher.class);
        return modelMapper.map(publisherRepository.save(publisher), PublisherResponse.class);
    }

    @Override
    public List<PublisherResponse> getAll() {
        return publisherRepository.findAll().stream()
                .map(p -> modelMapper.map(p, PublisherResponse.class))
                .toList();
    }

    @Override
    public PublisherResponse getById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Yayınevi bulunamadı: " + id));
        return modelMapper.map(publisher, PublisherResponse.class);
    }

    @Override
    public PublisherResponse update(Long id, PublisherSaveRequest request) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Yayınevi bulunamadı: " + id));

        publisher.setName(request.getName());
        publisher.setEstablishmentYear(request.getEstablishmentYear());
        publisher.setAddress(request.getAddress());

        return modelMapper.map(publisherRepository.save(publisher), PublisherResponse.class);
    }

    @Override
    public void delete(Long id) {
        if (!publisherRepository.existsById(id)) {
            throw new NotFoundException("Yayınevi bulunamadı: " + id);
        }
        publisherRepository.deleteById(id);
    }
}
