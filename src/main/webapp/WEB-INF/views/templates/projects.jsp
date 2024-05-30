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
          <h2>Proyectos</h2>
        </div>
        <!-- DataTales Example -->
        <div class="card shadow mb-4">
            <div class="card-body">
                    <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
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
                          <c:forEach items="${projects}" var="proyecto">
                            <tr data-proyecto-id="${proyecto.idProject}" class="proyecto-row pointer-row">
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

      </section>
      <%@ include file="modalCreate.jsp" %>
      <%@ include file="modalLogout.jsp" %>
      <%@ include file="modalCreateRelease.jsp" %>

    </main>
  </div>

</body>
</html>



