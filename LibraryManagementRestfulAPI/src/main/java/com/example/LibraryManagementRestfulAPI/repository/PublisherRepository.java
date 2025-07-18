package com.example.LibraryManagementRestfulAPI.repository;

import com.example.LibraryManagementRestfulAPI.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}

