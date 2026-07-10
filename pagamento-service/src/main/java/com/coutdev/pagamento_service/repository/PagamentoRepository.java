package com.coutdev.pagamento_service.repository;

import com.coutdev.pagamento_service.domin.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity,Integer> {
}
