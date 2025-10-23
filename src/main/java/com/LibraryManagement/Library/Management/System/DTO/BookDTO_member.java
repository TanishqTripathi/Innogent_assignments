package com.LibraryManagement.Library.Management.System.DTO;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO_member {
    private String title;
    Set<String> memberNames;
}
