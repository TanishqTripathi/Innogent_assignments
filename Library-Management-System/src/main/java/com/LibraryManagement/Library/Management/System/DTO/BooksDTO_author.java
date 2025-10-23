package com.LibraryManagement.Library.Management.System.DTO;

import com.LibraryManagement.Library.Management.System.Model.Books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksDTO_author {
    private String title;
    private String author;
    private Long stock;

}
