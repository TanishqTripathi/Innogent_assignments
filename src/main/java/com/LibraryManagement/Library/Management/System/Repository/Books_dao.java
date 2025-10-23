package com.LibraryManagement.Library.Management.System.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LibraryManagement.Library.Management.System.Model.Books;

public interface Books_dao extends JpaRepository<Books, Long> {

    Optional<Books> findByBookTitle(String bookTitle);

}
