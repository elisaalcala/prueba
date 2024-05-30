<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <div class="modal fade" id="assignModal" tabindex="-1" aria-labelledby="assignModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="assignModalLabel">Asignar Ticket</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Formulario para asignar el ticket -->
                    <form id="assignForm">
                        <div class="mb-2">
                            <label for="assignedEmployee" class="form-label">Empleado Asignado:</label>
                            
                            <div class="div-col">
                                    <select class="assignedEmployee m-right" name="state">
                                        <c:choose>
                                            <c:when test="${empty ticket.employeeUserAssign}">
                                                <option value="Sin asignar" selected disabled>Sin asignar</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${ticket.employeeUserAssign}" selected disabled>${ticket.employeeUserAssign}</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:forEach items="${allEmployees}" var="employeelist"> 
                                            <c:if test="${empty ticket.employeeUserAssign or not employeelist.userEmployee eq ticket.employeeUserAssign}">
                                                <option value="${employeelist.userEmployee}">${employeelist.employeeName} ${employeelist.employeeLastName}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                
                                <button type="button" class="btn btn-primary" id="assignToMeButton">Asignarme a mí</button>
                            </div>
                            
                            
                        </div>
                        <div id="alertMessageAssign" class="alert alert-danger m-top" role="alert" style="display: none;"></div>
                        <a href="#" id="quitAssign">Quitar Asignación</a>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-primary" id="saveAssignButton">Asignar</button>
                </div>
            </div>
        </div>
      </div>