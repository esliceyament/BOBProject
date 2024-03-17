package com.example.bobproject.controller;

import com.example.bobproject.model.requestDTO.BranchReqDTO;
import com.example.bobproject.model.responseDTO.BranchRespDTO;
import com.example.bobproject.service.implementation.BranchServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchServiceImpl branchService;
    private final Logger LOGGER = LoggerFactory.getLogger(BranchController.class);

    @GetMapping
    public List<BranchRespDTO> getBranch() {
        LOGGER.info("Inside getBranch of BranchApi");
        return branchService.getBranch();
    }

    @PostMapping()
    public void addBranch(@Valid @RequestBody BranchReqDTO branchReqDTO) {
        LOGGER.info("Inside addBranch of BranchApi");
        branchService.addBranch(branchReqDTO);
    }

    @DeleteMapping(path="{branchId}")
    public void deleteBranch(@PathVariable("branchId") Long branchId) {
        LOGGER.info("Inside deleteBranch of BranchApi");
        branchService.deleteBranch(branchId);
    }
}
