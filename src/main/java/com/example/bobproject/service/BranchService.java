package com.example.bobproject.service;

import com.example.bobproject.model.requestDTO.BranchReqDTO;
import com.example.bobproject.model.responseDTO.BranchRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BranchService {
    List<BranchRespDTO> getBranch();
    void addBranch(BranchReqDTO branchReqDTO);
    void deleteBranch(Long id);

}