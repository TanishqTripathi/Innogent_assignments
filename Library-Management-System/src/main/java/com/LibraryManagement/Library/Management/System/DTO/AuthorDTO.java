package com.LibraryManagement.Library.Management.System.DTO;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    @NotNull
    private String authorname;
    @NotNull
    private Set<String> books = new HashSet<>();
}
