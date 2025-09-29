package br.com.unhaeng.gestao_mottu.service;

import br.com.unhaeng.gestao_mottu.config.MessageHelper;
import br.com.unhaeng.gestao_mottu.dto.RevisionDTO;
import br.com.unhaeng.gestao_mottu.model.Canvas;
import br.com.unhaeng.gestao_mottu.model.Filial;
import br.com.unhaeng.gestao_mottu.model.Revision;
import br.com.unhaeng.gestao_mottu.repository.CanvasRepository;
import br.com.unhaeng.gestao_mottu.repository.FilialRepository;
import br.com.unhaeng.gestao_mottu.repository.RevisionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RevisionService {

    private final RevisionRepository revisionRepo;
    private final CanvasRepository canvasRepo;
    private final FilialRepository filialRepo;
    private final MessageHelper messageHelper;

    @Transactional
    public Revision saveSnapshot(RevisionDTO.Create dto){
        Canvas canvas = canvasRepo.findById(dto.canvasId())
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("canvas.notfound")));
        Filial filial = filialRepo.findById(dto.filialId())
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("filial.notfound")));

        Revision r = new Revision();
        r.setCanvas(canvas);
        r.setFilial(filial);
        r.setShapesJson(dto.shapesJson());
        r.setSvgText(dto.svgText());
        return revisionRepo.save(r);
    }

    @Transactional(readOnly = true)
    public List<Revision> listByCanvas(Long canvasId){
        return revisionRepo.findByCanvas_CanvasIdOrderByCreatedAtDesc(canvasId);
    }

    @Transactional(readOnly = true)
    public Revision getLatest(Long canvasId){
        return revisionRepo.findTopByCanvas_CanvasIdOrderByCreatedAtDesc(canvasId)
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("norevision.canvas")));
    }

    /**
     * Restaurar = criar nova revisão clonando o conteúdo de uma revisão antiga.
     * (não sobrescreve a antiga; mantém trilha de auditoria)
     */
    @Transactional
    public Revision restoreFrom(Long revisionId){
        Revision source = revisionRepo.findById(revisionId)
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("revision.notfound")));

        Revision newRev = new Revision();
        newRev.setCanvas(source.getCanvas());
        newRev.setFilial(source.getFilial());
        newRev.setShapesJson(source.getShapesJson());
        newRev.setSvgText(source.getSvgText());
        return revisionRepo.save(newRev);
    }

    // mantém somente as N revisões mais recentes para um canvas
    @Transactional
    public void prune(Long canvasId, int keepLastN){
        List<Revision> all = revisionRepo.findByCanvas_CanvasIdOrderByCreatedAtAsc(canvasId);
        if (all.size() <= keepLastN) return;

        int toDelete = all.size() - keepLastN;
        all.stream()
                .sorted(Comparator.comparing(Revision::getCreatedAt)) // mais antigas primeiro
                .limit(toDelete)
                .forEach(revisionRepo::delete);
    }
}
