<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <div class="modal fade" id="moveModal" tabindex="-1" aria-labelledby="moveModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="moveModalLabel">Traspasar Ticket</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Formulario para asignar el ticket -->
                    <form id="assignForm">
                        <div class="mb-2">
                            <label for="assignedTeam" class="form-label">Traspasar a Equipo:</label>
                            
                            <div class="div-col">
                                    <select class="assignedTeam" name="state">
                                        <option selected disabled></option>
                                        <c:forEach items="${createTicketTeamsList}" var="teams"> 
                                            <option value="${teams.nameTeam}">${teams.nameTeam}</option>
                                        </c:forEach>
                                    </select>
                            </div>  
                        </div>
                        <div id="alertMessage" class="alert alert-danger m-top" role="alert" style="display: none;"></div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-primary" id="saveMoveButton">Traspasar</button>
                </div>
            </div>
        </div>
      </div>