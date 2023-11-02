package ar.org.centro35.herramientas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ar.org.centro35.herramientas.entities.Socio;
import ar.org.centro35.herramientas.enums.HerramientaTipo;
import ar.org.centro35.herramientas.enums.SocioEstado;
import ar.org.centro35.herramientas.repositories.SocioRepository;

@Controller
public class SocioController {
    @Autowired
    private SocioRepository socioRepository;
    private String mensajeSocio = "Ingrese un nuevo socio";

    @GetMapping("/socios")
    public String getSocios(
        @RequestParam(name="buscar", defaultValue = "", required = false) String buscar,
        Model model) {
        model.addAttribute("tipos", HerramientaTipo.values());
        model.addAttribute("estados", SocioEstado.values());
        model.addAttribute("mensajeSocio", mensajeSocio);
        model.addAttribute("socio", new Socio());
        model.addAttribute("likeNombre", 
            ((List<Socio>)socioRepository.findAll())
                .stream()
                .filter(s->s.getSocio_estado()!=SocioEstado.NO_ACTIVO
                        && s.getNombre().toLowerCase().contains(buscar.toLowerCase())));
        return "socios";
        }

    @PostMapping("/sociosSave")
    public String sociosSave(@ModelAttribute Socio socio){
        try {
            socioRepository.save(socio);
            if(socio.getId()>0){
                mensajeSocio="Se guardo el socio id:" +socio.getId();
            }else{
                mensajeSocio="No se pudo guardar el socio!!";
            }
        } catch (Exception e){
            System.out.println("*************************************************************");
            System.out.println(e);
            System.out.println("*************************************************************");
            mensajeSocio = "Ocurrio un error!";
        }
        return "redirect:socios";
    }
    @PostMapping("sociosRemove")
    public String sociosRemove(@RequestParam(name = "idBorrar", defaultValue = "0", required = false) int idBorrar){
        try {
            Socio socio=socioRepository.findById(idBorrar).get();
            socio.setSocio_estado(SocioEstado.NO_ACTIVO);
            socioRepository.save(socio);
            mensajeSocio ="El socio id:"+idBorrar+", quedo borrado!";
        } catch (Exception e){
            mensajeSocio="No se pudo borrar el socio id:"+idBorrar+", porque tiene prestamos activos!";
        }
        return "redirect:socios";
    }
}
