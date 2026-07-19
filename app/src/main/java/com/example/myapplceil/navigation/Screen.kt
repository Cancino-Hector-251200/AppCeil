package com.example.myapplceil.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Privacy : Screen("privacy")
    object CategorySelection : Screen("category_selection")
    object BudgetSetup : Screen("budget_setup")
    object Dashboard : Screen("dashboard")
    object Debts : Screen("debts")
    object Graphics : Screen("graphics")
    object Profile : Screen("profile")
    object Terms : Screen("terms")
    object Medals : Screen("medals")
    object Apartments : Screen("apartments")
    object ApartmentDetail : Screen("apartment_detail")
    object TemplateSavings : Screen("template_savings")
    object TemplateEntertainment : Screen("template_entertainment")
    object TemplateSchool : Screen("template_school")
    object TemplateHome : Screen("template_home")
    object TemplateFood : Screen("template_food")
    object TemplatePersonal : Screen("template_personal")
    object AdminMain : Screen("admin_main")
}
