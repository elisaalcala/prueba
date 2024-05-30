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
    <%@ include file="scriptsRelease.jsp" %>
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
          <h2>${release.nameRelease}</h2>
        </div>
        <div class="div-col justify-content-between">

          <div class="div-col">

            <div class="ticket-button m-left">
              <button type="button" class="btn bg-cyan-800" id="createProjectButton" data-bs-toggle="modal" data-bs-target="#createModalProject">
                <i class="fa-solid fa-plus"></i>
                Añadir Proyecto
              </button>
            </div>

            <div class="ticket-button m-left">
              <button type="button" class="btn bg-cyan-800" id="editButton" data-bs-toggle="modal" data-bs-target="#editModalRelease">
                <i class="fa-solid fa-pen-to-square"></i>
                Editar
              </button>
            </div>

            <div class="ticket-button m-left">
              <button type="button" class="btn btn-danger" id="deleteButton" data-bs-toggle="modal" data-bs-target="#deleteModalRelease">
                <i class="fa-solid fa-trash"></i>
                Eliminar</button>
            </div>
            
          </div>
          
        </div>

        <div class="columnas col-margin-top-left">

            <div class="columnas-izquierda clearfix flex-wrap-nowrap">

              <!--Proyectos por release-->
              <div class="card shadow mb-4">
                  <div class="card-body">
                          <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                              <thead>
                                <tr>
                                  <th>Nombre</th>
                                  <th>Titulo</th>
                                  <th>Estado</th>
                                  <th>Encargado</th>
                                  <th>Equipo</th>
                                </tr>
                              </thead>
                              <tbody>
                                <c:forEach items="${projectMap}" var="proyecto">
                                  <tr data-proyecto-id-release="${proyecto.idProject}" class="proyecto-row-per-release pointer-row">
                                    <td>${proyecto.nameProject}</td>
                                    <td>${proyecto.titleProject}</td>
                                    <td>${proyecto.statusProject}</td>
                                    <td>${proyecto.employeeUserAssign}</td>
                                    <td>${proyecto.teamNameAssign}</td>
                                  </tr>
                                </c:forEach>
                            </table>
                      
                  </div>
              </div>
              
              <!--Informacion del proyecto clickado-->
              <div class="project-info">
                
                  
              </div>

                
            </div>
             
            <div class="columnas-derecha flex-wrap-nowrap">
                
                <div class="wrapper cal-margins">
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
                <div class="col">
                    <div class="ticket-info">
                        <div class="ticket-detail-heading color-cyan">
                        Fechas
                        </div>
                        <div class="row">
                            <dt class="col">Creación:</dt>
                            <dd class="col" id="initialDate"></dd>
                        </div>
                        <div class="row">
                            <dt class="col">Captura de requisitos:</dt>
                            <dd class="col" id="requirementsDate"></dd>
                        </div>
                        <div class="row">
                            <dt class="col">Asignación de proyectos:</dt>
                            <dd class="col" id="prjAssignmentDate"></dd>
                        </div>
                        <div class="row">
                            <dt class="col">Comienzo desarrollo:</dt>
                            <dd class="col" id="developDate"></dd>
                        </div>
                        <div class="row">
                            <dt class="col">Pruebas en TST:</dt>
                            <dd class="col" id="tstDate"></dd>
                        </div>
                        <div class="row">
                            <dt class="col">Pruebas en UAT:</dt>
                            <dd class="col" id="uatDate"></dd>
                        </div>
                        <div class="row">
                            <dt class="col">Subida a PRO:</dt>
                            <dd class="col" id="proDate"></dd>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      
        <!-- Footer  -->
        <%@ include file="footer.jsp" %>
      </section>

          
      <!-- Modal para crear el ticket -->
      <%@ include file="modalCreate.jsp" %>
      <%@ include file="modalLogout.jsp" %>
      <%@ include file="modalCreateRelease.jsp" %>

      <!-- Modal para clonar el ticket -->
      <%@ include file="modalCreateProject.jsp" %>

      <!-- Modal para borrar el ticket -->
      <%@ include file="modalDeleteRelease.jsp" %>

      <!-- Modal para borrar el ticket -->
      <%@ include file="modalEditDatesRelease.jsp" %>
      
    </main>
  </div>

</body>
</html>



