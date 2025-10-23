package com.LibraryManagement.Library.Management.System.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LibraryManagement.Library.Management.System.DTO.MemberDTO;
import com.LibraryManagement.Library.Management.System.DTO.Member_onlyDTO;
import com.LibraryManagement.Library.Management.System.Model.Members;
import com.LibraryManagement.Library.Management.System.Service.Members_service;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class Controller_Members {

    private final Members_service membersService;

    @PutMapping("/add")
    public ResponseEntity<Members> addMember(MemberDTO memberDTO) {
        return ResponseEntity.ok(membersService.addmembers(memberDTO));
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member_onlyDTO>> getAllMembers() {
        return ResponseEntity.ok(membersService.getAll());
    }

}
