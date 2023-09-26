package picpaydesafiobackend.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.application.payload.response.MessageResponseDTO;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.payload.request.UserRequest;
import picpaydesafiobackend.carteira.entity.Cartao;
import picpaydesafiobackend.carteira.entity.Carteira;
import picpaydesafiobackend.carteira.entity.Transacao;
import picpaydesafiobackend.carteira.payload.request.CartaoRequest;
import picpaydesafiobackend.common.entity.Contato;
import picpaydesafiobackend.common.entity.Endereco;
import picpaydesafiobackend.common.entity.Pessoa;
import picpaydesafiobackend.common.entity.SocialLink;
import picpaydesafiobackend.common.payload.request.ContactNumberRequest;
import picpaydesafiobackend.common.payload.request.ContatoRequest;
import picpaydesafiobackend.common.payload.request.EnderecoRequest;
import picpaydesafiobackend.common.payload.request.SocialMediaLinkRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomBuilders {

    public Transacao buildTransacao(String pagador, String recebedor, Double valor) {
        return Transacao.builder()
                .pagador(pagador)
                .recebedor(recebedor)
                .valor(valor)
                .build();
    }

    public Cartao buildCartao(CartaoRequest cartao, Carteira carteira) {
        return Cartao.builder()
                .bandeira(cartao.getBandeira())
                .nomeTitular(cartao.getNomeTitular())
                .numero(cartao.getNumero())
                .validade(cartao.getValidade())
                .codigoSeguranca(cartao.getCodigoSeguranca())
                .carteiraId(carteira.getId())
                .build();
    }

    public void buildAllContactsAndSetPessoa(ContatoRequest contatoRequest, Pessoa pessoa) {
        Endereco endereco = buildEndereco(contatoRequest.getEndereco(), pessoa);
        List<SocialLink> socialLinks = buildSocialLinks(contatoRequest.getSocialMediaLinks(), pessoa);
        List<Contato> contatos = buildContactNumbers(contatoRequest.getContacts(), pessoa);

        pessoa.setContacts(contatos);
        pessoa.setSocialLinks(socialLinks);
        pessoa.setEndereco(endereco);
    }

    public Endereco buildEndereco(EnderecoRequest enderecoRequest, Pessoa pessoa) {
        Endereco endereco = pessoa.getEndereco();

        if (endereco == null) {
            return buildNewEndereco(enderecoRequest, pessoa);
        }

        return updateEndereco(enderecoRequest, pessoa, endereco);
    }

    private Endereco updateEndereco(EnderecoRequest enderecoRequest, Pessoa pessoa, Endereco endereco) {
        return endereco = Endereco.builder()
                .id(pessoa.getEndereco().getId())
                .bairro(enderecoRequest.getBairro())
                .complemento(enderecoRequest.getComplemento())
                .logradouro(enderecoRequest.getLogradouro())
                .numero(enderecoRequest.getNumero())
                .cep(enderecoRequest.getCep())
                .cidade(enderecoRequest.getCidade())
                .uf(enderecoRequest.getUf())
                .pais(enderecoRequest.getPais())
                .pessoa(pessoa)
                .build();
    }

    private Endereco buildNewEndereco(EnderecoRequest enderecoRequest, Pessoa pessoa) {
        return Endereco.builder()
                .bairro(enderecoRequest.getBairro())
                .complemento(enderecoRequest.getComplemento())
                .logradouro(enderecoRequest.getLogradouro())
                .numero(enderecoRequest.getNumero())
                .cep(enderecoRequest.getCep())
                .cidade(enderecoRequest.getCidade())
                .uf(enderecoRequest.getUf())
                .pais(enderecoRequest.getPais())
                .pessoa(pessoa)
                .build();
    }

    public List<SocialLink> buildSocialLinks(List<SocialMediaLinkRequest> socialMediaLinkRequests, Pessoa pessoa) {
        List<SocialLink> existsSocialLinks = pessoa.getSocialLinks();

        if (existsSocialLinks.isEmpty()) {
            return buildNewSocialLinks(socialMediaLinkRequests, pessoa);
        }

        return updateSocialLinks(socialMediaLinkRequests, pessoa, existsSocialLinks);

    }

    private List<SocialLink> updateSocialLinks(
            List<SocialMediaLinkRequest> socialMediaLinkRequests,
            Pessoa pessoa,
            List<SocialLink> existsSocialLinks
    ) {

        for (SocialMediaLinkRequest socialMediaLinkRequest : socialMediaLinkRequests) {
            for (SocialLink socialMediaLink : existsSocialLinks) {

                socialMediaLink = SocialLink.builder()
                        .id(socialMediaLink.getId())
                        .nome(socialMediaLinkRequest.getNome())
                        .link(socialMediaLinkRequest.getLink())
                        .pessoa(pessoa)
                        .build();
                //pessoa.setSocialLinks();
                return existsSocialLinks;
            }
        }

        return existsSocialLinks;

    }

    private List<SocialLink> buildNewSocialLinks(List<SocialMediaLinkRequest> socialMediaLinkRequests, Pessoa pessoa) {
        List<SocialLink> socialLinks = new ArrayList<>();
        for (SocialMediaLinkRequest socialMediaLinkRequest : socialMediaLinkRequests) {
            socialLinks.add(buildSocialLink(socialMediaLinkRequest, pessoa));
        }
        return socialLinks;
    }

    public SocialLink buildSocialLink(SocialMediaLinkRequest socialMediaLinkRequest, Pessoa pessoa) {
        return SocialLink.builder()
                .nome(socialMediaLinkRequest.getNome())
                .link(socialMediaLinkRequest.getLink())
                .pessoa(pessoa)
                .build();
    }

    public List<Contato> buildContactNumbers(List<ContactNumberRequest> contactNumberRequests, Pessoa pessoa) {
        List<Contato> contatos = new ArrayList<>();
        for (ContactNumberRequest contactNumberRequest : contactNumberRequests) {
            contatos.add(buildContactNumber(contactNumberRequest, pessoa));
        }
        return contatos;
    }

    public Contato buildContactNumber(ContactNumberRequest contactNumberRequest, Pessoa pessoa) {
        return Contato.builder()
                .tipoContato(contactNumberRequest.getTipoContato())
                .value(contactNumberRequest.getValue())
                .pessoa(pessoa)
                .build();
    }

    public User buildUser(UserRequest userRequest, String encryptedPassword) {
        return User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(encryptedPassword)
                .tipoPessoa(userRequest.getTipoPessoa())
                .dtUltimoLogin(null)
                .build();
    }

    /*public Pessoa buildPessoa(User user) {
        return Pessoa.builder()
                .fullName(user.getUsername())
                .usuarioId(user.getId())
                .build();
    }*/

    public MessageResponseDTO BuildMessageDTO(Boolean success, String message, Object data, String error) {
        return MessageResponseDTO.builder()
                .success(success)
                .message(message)
                .data(data)
                .error(error)
                .timestamp(LocalDate.now())
                .build();
    }

    public Carteira buildCarteira(User user) {
        return Carteira.builder()
                .user(user)
                .saldo(0.0)
                .build();
    }
}