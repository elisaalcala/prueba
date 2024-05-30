<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Tu pagina</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
      <script>
        $(document).ready(function() {
            $('.dropdown-toggle').click(function() {
                $(this).next('.dropdown-menu').toggleClass('show');
            });

            // Cerrar el menú desplegable cuando se hace clic fuera de él
            $(document).click(function(e) {
                if (!$(e.target).closest('.dropdown').length) {
                    $('.dropdown-menu').removeClass('show');
                }
            });

            // Función para verificar la URL actual y mostrar u ocultar la barra de búsqueda
            function toggleSearchBar() {
                var currentPath = window.location.pathname;
                var showSearchBarURLs = ['/dailywork', '/private'];
                if (showSearchBarURLs.includes(currentPath)) {
                    $('#searchContainer').show();
                } else {
                    $('#searchContainer').hide();
                }
            }

            // Llamar a la función toggleSearchBar() al cargar la página
            toggleSearchBar();
        });

        document.addEventListener("DOMContentLoaded", function() {
        // Función para controlar los paneles de colapso
        function togglePanel(panelLinkSelector, panelSelector) {
            var panelLink = document.querySelector(panelLinkSelector);
            var panel = document.querySelector(panelSelector);

            panelLink.addEventListener('click', function() {
                if (panel.classList.contains('show')) {
                    panel.classList.remove('show');
                    panelLink.classList.add('collapsed');
                } else {
                    panel.classList.add('show');
                    panelLink.classList.remove('collapsed');
                }
            });
        }

        // Controlar el panel de Proyectos
        togglePanel('.nav-link[data-target="#collapseUtilities"]', '#collapseUtilities');

        // Controlar el panel de Incidencias
        togglePanel('.nav-link[data-target="#collapseIncidencias"]', '#collapseIncidencias');

        // Controlar el panel de Estadísticas
        togglePanel('.nav-link[data-target="#collapseEstadisticas"]', '#collapseEstadisticas');
        });
      </script>
  </head>
  <body>
<style>
/* Restablecer estilos */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* Contenedor principal */
.container {
  display: flex;
  flex-direction: row;
  min-height: 100vh;
  max-width: 100%;
  padding-left: 0px;
  padding-right: 0px;

}
.me-2 {
    margin-right: .5rem !important;
}

/* Navegación lateral */

.bg-gradient-primary {
    background-color: #00838f;
    background-image: linear-gradient(180deg, #018e9b 10%, #006169 100%);
    background-size: cover;
}
.sidebar hr.sidebar-divider {
    border-top: 1px solid rgba(255, 255, 255, 0.329);
}

@media (min-width: 768px){
.sidebar {
    width: 14.5rem !important;
}
}
@media (min-width: 768px){
.sidebar .nav-item .nav-link[data-toggle=collapse]::after {
    width: 1rem;
    text-align: center;
    float: right;
    vertical-align: 0;
    border: 0;
    font-weight: 900;
    content: '\f107';
    font-family: 'Font Awesome 5 Free';
}
}
.nav-item{
  padding-left: 15px;
}
.navbar-nav{
  padding-right: 20px;
  padding-left: 20px;
}
.nav-color, .nav-colorlink {
  color: rgba(255, 255, 255, 0.575);
}
.nav-colorlink:hover, 
.sidebar-brand-color:hover {
    color: #fff;
}
.sidebar-heading{
  font-weight: 800;
  font-size: .75rem;
  text-transform: uppercase;
}
.sidebar-brand {
	  color: rgba(255, 255, 255, 0.966);
    height: 4.375rem;
    font-size: 1.3rem;
    font-weight: 900;
    text-transform: uppercase;
    letter-spacing: .05rem;
}
.mt-0{
  margin-top: 0rem;
}
.collapse-header {
    margin: 0;
    padding: .3rem 1rem;
    text-transform: uppercase;
    font-weight: 800;
    font-size: .70rem;
    color: #b7b9cc;
}
.collapse-item {
    padding: .5rem 1rem;
    margin: 0 .5rem;
    display: block;
    color: #3a3b45;
    text-decoration: none;
    border-radius: .35rem;
    white-space: nowrap;
}
.collapse-item:hover {
  background-color: #84c8cee3;
  text-decoration: none;
}
.bg-clear{
    background-color: #c7c7c771;
}

/* Cabecera */
header {
  background-color: #333;
  color: #fff;
  padding: 10px;
  text-align: center;
}

/* Navegación superior */
.navegacion-superior {
    background-color: #f0f0f0;
    padding: 5px;
    display: flex;
    justify-content: space-around;
}

.navegacion-superior a {
  text-decoration: none;
  color: #333;
  margin: 0 10px;
}

/* Contenido principal */
.container-fluid {
    padding-left: 1.5rem;
    padding-right: 1.5rem;
    width: 100%;
    margin-right: auto;
    margin-left: auto;
}

.container-tittle {
    font-weight: 800;
    font-size: 1.5rem;
}

/* Columnas */
.columnas {
  display: flex;
  flex-wrap: wrap;

}
.columnas-izquierda {
  display: flex;
  flex-wrap: wrap;
  flex: 3 4 auto;
  flex-direction: column; 

}
.columnas-derecha {
  display: flex;
  flex-wrap: wrap;
  flex: 1 4 auto;
  flex-direction: column; 
}
.columna-izquierda,.columna-derecha {
  padding: 10px;
}

/*Botones*/
.bg-cyan-800 {
    background-color: #00838f;
    border-color: #00838f;
}

/* Tablas */
table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
  padding: 5px;
}

