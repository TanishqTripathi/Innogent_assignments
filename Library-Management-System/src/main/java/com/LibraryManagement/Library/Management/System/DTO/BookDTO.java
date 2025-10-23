package com.LibraryManagement.Library.Management.System.DTO;

import com.LibraryManagement.Library.Management.System.Model.Books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String title;
    private String author;
    private Long stock;

    public BookDTO(Books book) {
        this.title = book.getBookTitle();
        this.author = book.getAuthors() != null ? book.getAuthors().getAuthorname() : null;
        this.stock = book.getStock();
    }
}
