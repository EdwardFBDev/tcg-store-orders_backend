package com.eduardo.tcgstore.controller;

import com.eduardo.tcgstore.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalCustomers", dashboardService.getTotalCustomers());
        model.addAttribute("totalOrders", dashboardService.getTotalOrders());
        model.addAttribute("totalRevenue", dashboardService.getTotalRevenue());
        model.addAttribute("inventoryValue", dashboardService.getInventoryValue());

        return "admin/dashboard";
    }
}