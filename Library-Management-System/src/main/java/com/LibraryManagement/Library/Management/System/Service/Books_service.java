package com.LibraryManagement.Library.Management.System.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.LibraryManagement.Library.Management.System.DTO.BookDTO;
import com.LibraryManagement.Library.Management.System.DTO.BookDTO_member;
import com.LibraryManagement.Library.Management.System.DTO.BooksDTO_author;
import com.LibraryManagement.Library.Management.System.Model.Author;
import com.LibraryManagement.Library.Management.System.Model.Books;
import com.LibraryManagement.Library.Management.System.Model.Members;
import com.LibraryManagement.Library.Management.System.Repository.Author_dao;
import com.LibraryManagement.Library.Management.System.Repository.Books_dao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Books_service {

    private final Books_dao bookrepo;
    private final Author_dao authorDao;

    public BooksDTO_author addBook(BooksDTO_author dto) {
        Author author = authorDao.findByAuthorname(dto.getAuthor())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Books book = new Books();
        book.setBookTitle(dto.getTitle());
        book.setStock(dto.getStock());
        book.setAuthors(author);
        Books savedBook = bookrepo.save(book);

        return new BooksDTO_author(
                savedBook.getBookTitle(),
                savedBook.getAuthors().getAuthorname(),
                savedBook.getStock());
    }

    public List<BookDTO> getAllBooks() {
        return bookrepo.findAll().stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
    }

    public List<BookDTO_member> getAllBooksWithMembers() {
        List<Books> books = bookrepo.findAll();

        return books.stream().map(book -> {
            BookDTO_member dto = new BookDTO_member();
            dto.setTitle(book.getBookTitle());

            if (book.getMembers() != null && !book.getMembers().isEmpty()) {
                Set<String> memberNames = book.getMembers().stream()
                        .map(Members::getMembername)
                        .collect(Collectors.toSet());
                dto.setMemberNames(memberNames);
            }

            return dto;
        }).toList();
    }

}
