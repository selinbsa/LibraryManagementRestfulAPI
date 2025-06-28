package com.example.LibraryManagementRestfulAPI.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PublisherResponse {

    private Long id;
    private String name;
    private LocalDate establishmentYear;
}

