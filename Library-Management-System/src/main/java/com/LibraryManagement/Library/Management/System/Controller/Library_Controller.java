package com.LibraryManagement.Library.Management.System.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.LibraryManagement.Library.Management.System.DTO.BookDTOresponse;
import com.LibraryManagement.Library.Management.System.DTO.BorrowRequest;
import com.LibraryManagement.Library.Management.System.Service.LibraryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class Library_Controller {

    private final LibraryService libraryService;

    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestBody BorrowRequest request) {
        libraryService.borrowBookByNames(request.getMemberName(), request.getBookTitle());
        return ResponseEntity.ok("Book borrowed successfully!");
    }

    @GetMapping("/book-stock")
    public ResponseEntity<BookDTOresponse> BookStock(@RequestParam String title) {
        BookDTOresponse bookStock = libraryService.getbookstock(title);
        return ResponseEntity.ok(bookStock);
    }
}
