package com.example.accessingdatamysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.accessingdatamysql.model.Mascotas;
import com.example.accessingdatamysql.repository.MascotasRepository;

//import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;
import java.lang.String;
import java.lang.Object;


@Controller
@RequestMapping(path="/mascotas")
public class MascotasController {

  @Autowired 
  private MascotasRepository mascotasRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;


  @GetMapping(path="/all") // GET http://localhost:8080/mascotas/all
  public @ResponseBody Iterable<Mascotas> getAllMascotas() {
    // This returns a JSON or XML with the users
    return mascotasRepository.findAll();
  }


  @PostMapping(path="/add") // POST http://localhost:8080/mascotas/add
  public @ResponseBody String addNewMascotas (@RequestParam String name, @RequestParam String raza, @RequestParam String propietario) {

    Mascotas m = new Mascotas();
    m.setName(name);
    m.setRaza(raza);
    m.setPropietario(propietario);
    mascotasRepository.save(m);
    return "Saved";
  }

  
  @PutMapping(path="/edit") //PUT http://localhost:8080/mascotas/edit
  public @ResponseBody String editMascotas(@RequestParam Integer id, @RequestParam String name, @RequestParam String raza, @RequestParam String propietario) {
    Mascotas mascotas = mascotasRepository.findById(id).orElse(null);
    if (mascotas != null) {
      mascotas.setName(name);
      mascotas.setRaza(raza);
      mascotas.setPropietario(propietario);
      mascotasRepository.save(mascotas);
      return "Edited";
    }
    return "Not found";
  }


  @GetMapping(path="/ver/{id}") // GET http://localhost:8080/ver/id
  public @ResponseBody Mascotas getMascotas(@PathVariable("id") Integer id) {
    return mascotasRepository.findById(id).orElse(null);
  }

  

  @DeleteMapping(path="/del")
  public @ResponseBody String deleteMascotas(@RequestParam Integer id) {
    Mascotas mascotas = mascotasRepository.findById(id).orElse(null);
    if (mascotas != null) {
      mascotasRepository.delete(mascotas);
      return "Deleted";
    }
    return "Not found";
  }

  @GetMapping(path="/get/report")
  public @ResponseBody List getReport() {
    String sql = "SELECT CONCAT(name, ' ==> ', raza, ' ==> ', propietario) as reporte FROM mascotas";
    List<Map<String, Object>> queryResult = jdbcTemplate.queryForList(sql);
    return queryResult;
  }
  
}