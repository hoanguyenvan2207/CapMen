package com.example.assignmentgd1.controller;

import com.example.assignmentgd1.model.NhanVien;
import com.example.assignmentgd1.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("dsNhanVien", nhanVienService.getAllNhanVien());
        return "nhan-vien/list";
    }

    @GetMapping("/search")
    public String timKiemNhanVien(@RequestParam String ma, Model model) {
        if(ma.equals("")) {
            return "redirect:/nhan-vien/hien-thi";
        }else{
            model.addAttribute("dsNhanVien", nhanVienService.findNhanviensByMaNv(ma));
        }
        return "nhan-vien/list";
    }

    @GetMapping("/add")
    public String themNhanVienForm(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        return "nhan-vien/add"; // Form thêm mới
    }

    @PostMapping("/add")
    public String themNhanVien(@ModelAttribute NhanVien nhanVien) {
        nhanVienService.saveNhanVien(nhanVien);
        return "redirect:/nhan-vien/hien-thi";
    }

    @GetMapping("/update/{id}")
    public String suaNhanVienForm(@PathVariable Integer id, Model model) {
        NhanVien nhanVien = nhanVienService.getNhanVienById(id);
        model.addAttribute("nhanVien", nhanVien);
        return "nhan-vien/update"; // Form sửa
    }

    @PostMapping("/update/{id}")
    public String suaNhanVien(@PathVariable Integer id, @ModelAttribute NhanVien nhanVien) {
        nhanVienService.updateNhanVien(id, nhanVien);
        return "redirect:/nhan-vien/hien-thi";
    }

    @GetMapping("/delete")
    public String xoaNhanVien(@RequestParam Integer id) {
        nhanVienService.deleteNhanVien(id);
        return "redirect:/nhan-vien/hien-thi";
    }

    @GetMapping("/detail")
    public String chiTietNhanVien(@RequestParam Integer id, Model model) {
        NhanVien nhanVien = nhanVienService.getNhanVienById(id);
        model.addAttribute("nhanVien", nhanVien);
        return "nhan-vien/detail"; // Trang chi tiết
    }

}
