package couto.dev.estoque_service.mapper;

import couto.dev.estoque_service.database.domin.EstoqueEntity;
import couto.dev.estoque_service.dto.EstoqueResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstoqueMapper {


    EstoqueResponseDto toDto(EstoqueEntity estoque);
}
