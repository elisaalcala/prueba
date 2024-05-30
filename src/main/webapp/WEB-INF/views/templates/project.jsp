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
    <%@ include file="scriptsProject.jsp" %>
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
          <h2>${project.nameProject} - ${project.titleProject}</h2>
        </div>

        <div class="div-col justify-content-between">

          <div class="div-col">

            <div class="ticket-button m-left">
              <button type="button" class="btn bg-cyan-800" id="assignButton" data-bs-toggle="modal" data-bs-target="#assignModalProject" 
              ${employee.nameTeam != project.teamNameAssign ? 'disabled' : ''}>Asignar</button>

            </div>
            <div class="ticket-button dropdown m-left">
              <button class="btn bg-cyan-800 dropdown-toggle" type="button" id="changeStatusButtonProject" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
              ${employee.nameTeam != project.teamNameAssign ? 'disabled' : ''}>
                Cambiar estado
              </button>
              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <h6 class="collapse-header">Cambiar a:</h6>
                <c:forEach items="${allStatus}" var="status"> 
                  <a class="collapse-item project-item" id="${status}" href="#">${status}</a>
                </c:forEach>
              </div>
            </div>

          </div>
          <div class="div-col">

            <div class="ticket-button m-left">
              <button type="button" class="btn bg-cyan-800" id="cloneButton" data-bs-toggle="modal" data-bs-target="#cloneModalProject">
                <i class="fa-solid fa-clone"></i>
                Clonar</button>
            </div>
            <div class="ticket-button m-left">
              <button type="button" class="btn bg-cyan-800" id="editButton" data-bs-toggle="modal" data-bs-target="#editModalProject"
              ${employee.nameTeam != project.teamNameAssign ? 'disabled' : ''}>
                <i class="fa-solid fa-pen-to-square"></i>
                Editar
              </button>
            </div>
            <div class="ticket-button m-left">
              <button type="button" class="btn btn-danger" id="deleteButton" data-bs-toggle="modal" data-bs-target="#deleteModalProject"
              ${employee.nameTeam != project.teamNameAssign ? 'disabled' : ''}>
                <i class="fa-solid fa-trash"></i>
                Eliminar</button>
            </div>
            
          </div>
          
        </div>

        <div class="columnas col-margin-top-left">


          <div class="columnas-izquierda clearfix flex-wrap-nowrap">
            <div class="row">
              <div class="col">
              <div class="ticket-info">
                <div class="ticket-detail-heading color-cyan">
                  Detalles
                </div>
                <div class="row">
                    <dt class="col">Alcance:</dt>
                    <dd class="col" id="ticketType">${project.typeProject}</dd>
                </div>
                <div class="row">
                    <dt class="col">Prioridad:</dt>
                    <dd class="col" id="ticketPriority">${project.priorityProject}</dd>
                </div>
                <div class="row">
                    <dt class="col">Entorno prueba:</dt>
                    <dd class="col" id="ticketEnvironment">${project.environmentProject}</dd>
                </div>
                <div class="row">
                    <dt class="col">Estado actual:</dt>
                    <dd class="col" id="ticketStatus">${project.statusProject}</dd>
                </div>
              </div>
              </div>

              <div class="col">
                <div class="ticket-info">
                  <div class="ticket-detail-heading color-cyan">
                    Personal
                  </div>
                  <div class="row">
                    <dt class="col">Empleado Asignado:</dt>
                      <c:choose>
                        <c:when test="${empty project.employeeUserAssign}">
                          <dd class="col" id="employeeUserAssign">
                            Sin asignar
                            <br>
                            <c:if test="${employee.nameTeam eq project.teamNameAssign}">
                              <a href="#" id="assignToMeLinkProject" class="color-cyan">
                                <i class="fa-solid fa-pencil "></i>
                                Asignarme a mi</a>
                            </c:if>
                          </dd>
                        </c:when>
                        <c:otherwise>
                          <dd class="col" id="employeeUserAssign">${project.employeeUserAssign}</dd>
                        </c:otherwise>
                      </c:choose>
                  </div>
                  <div class="row">
                      <dt class="col">Equipo Asignado:</dt>
                      <dd class="col" id="teamNameAssign">${project.teamNameAssign}</dd>
                  </div>
                  <div class="row">
                    <dt class="col">Empleado creador:</dt>
                    <dd class="col" id="employeeUserCreation">${project.employeeUserCreation}</dd>
                  </div>
                  
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
                    <dt class="col">Última modificación:</dt>
                    <dd class="col" id="modifyDate"></dd>
                </div>
                  <div class="row">
                      <dt class="col">Finalización:</dt>
                      <dd class="col" id="finishDate"></dd>
                  </div>
                </div>
              </div>
            </div>
            
            <div class="ticket-info">
              <div class="ticket-detail-heading color-cyan">
                  Descripcion
              </div>
              <div class="row textarea-height">
                <textarea class="form-control" id="descripcionProject" readonly>${project.descriptionProject}</textarea>
            </div>
              
          </div>
             
          </div>

          <div class="columnas-derecha flex-wrap-nowrap">
            
            
          </div>
      </div>
      
        <!-- Footer  -->
        <%@ include file="footer.jsp" %>
      </section>

      <!-- Modal para editar el ticket -->
      <%@ include file="modalEditProject.jsp" %>

      <!-- Modal para asignar el ticket -->
      <%@ include file="modalAssignProject.jsp" %>
          
      <!-- Modal para crear el ticket -->
      <%@ include file="modalCreate.jsp" %>
      <%@ include file="modalLogout.jsp" %>
      <%@ include file="modalCreateRelease.jsp" %>

      <!-- Modal para clonar el ticket -->
      <%@ include file="modalCloneProject.jsp" %>

      <!-- Modal para eliminar el ticket -->
      <%@ include file="modalDeleteProject.jsp" %>

    </main>
  </div>

</body>
</html>



