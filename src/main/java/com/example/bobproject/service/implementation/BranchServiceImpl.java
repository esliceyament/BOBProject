package com.example.bobproject.service.implementation;

import com.example.bobproject.entity.Branch;
import com.example.bobproject.mapper.BranchDTOMapper;
import com.example.bobproject.model.requestDTO.BranchReqDTO;
import com.example.bobproject.model.responseDTO.BranchRespDTO;
import com.example.bobproject.repository.BranchRepository;
import com.example.bobproject.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public List<BranchRespDTO> getBranch() {
        return branchRepository.findAll().stream().map(BranchDTOMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    public void addBranch(BranchReqDTO branchReqDTO) {
        branchRepository.save(BranchDTOMapper.INSTANCE.toEntity(branchReqDTO));
    }

    public void deleteBranch(Long id) {
        Optional<Branch> branchOptional = branchRepository.findById(id);

        if (branchOptional.isEmpty()) {
            throw new IllegalStateException("Does not exist");
        }
        branchRepository.delete(branchOptional.get());

    }
}
