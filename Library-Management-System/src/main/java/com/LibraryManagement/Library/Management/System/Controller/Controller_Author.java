package com.LibraryManagement.Library.Management.System.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LibraryManagement.Library.Management.System.DTO.AuthorDTO;
import com.LibraryManagement.Library.Management.System.Model.Author;
import com.LibraryManagement.Library.Management.System.Service.Author_service;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class Controller_Author {

    private final Author_service authorService;

    @PostMapping("/add")
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorDTO dto) {
        System.out.println("Swagger raw DTO => " + dto);
        AuthorDTO savedAuthorDTO = authorService.addAuthor(dto);
        return ResponseEntity.ok(savedAuthorDTO);
    }

    @GetMapping("/getall")
    public ResponseEntity<Set<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }
}
