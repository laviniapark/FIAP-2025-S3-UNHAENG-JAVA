package br.com.unhaeng.gestao_mottu.service;

import br.com.unhaeng.gestao_mottu.config.MessageHelper;
import br.com.unhaeng.gestao_mottu.model.Filial;
import br.com.unhaeng.gestao_mottu.repository.FilialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilialService {

    private final FilialRepository filialRepository;
    private final MessageHelper messageHelper;

    public List<Filial> getAllFiliais(){
        return filialRepository.findAll(Sort.by(Sort.Order.desc("isActive"), Sort.Order.asc("nome")));
    }

    public Filial getFilialById(Long id){
        return filialRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException(messageHelper.get("filial.notfound"))
        );
    }

    public Filial create(Filial filial){
        return filialRepository.save(filial);
    }

    public Filial update(Long id, Filial filial){
        Filial db = filialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("filial.notfound")));

        db.setNome(filial.getNome());
        db.setCnpj(filial.getCnpj());
        db.setEndereco(filial.getEndereco());

        return filialRepository.save(db);
    }

    public void deactivate(Long id){
        Filial db = filialRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(messageHelper.get("filial.notfound")));

        db.setActive(false);
        filialRepository.save(db);
    }
}
