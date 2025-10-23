package com.LibraryManagement.Library.Management.System.Model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;
    private String membername;

    @ManyToMany
    @JoinTable(name = "member_books", joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "member_id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "bookid"))
    @EqualsAndHashCode.Exclude
    private Set<Books> books = new HashSet<>();;

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }
}
