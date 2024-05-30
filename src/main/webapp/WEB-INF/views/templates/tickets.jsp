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
<body>
  <div class="container">
    <!-- Sidebar -->
    <%@ include file="sidebar.jsp" %>
    
    <main>
      <!-- Topbar  -->
      <%@ include file="navbar.jsp" %>

      <section class="container-fluid">
        <div class="titulo">
          <h2>Tickets</h2>
        </div>
        <!-- DataTales Example -->
        <div class="card shadow mb-4">
            <div class="card-body">
              <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                <thead>
                  <tr>
                    <th>Nombre</th>
                    <th>Descripcion</th>
                    <th>Estado</th>
                    <th>Fecha Inicio</th>
                    <th>Encargado</th>
                    <th>Equipo</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- Aquí se generan las filas de la tabla con JSP -->
                  <c:forEach items="${tickets}" var="ticket">
                    <tr data-ticket-id="${ticket.idTicket}" class="ticket-row pointer-row">
                      <td>${ticket.nameTicket}</td>
                      <td>${ticket.descriptionTicket}</td>
                      <td>${ticket.statusTicket}</td>
                      <td>${ticket.initialDate}</td>
                      <td>${ticket.employeeUserAssign}</td>
                      <td>${ticket.teamNameAssign}</td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
                
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



