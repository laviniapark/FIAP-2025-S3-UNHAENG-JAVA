package br.com.unhaeng.gestao_mottu.service;

import br.com.unhaeng.gestao_mottu.config.MessageHelper;
import br.com.unhaeng.gestao_mottu.model.Filial;
import br.com.unhaeng.gestao_mottu.repository.FilialRepository;
import br.com.unhaeng.gestao_mottu.model.Moto;
import br.com.unhaeng.gestao_mottu.repository.MotoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MotoService {

    private final MotoRepository motoRepository;
    private final FilialRepository filialRepository;
    private final MessageHelper messageHelper;

    public List<Moto> getAllMotos(){
        return motoRepository.findAll();
    }

    public Moto getMotoById(Long id){
        return motoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(messageHelper.get("moto.notfound"))
        );
    }

    public Moto create(Moto moto){
        Long filialId = (moto.getFilial() != null) ? moto.getFilial().getFilialId() : null;
        if (filialId == null){
            throw new IllegalArgumentException(messageHelper.get("filialId.null"));
        }
        Filial filial = filialRepository.findById(filialId)
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("filial.notfound")));
        moto.setFilial(filial);
        return motoRepository.save(moto);
    }

    public Moto update(Long id, Moto moto){
        Moto db = motoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("moto.notfound")));

        db.setPlaca(moto.getPlaca());
        db.setMarca(moto.getMarca());
        db.setModelo(moto.getModelo());
        db.setAno(moto.getAno());
        db.setStatus(moto.getStatus());

        Long filialId = (moto.getFilial() != null) ? moto.getFilial().getFilialId() : null;
        if (filialId != null) {
            Filial filial = filialRepository.findById(filialId)
                    .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("filial.notfound")));
            db.setFilial(filial);
        }

        return motoRepository.save(db);
    }

    public void deleteById(Long id){
        motoRepository.delete(getMotoById(id));
    }
}
