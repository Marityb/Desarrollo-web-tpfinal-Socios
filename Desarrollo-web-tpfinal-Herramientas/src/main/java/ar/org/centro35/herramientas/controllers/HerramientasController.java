package ar.org.centro35.herramientas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.org.centro35.herramientas.entities.Herramienta;
import ar.org.centro35.herramientas.enums.HerramientaEstado;
import ar.org.centro35.herramientas.enums.HerramientaTipo;
import ar.org.centro35.herramientas.repositories.HerramientaRepository;


@Controller
public class HerramientasController {
    
    @Autowired
    private HerramientaRepository herramientaRepository;

    private String mensajeHerramienta = "Ingrese una nueva herramienta!";

    @GetMapping("/herramientas")
    public String getHerramientas(
                @RequestParam(name="buscar", defaultValue = "", required = false) String buscar, 
                Model model) {
        //herramientaRepository.findAll().forEach(System.out::println);
        //herramientaRepository.findByDescripcion("pinza").forEach(System.out::println);
        model.addAttribute("tipos", HerramientaTipo.values());
        model.addAttribute("estados", HerramientaEstado.values());
        model.addAttribute("mensajeHerramienta", mensajeHerramienta);
        model.addAttribute("herramienta", new Herramienta());
        model.addAttribute("likeDescripcion", 
            ((List<Herramienta>)herramientaRepository.findAll())
                .stream()
                .filter(h->h.getHerramienta_estado()!=HerramientaEstado.FUERA_DE_USO
                            && h.getDescripcion().toLowerCase().contains(buscar.toLowerCase())));
        // select * from herramientas where estado!='FUERA_DE_USO' && descripcion like '%%';
        return "herramientas";
    }
    
    @PostMapping("/herramientasSave")
    public String herramientasSave(@ModelAttribute Herramienta herramienta) {
        // System.out.println("----------------------------------------------------");
        // System.out.println(herramienta);
        // System.out.println("----------------------------------------------------");
        try {
            //if(herramienta.getCodigo_articulo().length()!=13) herramienta.setCodigo_articulo(null);
            herramientaRepository.save(herramienta);
            if(herramienta.getId()>0){
                mensajeHerramienta="Se guardo la herramienta id:"+herramienta.getId();
            }else{
                mensajeHerramienta="No se pudo guardar la herramienta!";
            }
        } catch (Exception e) {
            System.out.println("*************************************************************");
            System.out.println(e);
            System.out.println("*************************************************************");
            mensajeHerramienta = "Ocurrio un error!";
        }
        return "redirect:herramientas";
    }

    @PostMapping("herramientasRemove")
    public String herramientasRemove(@RequestParam(name="idBorrar", defaultValue = "0", required = false) int idBorrar){
        // System.out.println("*************************************************************");
        // System.out.println(idBorrar);
        // System.out.println("*************************************************************");
        // if(pr.getCantidadPrestamos(idBorrar)==0){
        //     hr.remove(hr.getById(idBorrar)); 
        //     mensajeHerramienta = "Se borro la herramienta id: "+idBorrar+"!";   
        // }else{
        //     mensajeHerramienta = "No se pudo borrar la herramienta id: "+idBorrar+", por que tiene prestamos activos!";
        // }
        try {
            //herramientaRepository.delete(herramientaRepository.findById(idBorrar).get()); 
            Herramienta herramienta=herramientaRepository.findById(idBorrar).get();
            herramienta.setHerramienta_estado(HerramientaEstado.FUERA_DE_USO);
            herramientaRepository.save(herramienta);
            mensajeHerramienta = "La herramienta id: "+idBorrar+", quedo borrada!";
        } catch (Exception e) {
            mensajeHerramienta = "No se pudo borrar la herramienta id: "+idBorrar+", por que tiene prestamos activos!";
        }
        return "redirect:herramientas";     
    }
}
