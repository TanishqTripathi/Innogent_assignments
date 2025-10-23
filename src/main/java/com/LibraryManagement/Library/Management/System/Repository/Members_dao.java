package com.LibraryManagement.Library.Management.System.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LibraryManagement.Library.Management.System.Model.Members;

public interface Members_dao extends JpaRepository<Members, Long> {
    Optional<Members> findByMembername(String name);
}
