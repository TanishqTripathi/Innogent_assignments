package com.LibraryManagement.Library.Management.System.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LibraryManagement.Library.Management.System.DTO.BookDTO;
import com.LibraryManagement.Library.Management.System.DTO.BookDTO_member;
import com.LibraryManagement.Library.Management.System.DTO.BooksDTO_author;
import com.LibraryManagement.Library.Management.System.Model.Books;
import com.LibraryManagement.Library.Management.System.Service.Books_service;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class Controller_books {

    private final Books_service booksService;

    @PostMapping("/add")
    public ResponseEntity<BooksDTO_author> addBook(@RequestBody BooksDTO_author dto) {
        return ResponseEntity.ok(booksService.addBook(dto));
    }

    @GetMapping("/with_members")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(booksService.getAllBooks());
    }
}
