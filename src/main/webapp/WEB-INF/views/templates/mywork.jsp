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
  <div class="container">
    <!-- Sidebar -->
    <%@ include file="sidebar.jsp" %>
    
    <main>
      <!-- Topbar  -->
      <%@ include file="navbar.jsp" %>

      <section class="container-fluid">
        <div class="titulo">
          <h2>Mi trabajo - ${employee.employeeName} ${employee.employeeLastName}</h2>
        </div>

        <div class="columnas">
            <div class="columnas-izquierda">

                <div class="card shadow m-column m-right">
                  <div class=" card-header d-flex align-items-center justify-content-between bg-projects">
                    <h6 class="m-0 font-weight-bold text-uppercase text-white">Proyectos por hacer</h6>
                        <div class="dropdown">
                              <a class="dropdown-toggle table text-white" href="#" role="button" id="dropdownMenuLink"
                                  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                  <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                              </a>
                              <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                  aria-labelledby="dropdownMenuLink">
                                  <div class="dropdown-header">Dropdown Header:</div>
                                  <a class="dropdown-item" href="#">Action</a>
                                  <a class="dropdown-item" href="#">Another action</a>
                                  <div class="dropdown-divider"></div>
                                  <a class="dropdown-item" href="#">Something else here</a>
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
                                    <c:forEach items="${projectsNotCompleted}" var="proyecto">
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

                <div class="card shadow m-column m-right">
                  <div class=" card-header d-flex align-items-center justify-content-between bg-incidences">
                    <h6 class="m-0 font-weight-bold text-uppercase text-white">Incidencias por hacer</h6>
                    <div class="dropdown">
                        <a class="dropdown-toggle table text-white" href="#" role="button" id="dropdownMenuLink"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                            aria-labelledby="dropdownMenuLink">
                            <div class="dropdown-header">Dropdown Header:</div>
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
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
                            <th>Descripcion</th>
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
                                <td>${ticket.descriptionTicket}</td>
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

                <div class="card shadow m-column m-right">
                    <div class=" card-header d-flex align-items-center justify-content-between bg-projects">
                      <h6 class="m-0 font-weight-bold text-uppercase text-white">Proyectos por desplegar</h6>
                          <div class="dropdown">
                                <a class="dropdown-toggle table text-white" href="#" role="button" id="dropdownMenuLink"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                    aria-labelledby="dropdownMenuLink">
                                    <div class="dropdown-header">Dropdown Header:</div>
                                    <a class="dropdown-item" href="#">Action</a>
                                    <a class="dropdown-item" href="#">Another action</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Something else here</a>
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
                                      <c:forEach items="${projectsReady}" var="proyecto">
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
              
              <div class="card shadow m-column m-right">
                <div class=" card-header d-flex align-items-center justify-content-between bg-incidences">
                  <h6 class="m-0 font-weight-bold text-uppercase text-white">Incidencias por desplegar</h6>
                  <div class="dropdown">
                      <a class="dropdown-toggle table text-white" href="#" role="button" id="dropdownMenuLink"
                          data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                      </a>
                      <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                          aria-labelledby="dropdownMenuLink">
                          <div class="dropdown-header">Dropdown Header:</div>
                          <a class="dropdown-item" href="#">Action</a>
                          <a class="dropdown-item" href="#">Another action</a>
                          <div class="dropdown-divider"></div>
                          <a class="dropdown-item" href="#">Something else here</a>
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
                          <th>Descripcion</th>
                          <th>Prioridad</th>
                          <th>Estado</th>
                          <th>Fecha creación</th>
                          <th>Encargado</th>
                          <th>Equipo</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${ticketsReady}" var="ticket">
                          <tr data-ticket-id="${ticket.idTicket}" class="ticket-row">
                              <td>${ticket.nameTicket}</td>
                              <td>${ticket.descriptionTicket}</td>
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

              <div class="card shadow m-column m-right">
                <div class=" card-header d-flex align-items-center justify-content-between bg-finish">
                  <h6 class="m-0 font-weight-bold text-uppercase text-white">Proyectos acabados</h6>
                      <div class="dropdown">
                            <a class="dropdown-toggle table text-white" href="#" role="button" id="dropdownMenuLink"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                aria-labelledby="dropdownMenuLink">
                                <div class="dropdown-header">Dropdown Header:</div>
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Something else here</a>
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
                                  <c:forEach items="${projectsFinish}" var="proyecto">
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
          
          <div class="card shadow m-column m-right">
            <div class=" card-header d-flex align-items-center justify-content-between bg-finish">
              <h6 class="m-0 font-weight-bold text-uppercase text-white">Incidencias acabadas</h6>
              <div class="dropdown">
                  <a class="dropdown-toggle table text-white" href="#" role="button" id="dropdownMenuLink"
                      data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                  </a>
                  <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                      aria-labelledby="dropdownMenuLink">
                      <div class="dropdown-header">Dropdown Header:</div>
                      <a class="dropdown-item" href="#">Action</a>
                      <a class="dropdown-item" href="#">Another action</a>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="#">Something else here</a>
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
                      <th>Descripcion</th>
                      <th>Prioridad</th>
                      <th>Estado</th>
                      <th>Fecha creación</th>
                      <th>Encargado</th>
                      <th>Equipo</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${ticketsFinish}" var="ticket">
                      <tr data-ticket-id="${ticket.idTicket}" class="ticket-row">
                        
                          <td>${ticket.nameTicket}</td>
                          <td>${ticket.descriptionTicket}</td>
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


            <div class="columnas-derecha">
              <div class="card shadow m-column m-left">

                <div class=" card-header d-flex align-items-center justify-content-between bg-charts">
                  <h6 class="m-0 font-weight-bold text-uppercase text-white">Control de trabajo</h6>
                </div>
                
                

              </div>
            </div>

        </div>
      </section>
      <%@ include file="modalCreate.jsp" %>
      <%@ include file="modalLogout.jsp" %>
      <%@ include file="modalCreateRelease.jsp" %>

    </main>
  </div>

</body>
</html>



