package couto.dev.order_service.mapping;

import couto.dev.order_service.domin.OrderEntity;
import couto.dev.order_service.dto.OrderProducerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderProducerMapper {

    @Mapping(source = "order.id",target ="OrderId")
    OrderProducerDto toEvent(OrderEntity order);

}
