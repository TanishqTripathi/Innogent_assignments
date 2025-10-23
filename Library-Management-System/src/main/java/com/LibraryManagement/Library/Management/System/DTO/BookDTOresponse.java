package com.LibraryManagement.Library.Management.System.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTOresponse {
    private String title;
    private Long stock;
}
