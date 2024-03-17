package com.example.bobproject.mapper;

import com.example.bobproject.entity.Branch;
import com.example.bobproject.model.requestDTO.BranchReqDTO;
import com.example.bobproject.model.responseDTO.BranchRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class BranchDTOMapper {
    public static BranchDTOMapper INSTANCE = Mappers.getMapper(BranchDTOMapper.class);
    public abstract BranchRespDTO toDTO(Branch branch);
    public abstract Branch toEntity(BranchReqDTO branchReqDTO);
}
