<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Alcalapp</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/2.0.3/css/dataTables.bootstrap5.css">
    
    <%@ include file="scripts.jsp" %>
    <%@ include file="style.jsp" %>
  </head>
<body>
  <div class="container" id="dailywork">
    <!-- Sidebar -->
    <%@ include file="sidebar.jsp" %>
    
    <main>
      <!-- Topbar  -->
      <%@ include file="navbar.jsp" %>

      <section class="container-fluid">
        <div class="titulo">
          <h2>Trabajo diario - ${team.nameTeam}</h2>
        </div>
        <div class="columnas">
            <div class="columnas-izquierda">

              <c:forEach items="${projectsTables}" var="table">

                <div class="card shadow m-column m-right">
                  <div class=" card-header d-flex align-items-center justify-content-between bg-projects">
                    <h6 class="m-0 font-weight-bold text-uppercase text-white">Proyectos release ${table.releaseName}</h6>
                        <div class="dropdown">
                              <a class="dropdown-toggle table text-white" href="#" role="button" id="dropdownMenuLink"
                                  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                  <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                              </a>
                              <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                  aria-labelledby="dropdownMenuLink">
                                  <a class="dropdown-item" href="/releases/${table.idRelease}">Ver Release</a>
                                  <a class="dropdown-item createProjectLink" data-release-id="${table.idRelease}" data-target="#createModalProject" href="#">Añadir Proyecto</a>
                                  <div class="dropdown-divider"></div>
                                  <a class="dropdown-item deleteProjectLink text-danger" data-release-id="${table.idRelease}" data-target="#deleteModalRelease" href="#">Eliminar Proyecto</a>
                              </div>
                        </div>
                  </div>
    
                    <!-- Card Body -->
                    <div class="card-body padding-0">
                        <div class="chart-area">
                            <div class="columna-izquierda">
                              <table>
                                <thead>
                                  <tr>
                                    <th>Nombre</th>
                                    <th>Titulo</th>
                                    <th>Estado</th>
                                    <th>Release</th>
                                    <th>Encargado</th>
                                    <th>Equipo</th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <c:forEach items="${table.projects}" var="proyecto">
                                    <tr data-proyecto-id="${proyecto.idProject}" class="proyecto-row">
                                      
                                        <td>${proyecto.nameProject}</td>
                                        <td>${proyecto.titleProject}</td>
                                        <td>${proyecto.statusProject}</td>
                                        <td>${proyecto.releaseName}</td>
                                        <td>${proyecto.employeeUserAssign}</td>
                                        <td>${proyecto.teamNameAssign}</td>
                                      
                                    </tr>
                                  </c:forEach>
                              </table>
                              </div>
                        </div>
                    </div>
                </div>

              </c:forEach>

              <div class="card shadow m-column m-right">
                <div class=" card-header d-flex align-items-center justify-content-between bg-incidences">
                  <h6 class="m-0 font-weight-bold text-uppercase text-white">Incidencias</h6>
                  <div class="dropdown">
                      <a class="dropdown-toggle table text-white" href="#" role="button" id="dropdownMenuLink"
                          data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                      </a>
                      <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                          aria-labelledby="dropdownMenuLink">
                          <a class="dropdown-item" href="#">Action</a>
                          <a class="dropdown-item" href="#">Another action</a>
                      </div>
                  </div>
                </div>
                <!-- Card Body -->
                <div class="card-body padding-0">
                  <div class="chart-area">
                    <table>
                      <thead>
                        <tr>
                          <th>Nombre</th>
                          <th>Titulo</th>
                          <th>Prioridad</th>
                          <th>Estado</th>
                          <th>Fecha creación</th>
                          <th>Encargado</th>
                          <th>Equipo</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${ticketsNotCompleted}" var="ticket">
                          <tr data-ticket-id="${ticket.idTicket}" class="ticket-row">
                            
                              <td>${ticket.nameTicket}</td>
                              <td>${ticket.titleTicket}</td>
                              <td>${ticket.priorityTicket}</td>
                              <td>${ticket.statusTicket}</td>
                              <td>${ticket.initialDate}</td>
                              <td>${ticket.employeeUserAssign}</td>
                              <td>${ticket.teamNameAssign}</td>
                            
                          </tr>
                        </c:forEach>
                    </table>
                  </div>
                </div> 
              </div>
            </div>
            <div class="columnas-derecha align_columns">

              <div class="wrapper m-bottom m-top m-left">
                <header>
                  <div class="current-date"></div>
                  <div class="icons">
                    <span id="prev" class="material-symbols-rounded">
                      <i class="fa-solid fa-chevron-left"></i>
                    </span>
                    <span id="next" class="material-symbols-rounded">
                      <i class="fa-solid fa-chevron-right"></i>
                    </span>
                  </div>
                </header>
                <div class="calendar">
                  <ul class="weeks">
                    <li>D</li>
                    <li>L</li>
                    <li>M</li>
                    <li>X</li>
                    <li>J</li>
                    <li>V</li>
                    <li>S</li>
                  </ul>
                  <ul class="days"></ul>
                </div>
              </div>

              <div class="card shadow m-column m-left">
                <div class=" card-header d-flex align-items-center justify-content-between bg-charts">
                  <h6 class="m-0 font-weight-bold text-uppercase text-white">Carga de trabajo</h6>
                </div>
                <div class="card-body padding-0">
                  <div class="chart-container">
                    <canvas id="chart-carga-trabajo" user='${userPerEmployee}' load='${loadPerEmployee}'></canvas>
                  </div>
                  <div class="chart-area">
                    <c:forEach items="${workLoad.listWorkPerEmployee}" var="workPerEmployee">
                      <div class="carga">
                        <a class="carga nav-link dropdown-toggle collapsed" href="#" data-toggle="collapse" data-target="#collapseCargaTrabajo">
                          <span class="carga carga-text carga-tittle">
                            ${workPerEmployee.userEmployee}
                          </span> 
                        </a>
                        <div id="collapseCargaTrabajo" class="collapse">
                            <ul class="list-group">
                                <c:forEach items="${workPerEmployee.projectInProgress}" var="projectInProgress">
                                    <li class="list-group-item">
                                        <a class="link-text" href="/projects/${projectInProgress.idProject}">
                                            ${projectInProgress.nameProject} - ${projectInProgress.titleProject}
                                        </a>
                                    </li>
                                </c:forEach>
                                <c:forEach items="${workPerEmployee.ticketInProgress}" var="ticketsInProgress">
                                    <li class="list-group-item">
                                        <a class="link-text" href="/tickets/${ticketsInProgress.idTicket}">
                                            ${ticketsInProgress.nameTicket} - ${ticketsInProgress.titleTicket}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    </c:forEach>
                  </div>
                </div> 
              </div>
              
            </div>
        </div>
        <!-- Footer  -->
        <%@ include file="footer.jsp" %>
      </section>
      <%@ include file="modalCreate.jsp" %>
      <%@ include file="modalLogout.jsp" %>
      <%@ include file="modalCreateRelease.jsp" %>

      <%@ include file="scriptsDailyWork.jsp" %>
    </main>
  </div>

</body>
</html>



