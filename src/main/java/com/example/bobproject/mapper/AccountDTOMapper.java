package com.example.bobproject.mapper;

import com.example.bobproject.entity.Account;
import com.example.bobproject.model.requestDTO.AccountReqDTO;
import com.example.bobproject.model.responseDTO.AccountRespDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class AccountDTOMapper {

    public static AccountDTOMapper INSTANCE = Mappers.getMapper(AccountDTOMapper.class);
    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "client.id", target = "clientId")
    public abstract AccountRespDTO toDTO(Account account);

    @Mapping(source = "branchId", target = "branch.id")
    @Mapping(source = "clientId", target = "client.id")
    public abstract Account toEntity(AccountReqDTO accountReqDTO);
}

