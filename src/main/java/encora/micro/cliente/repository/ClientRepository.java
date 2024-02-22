package encora.micro.cliente.repository;

import encora.micro.cliente.domain.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClienteEntity, Integer> {
}
