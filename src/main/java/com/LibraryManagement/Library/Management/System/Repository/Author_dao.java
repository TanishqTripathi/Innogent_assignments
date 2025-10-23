package com.LibraryManagement.Library.Management.System.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LibraryManagement.Library.Management.System.Model.Author;

public interface Author_dao extends JpaRepository<Author, Long> {

    Optional<Author> findByAuthorname(String authorname);

}
