package com.example.LibraryManagementRestfulAPI.service.interfaces;

import com.example.LibraryManagementRestfulAPI.dto.request.PublisherSaveRequest;
import com.example.LibraryManagementRestfulAPI.dto.response.PublisherResponse;

import java.util.List;

public interface IPublisherService {
    PublisherResponse save(PublisherSaveRequest request);
    List<PublisherResponse> getAll();
    PublisherResponse getById(Long id);
    PublisherResponse update(Long id, PublisherSaveRequest request);
    void delete(Long id);
}
