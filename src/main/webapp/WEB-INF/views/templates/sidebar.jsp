<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<ul class="navbar-nav bg-gradient-primary sidebar" id="accordionSidebar">

    <a class="sidebar-brand d-flex align-items-center justify-content-center nav-link sidebar-brand-color" href="/dailywork">
        <div class="sidebar-brand-icon">
          <i class="fas fa-solid fa-rocket"></i>
          <span class="sidebar-brand-text mx-3">Alcalapp</span>
        </div>
    </a>

    <hr class="sidebar-divider mt-0">

    <div class="sidebar-heading nav-color">
      Trabajo diario
    </div>

    <li class="nav-item">
        <a class="nav-link nav-colorlink" id="dailywork-link" href="/dailywork">
          <i class="fa-solid fa-code-compare"></i>
          <span>En equipo</span></a>
    </li>
    <li class="nav-item">
      <a class="nav-link nav-colorlink" id="mywork-link" href="/mywork">
        <i class="fa-solid fa-briefcase"></i>
          <span>Mi trabajo</span></a>
    </li>

    <hr class="sidebar-divider">

    <div class="sidebar-heading nav-color">
        Control general
    </div>

    <li class="nav-item">
      <a class="nav-link collapsed nav-colorlink" href="#" data-toggle="collapse" data-target="#collapseEstadisticas">
        <i class="fas fa-fw fa-chart-line"></i>
        <span>Estadisticas</span>
      </a>
      <div id="collapseEstadisticas" class="collapse">
        <div class="bg-white py-2 rounded">
            <h6 class="collapse-header">Tablas:</h6>
            <a class="collapse-item" href="utilities-color.html">Globales</a>
            <a class="collapse-item" href="utilities-border.html">Equipos</a>
            <a class="collapse-item" href="utilities-animation.html">Personales</a>
        </div>
      </div>
    </li>

    <hr class="sidebar-divider">

    <div class="sidebar-heading nav-color">
      Información
    </div>

    <li class="nav-item">
      <a class="nav-link nav-colorlink" id="releases-link" href="/releases">
        <i class="fas fa-fw fa-table"></i>
          <span>Releases</span></a>
    </li>

    <li class="nav-item">
      <a class="nav-link nav-colorlink" id="projects-link" href="/projects">
        <i class="fa-solid fa-code"></i>
            <span>Proyectos</span></a>
    </li>

    <li class="nav-item">
      <a class="nav-link nav-colorlink" id="tickets-link" href="/tickets">
        <i class="fas fa-solid fa-bug"></i>
          <span>Incidencias</span></a>
    </li>

    <hr class="sidebar-divider">


    <div class="sidebar-heading nav-color">
      Crear
    </div>

    <li class="nav-item">
        <button class="nav-link nav-colorlink" data-bs-toggle="modal" data-bs-target="#createModal">
            <i class="fas fa-solid fa-pen-to-square"></i>
            <span>Incidencia</span></button>
    </li>

    <li class="nav-item">
      <button class="nav-link nav-colorlink" data-bs-toggle="modal" data-bs-target="#createModalRelease">
            <i class="fas fa-fw fa-table"></i>
            <span>Release</span></button>
    </li>

    <hr class="sidebar-divider">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

  </ul>