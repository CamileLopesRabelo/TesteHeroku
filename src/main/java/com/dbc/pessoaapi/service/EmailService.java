package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.dto.PessoaDTO;
import com.dbc.pessoaapi.entity.PessoaEntity;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String remetente;
    private final Configuration configuration;

    public void enviarEmailSimples(PessoaEntity pessoaEntity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(remetente);
        message.setTo(pessoaEntity.getEmail());
        message.setSubject("Assunto do e-mail");
        message.setText("Olá " + pessoaEntity.getNome() + ",\n\nEstamos felizes de ter você em nosso sistema :)\n\nSeu Cadastro foi realizado com sucesso" +
                ", seu identificador é: " + pessoaEntity.getIdPessoa() + ".\n\n\n Qualquer duvida é só contatar o suporte pelo email " + remetente + ".\n\n\n Att,\n\nSistema. ");
        emailSender.send(message);
    }

    public void enviarEmailComTemplateNoUpdatePessoa(PessoaDTO pessoaDTO) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(remetente);
        helper.setTo(pessoaDTO.getEmail());
        helper.setSubject("alteração de cadastro");

        Template template = configuration.getTemplate("email-template.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", pessoaDTO.getNome());
        dados.put("email", remetente);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);

        emailSender.send(mimeMessage);
    }

    public void enviarEmailComTemplateNoDeletePessoa(PessoaEntity pessoaEntity) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(remetente);
        helper.setTo(pessoaEntity.getEmail());
        helper.setSubject("Exclusão cadastro");

        Template template = configuration.getTemplate("email-template2.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", pessoaEntity.getNome());
        dados.put("email", remetente);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);

        emailSender.send(mimeMessage);
    }

    public void enviarEmailComAnexos() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(remetente);
        helper.setTo("maicon.gerardi@dbccompany.com.br");
        helper.setSubject("Email com anexo Exemplo");
        helper.setText("Teste");

        File file = new File("imagem.jpg");
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        helper.addAttachment(file.getName(), fileSystemResource);

        File file2 = new File("HELP.md");
        FileSystemResource fileSystemResource2 = new FileSystemResource(file2);
        helper.addAttachment(file2.getName(), fileSystemResource2);

        emailSender.send(message);
    }

    public void enviarEmailComTemplateAndAnexos() throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(remetente);
        helper.setTo("maicon.gerardi@dbccompany.com.br");
        helper.setSubject("Email com template Exemplo");

        Template template = configuration.getTemplate("email-template.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nomeUsuario", "Maicon Gerardi");
        dados.put("idade", 30);
        dados.put("cidade", "Florianópolis");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);

        File file = new File("imagem.jpg");
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        helper.addAttachment("foto.jpg", fileSystemResource);

        emailSender.send(mimeMessage);
    }
}
