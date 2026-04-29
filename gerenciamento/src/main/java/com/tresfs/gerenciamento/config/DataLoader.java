package com.tresfs.gerenciamento.config;

import com.tresfs.gerenciamento.entity.Setor;
import com.tresfs.gerenciamento.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader  implements CommandLineRunner {
     @Autowired
    private SetorRepository setorRepository;

     @Override
    public void run(String... args) throws Exception{
         if (setorRepository.count () == 0){
            setorRepository.save(new Setor(null, "Setor Licitação", "" ));
            setorRepository.save(new Setor(null, "Compras", ""));
            setorRepository.save(new Setor(null, "Contabilidade", ""));
            setorRepository.save(new Setor(null, "Almoxerifado", ""));
            setorRepository.save(new Setor(null, "Juridico", ""));
            setorRepository.save(new Setor(null, "Controladoria", ""));
            setorRepository.save(new Setor(null, "Administração", ""));
         }
     }
}