main {
    flex: 1;
}

</style>
<body>
  <div class="container">
    <!-- Sidebar -->
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
            <a class="nav-link nav-colorlink" href="/dailywork">
              <i class="fa-solid fa-code-compare"></i>
              <span> En equipo</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link nav-colorlink" href="/private">
            <i class="fa-solid fa-briefcase"></i>
              <span> Mi trabajo</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link nav-colorlink" href="/private">
            <i class="fa-solid fa-calendar"></i>
              <span> Calendario</span></a>
        </li>
    
        <hr class="sidebar-divider">
    
        <div class="sidebar-heading nav-color">
            Control general
        </div>
    
        <li class="nav-item">
            <a class="nav-link collapsed nav-colorlink" href="#" data-toggle="collapse" data-target="#collapseUtilities">
                <i class="fas fa-fw fa-table"></i>
                <span>Proyectos</span>
            </a>
            <div id="collapseUtilities" class="collapse">
                <div class="bg-clear py-2 rounded">
                    <a class="collapse-item nav-colorlink" href="utilities-color.html">Todos</a>
                    <a class="collapse-item nav-colorlink" href="utilities-border.html">Por equipos</a>
                    <a class="collapse-item nav-colorlink" href="utilities-animation.html">Personales</a>
                </div>
            </div>
        </li>
    
        <li class="nav-item">
          <a class="nav-link collapsed nav-colorlink" href="#" data-toggle="collapse" data-target="#collapseIncidencias">
              <i class="fas fa-solid fa-bug"></i>
              <span>Incidencias</span>
          </a>
          <div id="collapseIncidencias" class="collapse">
              <div class="bg-white py-2 rounded">
                  <h6 class="collapse-header">Custom Utilities:</h6>
                  <a class="collapse-item" href="utilities-color.html">Todos</a>
                  <a class="collapse-item" href="utilities-border.html">Por equipos</a>
                  <a class="collapse-item" href="utilities-animation.html">Personales</a>
              </div>
          </div>
        </li>
    
        <li class="nav-item">
          <a class="nav-link collapsed nav-colorlink" href="#" data-toggle="collapse" data-target="#collapseEstadisticas">
            <i class="fas fa-fw fa-chart-line"></i>
            <span>Estadisticas</span>
          </a>
          <div id="collapseEstadisticas" class="collapse">
            <div class="bg-white py-2 rounded">
                <h6 class="collapse-header">Custom Utilities:</h6>
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
          <a class="nav-link nav-colorlink" href="/private">
            <i class="fa-solid fa-users"></i>
              <span> Equipos</span></a>
        </li>
    
        <hr class="sidebar-divider">
    
    
        <div class="sidebar-heading nav-color">
          Crear
        </div>
    
        <li class="nav-item">
            <a class="nav-link nav-colorlink" href="charts.html">
                <i class="fas fa-solid fa-pen-to-square"></i>
                <span>Incidencia</span></a>
        </li>
    
        <li class="nav-item">
            <a class="nav-link nav-colorlink" href="tables.html">
                <i class="fas fa-fw fa-table"></i>
                <span>Release</span></a>
        </li>
    
        <hr class="sidebar-divider">
    
        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>
    
      </ul>
    
    <main>
      <!-- Topbar  -->
      <nav class="navbar navbar-expand navbar-light bg-white topbar static-top shadow">
        <!-- Search Item -->
        <div class="container-fluid" id="searchContainer">
        <form class="d-sm-inline-block ml-md-3 my-md-0 navbar-search">
          <div class="input-group">
            <input type="text" class="form-control bg-light border-0 small" placeholder="Buscar..."
                aria-label="Search" aria-describedby="basic-addon2" id="searchInput">
                <div class="input-group-append">
                  <button class="btn bg-cyan-800" type="button" id="searchButton">
                    <i class="fas fa-search fa-sm text-white"></i>
                </button>
                </div>
          </div>
        </form>
        </div>
        <ul class="navbar-nav ml-auto">
    
          <!-- Nav Item - Messages -->
          <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <i class="fas fa-envelope fa-fw"></i>
                  <!-- Counter - Messages -->
                  <span class="badge badge-danger badge-counter">7</span>
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                  aria-labelledby="messagesDropdown">
                  <h6 class="dropdown-header">
                      Message Center
                  </h6>
                  <a class="dropdown-item d-flex align-items-center" href="#">
                      <div class="dropdown-list-image mr-3">
                          <img class="rounded-circle" src="img/undraw_profile_1.svg"
                              alt="...">
                          <div class="status-indicator bg-success"></div>
                      </div>
                      <div class="font-weight-bold">
                          <div class="text-truncate">Hi there! I am wondering if you can help me with a
                              problem I've been having.</div>
                          <div class="small text-gray-500">Emily Fowler · 58m</div>
                      </div>
                  </a>
                  <a class="dropdown-item d-flex align-items-center" href="#">
                      <div class="dropdown-list-image mr-3">
                          <img class="rounded-circle" src="img/undraw_profile_2.svg"
                              alt="...">
                          <div class="status-indicator"></div>
                      </div>
                      <div>
                          <div class="text-truncate">I have the photos that you ordered last month, how
                              would you like them sent to you?</div>
                          <div class="small text-gray-500">Jae Chun · 1d</div>
                      </div>
                  </a>
                  <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
              </div>
          </li>
    
          <div class="topbar-divider d-none d-sm-block"></div>
    
          <!-- Nav Item - User Information -->
          <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#"  id="userDropdown" role="button"
                  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <span class="mr-2 d-none d-lg-inline text-gray-600 small">${employee.userEmployee}</span>
                  <i class="fas fa-circle-user"></i>
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                  aria-labelledby="userDropdown">
                  <a class="dropdown-item" href="#">
                      <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                      Profile
                  </a>
                  <a class="dropdown-item" href="#">
                      <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                      Settings
                  </a>
                  <a class="dropdown-item" href="#">
                      <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                      Activity Log
                  </a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                      <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                      Logout
                  </a>
              </div>
          </li>
    
        </ul>
      </nav>

      <section class="container-fluid">
        <div class="container-tittle">
          <span>Trabajo diario equipo - ${team.nameTeam}</span>
        </div>
        <div class="columnas">
          <div class="columnas-izquierda">
            <div class="columna-izquierda">
            <h3>Proyectos de release</h3>
            <table>
              <thead>
                <tr>
                  <th>Nombre</th>
                  <th>Descripcion</th>
                  <th>Estado</th>
                  <th>Release</th>
                  <th>Encargado</th>
                  <th>Equipo</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${projectsNotCompleted}" var="proyecto">
                  <tr>
                    <td>${proyecto.nameProject}</td>
                    <td>${proyecto.descriptionProject}</td>
                    <td>${proyecto.statusProject}</td>
                    <td>${proyecto.releaseName}</td>
                    <td>${proyecto.employeeUser}</td>
                    <td>${proyecto.teamName}</td>
                  </tr>
                </c:forEach>
            </table>
            </div>
		      <div class="columna-izquierda">
          <h3>Incidencias</h3>
            <table>
              <thead>
                <tr>
                  <th>Columna 1</th>
                  <th>Columna 2</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Dato 1</td>
                  <td>Dato 2</td>
                </tr>
                <tr>
                  <td>Dato 3</td>
                  <td>Dato 4</td>
                </tr>
              </tbody>
            </table>
          </div>
          </div>
          <div class="columnas-derecha">
          <div class="columna-derecha">
          <h3>Carga de trabajo</h3>
            <table>
              <thead>
                <tr>
                  <th>Columna 1</th>
                  <th>Columna 2</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Dato 5</td>
                  <td>Dato 6</td>
                </tr>
                <tr>
                  <td>Dato 7</td>
                  <td>Dato 8</td>
                </tr>
              </tbody>
            </table>
          </div>
         </div> 
        </div>
      </section>
    </main>
  </div>

</body>
</html>



