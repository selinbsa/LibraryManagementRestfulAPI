package com.example.LibraryManagementRestfulAPI.service.impl;

import com.example.LibraryManagementRestfulAPI.core.exception.NotFoundException;
import com.example.LibraryManagementRestfulAPI.dto.request.AuthorSaveRequest;
import com.example.LibraryManagementRestfulAPI.dto.response.AuthorResponse;
import com.example.LibraryManagementRestfulAPI.entity.Author;
import com.example.LibraryManagementRestfulAPI.repository.AuthorRepository;
import com.example.LibraryManagementRestfulAPI.service.interfaces.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    public AuthorResponse save(AuthorSaveRequest request) {
        Author author = modelMapper.map(request, Author.class);
        return modelMapper.map(authorRepository.save(author), AuthorResponse.class);
    }

    @Override
    public List<AuthorResponse> getAll() {
        return authorRepository.findAll().stream()
                .map(author -> modelMapper.map(author, AuthorResponse.class))
                .toList();
    }

    @Override
    public AuthorResponse getById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Yazar bulunamadı: " + id));
        return modelMapper.map(author, AuthorResponse.class);
    }

    @Override
    public AuthorResponse update(Long id, AuthorSaveRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Yazar bulunamadı: " + id));

        author.setName(request.getName());
        author.setBirthDate(request.getBirthDate());
        author.setCountry(request.getCountry());

        return modelMapper.map(authorRepository.save(author), AuthorResponse.class);
    }

    @Override
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException("Yazar bulunamadı: " + id);
        }
        authorRepository.deleteById(id);
    }
}