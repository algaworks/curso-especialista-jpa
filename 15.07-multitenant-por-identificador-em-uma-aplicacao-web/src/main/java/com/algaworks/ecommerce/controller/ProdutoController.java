package com.algaworks.ecommerce.controller;

import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.repository.Produtos;
import com.algaworks.ecommerce.service.ProdutoService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private Produtos produtos;

    @Autowired
    private ProdutoService service;

    @PostMapping("/{id}/editar")
    public ModelAndView atualizar(@RequestAttribute String tenant,
                                  @PathVariable Integer id,
                                  @RequestParam Map<String, Object> produto,
                                  RedirectAttributes redirectAttributes) {
        service.atualizar(id, tenant, produto);

        redirectAttributes.addFlashAttribute("mensagem", "Atualização feita com sucesso!");

        return new ModelAndView("redirect:/produtos/{id}/editar");
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(
            @RequestAttribute String tenant, @PathVariable Integer id, ServletRequest servletRequest) {
        return novo(produtos.buscar(id, tenant), servletRequest);
    }

    @PostMapping("/novo")
    public ModelAndView criar(@RequestAttribute String tenant,
                              Produto produto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        Produto atualizado = service.criar(tenant, produto);

        redirectAttributes.addFlashAttribute("mensagem", "Registro criado com sucesso!");

        return new ModelAndView(
                "redirect:/produtos/{id}/editar", "id", atualizado.getId());
    }

    @GetMapping("/novo")
    public ModelAndView novo(Produto produto, ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        ModelAndView mv = new ModelAndView("produtos/produtos-formulario");
        mv.addObject("produto", produto);
        mv.addObject("httpServletRequest", httpServletRequest);
        return mv;
    }

    @GetMapping
    public ModelAndView listar(@RequestAttribute String tenant) {
        ModelAndView mv = new ModelAndView("produtos/produtos-lista");
        mv.addObject("produtos", produtos.listar(tenant));
        return mv;
    }
}