package com.example.bobproject.mapper;

import com.example.bobproject.entity.Client;
import com.example.bobproject.model.requestDTO.ClientReqDTO;
import com.example.bobproject.model.responseDTO.ClientRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class ClientDTOMapper {
    public static ClientDTOMapper INSTANCE = Mappers.getMapper(ClientDTOMapper.class);
    public abstract ClientRespDTO toDTO(Client client);
    public abstract Client toEntity(ClientReqDTO clientReqDTO);
}
