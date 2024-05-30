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
          <h2>
            Mi perfil
          </h2>
        </div>

        <div class="titulo subtitulo">
          <h2 class="subtitulo-size">
            <i class="fas fa-circle-user"></i>
            ${employee.userEmployee} - ${employee.userEmployee}@alcalapp.com
          </h2>
        </div>

        <div class="columnas col-margin-top-left m-50">

          <div class="columnas-izquierda clearfix flex-wrap-nowrap flex-40">
            <div class="row">

              <div class="col">
                
                <div class="ticket-detail-heading color-cyan">

                    Información profesional
                </div>
                <div class="row">
                  <dt class="col">ID Empleado:</dt>
                  <dd class="col" id="ticketPriority">${employee.employeeId}</dd>
                </div>
                <div class="row">
                    <dt class="col">Fecha de alta:</dt>
                    <dd class="col" id="ticketEnvironment">${employee.hireDate}</dd>
                </div>
                <div class="row">
                    <dt class="col">Equipo perteneciente:</dt>
                    <dd class="col" id="ticketStatus">${employee.nameTeam}</dd>
                </div>
                <div class="row">
                    <dt class="col">Puesto:</dt>
                    <dd class="col" id="ticketStatus">${employee.employeePosition}</dd>
                </div>

              </div>

                <div class="col">
                    <div class="ticket-info">
                        <div class="ticket-detail-heading color-cyan">
                        
                        Información personal
                        </div>
                        <div class="row">
                            <dt class="col">Nombre y Apellidos:</dt>
                            <dd class="col" id="ticketEnvironment">${employee.employeeName}  ${employee.employeeLastName}</dd>
                        </div>
                        <div class="row">
                            <dt class="col">DNI:</dt>
                            <dd class="col" id="ticketStatus">${employee.employeeDni}</dd>
                        </div>
                        <div class="row">
                            <dt class="col">Fecha Nacimiento:</dt>
                            <dd class="col" id="ticketStatus">${employee.birthDate}</dd>
                        </div>
                    </div>
                </div>

            </div>
          </div>
            
          <div class="columnas-derecha flex-wrap-nowrap margin-30">
            
          </div>
        </div>
      

      </section>

      <%@ include file="footer.jsp" %>

      <!-- Modal para crear el ticket -->
      <%@ include file="modalCreate.jsp" %>
      <%@ include file="modalLogout.jsp" %>
      <%@ include file="modalCreateRelease.jsp" %>

    </main>
  </div>

</body>
</html>



