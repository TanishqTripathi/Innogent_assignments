package com.LibraryManagement.Library.Management.System.Service;

import org.springframework.stereotype.Service;

import com.LibraryManagement.Library.Management.System.DTO.BookDTOresponse;
import com.LibraryManagement.Library.Management.System.Model.Books;
import com.LibraryManagement.Library.Management.System.Model.Members;
import com.LibraryManagement.Library.Management.System.Repository.Books_dao;
import com.LibraryManagement.Library.Management.System.Repository.Members_dao;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final Members_dao memberRepo;
    private final Books_dao bookRepo;

    @Transactional
    public void borrowBookByNames(String memberName, String bookTitle) {
        Members member = memberRepo.findByMembername(memberName)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Books book = bookRepo.findByBookTitle(bookTitle)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getStock() < 1) {
            throw new RuntimeException("Book out of stock!");
        }

        book.setStock(book.getStock() - 1);

        member.getBooks().add(book);
        book.getMembers().add(member);

        memberRepo.save(member);
        bookRepo.save(book);
    }

    public BookDTOresponse getbookstock(String title) {
        Books book = bookRepo.findByBookTitle(title).orElseThrow(() -> new RuntimeException("Book Not found"));
        return new BookDTOresponse(book.getBookTitle(), book.getStock());
    }
}
