package br.com.unhaeng.gestao_mottu.service;

import br.com.unhaeng.gestao_mottu.config.MessageHelper;
import br.com.unhaeng.gestao_mottu.model.Filial;
import br.com.unhaeng.gestao_mottu.repository.FilialRepository;
import br.com.unhaeng.gestao_mottu.model.Funcionario;
import br.com.unhaeng.gestao_mottu.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FilialRepository filialRepository;
    private final MessageHelper messageHelper;

    public List<Funcionario> getAllFuncionarios(){
        return funcionarioRepository.findAll(Sort.by(Sort.Order.desc("isActive"), Sort.Order.asc("nomeCompleto")));
    }

    public Funcionario getFuncionarioById(Long id){
        return funcionarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(messageHelper.get("funcionario.notfound"))
        );
    }

    public Funcionario create(Funcionario funcionario){
        Long filialId = (funcionario.getFilial() != null) ? funcionario.getFilial().getFilialId() : null;
        if (filialId == null){
            throw new IllegalArgumentException(messageHelper.get("filialId.null"));
        }
        Filial filial = filialRepository.findById(filialId)
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("filial.notfound")));
        funcionario.setFilial(filial);
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario update(Long id, Funcionario funcionario){
        Funcionario db =  funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("funcionario.notfound")));

        db.setNomeCompleto(funcionario.getNomeCompleto());
        db.setCpf(funcionario.getCpf());
        db.setCargo(funcionario.getCargo());

        Long filialId = (funcionario.getFilial() != null) ? funcionario.getFilial().getFilialId() : null;
        if (filialId != null){
            Filial filial = filialRepository.findById(filialId)
                    .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("filial.notfound")));
            db.setFilial(filial);
        }

        return funcionarioRepository.save(db);
    }

    public void deactivate(Long id){
        Funcionario db = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("funcionario.notfound")));
        db.setActive(false);
        funcionarioRepository.save(db);
    }
}
