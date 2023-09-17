package picpaydesafiobackend.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.carteira.entity.Carteira;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Integer> {
    Optional<Carteira> findCarteiraByUser(User user);
}
