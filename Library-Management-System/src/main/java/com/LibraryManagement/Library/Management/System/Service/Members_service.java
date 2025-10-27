package com.LibraryManagement.Library.Management.System.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LibraryManagement.Library.Management.System.DTO.MemberDTO;
import com.LibraryManagement.Library.Management.System.DTO.Member_onlyDTO;
import com.LibraryManagement.Library.Management.System.Model.Members;
import com.LibraryManagement.Library.Management.System.Repository.Members_dao;

@Service
public class Members_service {
    @Autowired
    private Members_dao membersDao;

    public Members addmembers(MemberDTO memberDTO) {
        Members member = new Members();
        member.setMembername(memberDTO.getMemberName());
        return membersDao.save(member);
    }

    public List<Member_onlyDTO> getAll() {
        return membersDao.findAll()
                .stream()
                .map(member -> new Member_onlyDTO(member.getMembername()))
                .collect(Collectors.toList());
    }
}
