package br.com.unhaeng.gestao_mottu.service;

import br.com.unhaeng.gestao_mottu.config.MessageHelper;
import br.com.unhaeng.gestao_mottu.model.Filial;
import br.com.unhaeng.gestao_mottu.repository.FilialRepository;
import br.com.unhaeng.gestao_mottu.model.Canvas;
import br.com.unhaeng.gestao_mottu.repository.CanvasRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CanvasService {

    private static final String DEFAULT_NOME = "PADRAO-1000x600";
    private static final int DEFAULT_LARGURA = 1000;
    private static final int DEFAULT_ALTURA  = 600;
    private static final String DEFAULT_BG   = "#ffffff";
    private static final int DEFAULT_GRID    = 10;

    private final CanvasRepository canvasRepo;
    private final FilialRepository filialRepo;
    private final MessageHelper messageHelper;

    @Transactional
    public Canvas getOrCreateDefaultForFilial(Long filialId) {
        return canvasRepo.findByFilial_FilialId(filialId).orElseGet(() -> {
            var filial = filialRepo.findById(filialId)
                    .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("filial.notfound")));

            var c = Canvas.builder()
                    .filial(filial)
                    .nome(DEFAULT_NOME)
                    .largura(DEFAULT_LARGURA)
                    .altura(DEFAULT_ALTURA)
                    .backgroundColor(DEFAULT_BG)
                    .gridSize(DEFAULT_GRID)
                    .snapAtivo(true)
                    .locked(false)
                    .build();

            return canvasRepo.save(c);
        });
    }

    public Canvas getById(Long canvasId) {
        return canvasRepo.findById(canvasId).orElseThrow(
                () -> new EntityNotFoundException(messageHelper.get("canvas.notfound"))
        );
    }
}
