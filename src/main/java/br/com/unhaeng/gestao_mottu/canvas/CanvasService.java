package br.com.unhaeng.gestao_mottu.canvas;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CanvasService {
    private final CanvasRepository repo;

    public CanvasService(CanvasRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Canvas seedPadraoSeNaoExistir(){
        return repo.findByName("PADRAO-1000x600").orElseGet(() -> {
            var c =  new Canvas();
            c.setNome( "PADRAO-1000x600" );
            c.setLargura(1000);
            c.setAltura(600);
            c.setBackgroundColor("#ffffff");
            return repo.save(c);
        });
    }
}
