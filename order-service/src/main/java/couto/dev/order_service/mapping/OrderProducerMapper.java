package couto.dev.order_service.mapping;

import couto.dev.order_service.domin.OrderEntity;
import couto.dev.order_service.dto.OrderProducerDto;
import couto.dev.order_service.dto.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderProducerMapper {

    @Mapping(source = "id",target ="orderId")
    OrderProducerDto toEvent(OrderEntity order);

    OrderResponseDto toDto(OrderEntity order);

}
