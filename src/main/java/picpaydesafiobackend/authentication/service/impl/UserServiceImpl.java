package picpaydesafiobackend.authentication.service.impl;

import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.payload.request.UserRequest;
import picpaydesafiobackend.authentication.repository.UserRepository;
import picpaydesafiobackend.authentication.service.UserService;
import picpaydesafiobackend.carteira.entity.Carteira;
import picpaydesafiobackend.common.entity.Pessoa;
import picpaydesafiobackend.common.service.PessoaService;
import picpaydesafiobackend.common.utils.CustomBuilders;
import picpaydesafiobackend.common.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomBuilders customBuilders;
    private final PessoaService pessoaService;
    @Override
    public User createUser(UserRequest userRequest) throws UserException {
        validateAccount(userRequest);
        checkIfEmailAlreadyExists(userRequest.getEmail());

        String encryptedPassword = PasswordUtil.criptografaToBCrypt(userRequest.getPassword());

        User user = customBuilders.buildUser(userRequest, encryptedPassword);

        Pessoa pessoa = pessoaService.createPessoa(userRequest.getPessoa(), user);
        Carteira carteira = customBuilders.buildCarteira(user);

        user.setCarteira(carteira);
        user.setPessoa(pessoa);

        return userRepository.save(user);
    }

    private void validateAccount(UserRequest userRequest) throws UserException {
        if (userRequest.getEmail() == null || userRequest.getPassword() == null) {
            throw new UserException("Email ou senha são obrigatórios.");
        }
    }

    private void checkIfEmailAlreadyExists(String email) throws UserException {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            throw new UserException("Email já cadastrado.");
        }
    }

    public void validateUser(String userId) throws UserException {
        Optional<User> userOptional = userRepository.findById(userId);
        if ((userOptional.isEmpty())) {
            throw new UserException("Usuário não encontrado na base de dados.");
        }
    }

    @Override
    public User findUserById(String id) throws UserException {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new UserException("Usuário não encontrado na base de dados."));
    }

    @Override
    public User findUserByEmail(String email) throws UserException {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        return userOptional.orElseThrow(() -> new UserException("Usuário não encontrado na base de dados."));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }



}


