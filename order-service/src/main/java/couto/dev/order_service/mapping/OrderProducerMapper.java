package couto.dev.order_service.mapping;

import couto.dev.order_service.domin.OrderEntity;
import couto.dev.order_service.dto.OrderProducerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderProducerMapper {

    OrderProducerDto toEvent(OrderEntity order);

}
