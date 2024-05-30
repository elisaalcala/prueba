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
    <link href="${pageContext.request.contextPath}/css/timeline.min.css" rel="stylesheet">
    
    <%@ include file="scripts.jsp" %>
    <%@ include file="scriptsTicket.jsp" %>
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
          <h2>${ticket.nameTicket} - ${ticket.titleTicket}</h2>
        </div>

        <div class="div-col justify-content-between">

          <div class="div-col">

            <div class="ticket-button m-left">
              <button type="button" class="btn bg-cyan-800" id="assignButton" data-bs-toggle="modal" data-bs-target="#assignModal" 
              ${employee.nameTeam != ticket.teamNameAssign ? 'disabled' : ''}>Asignar</button>

            </div>
            <div class="ticket-button dropdown m-left">
              <button class="btn bg-cyan-800 dropdown-toggle" type="button" id="changeStatusButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
              ${employee.nameTeam != ticket.teamNameAssign ? 'disabled' : ''}>
                Cambiar estado
              </button>
              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <h6 class="collapse-header">Cambiar a:</h6>
                <c:forEach items="${allStatus}" var="status"> 
                  <a class="collapse-item ticket-item" id="${status}" href="#">${status}</a>
                </c:forEach>
              </div>
            </div>
            <div class="ticket-button m-left">
              <button type="button" class="btn bg-cyan-800" id="moveButton" data-bs-toggle="modal" data-bs-target="#moveModal" 
              ${employee.nameTeam != ticket.teamNameAssign ? 'disabled' : ''}>Traspasar</button>
            </div>

          </div>
          <div class="div-col">

            <div class="ticket-button m-left">
              <button type="button" class="btn bg-cyan-800" id="cloneButton" data-bs-toggle="modal" data-bs-target="#cloneModal">
                <i class="fa-solid fa-clone"></i>
                Clonar</button>
            </div>
            <div class="ticket-button m-left">
              <button type="button" class="btn bg-cyan-800" id="editButton" data-bs-toggle="modal" data-bs-target="#editModal"
              ${employee.nameTeam != ticket.teamNameAssign ? 'disabled' : ''}>
                <i class="fa-solid fa-pen-to-square"></i>
                Editar
              </button>
            </div>
            <div class="ticket-button m-left">
              <button type="button" class="btn btn-danger" id="deleteButton" data-bs-toggle="modal" data-bs-target="#deleteModal"
              ${employee.nameTeam != ticket.teamNameAssign ? 'disabled' : ''}>
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
                    <dt class="col">Prioridad:</dt>
                    <dd class="col" id="ticketPriority">${ticket.priorityTicket}</dd>
                </div>
                <div class="row">
                    <dt class="col">Entorno prueba:</dt>
                    <dd class="col" id="ticketEnvironment">${ticket.environmentTicket}</dd>
                </div>
                <div class="row">
                    <dt class="col">Estado actual:</dt>
                    <dd class="col" id="ticketStatus">${ticket.statusTicket}</dd>
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
                        <c:when test="${empty ticket.employeeUserAssign}">
                          <dd class="col" id="employeeUserAssign">
                            Sin asignar
                            <br>
                            <c:if test="${employee.nameTeam eq ticket.teamNameAssign}">
                              <a href="#" id="assignToMeLink" class="color-cyan">
                                <i class="fa-solid fa-pencil "></i>
                                Asignarme a mi</a>
                            </c:if>
                          </dd>
                        </c:when>
                        <c:otherwise>
                          <dd class="col" id="employeeUserAssign">${ticket.employeeUserAssign}</dd>
                        </c:otherwise>
                      </c:choose>
                  </div>
                  <div class="row">
                      <dt class="col">Equipo Asignado:</dt>
                      <dd class="col" id="teamNameAssign">${ticket.teamNameAssign}</dd>
                  </div>
                  <div class="row">
                    <dt class="col">Empleado creador:</dt>
                    <dd class="col" id="employeeUserCreation">${ticket.employeeUserCreation}</dd>
                  </div>
                  <div class="row">
                      <dt class="col">Equipo del creador:</dt>
                      <dd class="col" id="teamNameCreation">${ticket.teamNameCreation}</dd>
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
                <textarea class="form-control" readonly>${ticket.descriptionTicket}</textarea>
            </div>
              
          </div>
              
             
          </div>

          <div class="columnas-derecha flex-wrap-nowrap margin-30">
            <div>
              <div class="ticket-detail-heading color-cyan margin-30">
                Historial
              </div>
              <div class="timeline margin-timeline">
                <div class="timeline__wrap">
                  <div class="timeline__items">

                    <c:forEach items="${historial}" var="mensaje">
                      <div class="timeline__item">
                        <div class="timeline__content">
                          ${mensaje.text}
                          <div class="fw-bold small">${mensaje.dateRecord}</div>
                        </div>
                      </div>
                    </c:forEach>

                  </div>
                </div>
              </div>
          </div>
        </div>
      </div>
      
        <!-- Footer  -->
        <%@ include file="footer.jsp" %>
      </section>

      <!-- Modal para editar el ticket -->
      <%@ include file="modalEdit.jsp" %>

      <!-- Modal para asignar el ticket -->
      <%@ include file="modalAssign.jsp" %>
          
      <!-- Modal para crear el ticket -->
      <%@ include file="modalCreate.jsp" %>
      <%@ include file="modalLogout.jsp" %>
      <%@ include file="modalCreateRelease.jsp" %>

      <!-- Modal para traspasar el ticket -->
      <%@ include file="modalMove.jsp" %>

      <!-- Modal para clonar el ticket -->
      <%@ include file="modalClone.jsp" %>

      <!-- Modal para eliminar el ticket -->
      <%@ include file="modalDelete.jsp" %>

    </main>
  </div>

</body>
</html>



