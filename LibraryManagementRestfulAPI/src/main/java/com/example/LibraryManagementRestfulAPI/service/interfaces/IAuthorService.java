package com.example.LibraryManagementRestfulAPI.service.interfaces;

import com.example.LibraryManagementRestfulAPI.dto.request.AuthorSaveRequest;
import com.example.LibraryManagementRestfulAPI.dto.response.AuthorResponse;

import java.util.List;

public interface IAuthorService {
    AuthorResponse save(AuthorSaveRequest request);
    List<AuthorResponse> getAll();
    AuthorResponse getById(Long id);
    AuthorResponse update(Long id, AuthorSaveRequest request);
    void delete(Long id);
}

