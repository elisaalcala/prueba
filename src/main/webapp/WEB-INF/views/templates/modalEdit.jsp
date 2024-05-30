<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Modal para editar el ticket -->
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="editModalLabel">Editar Ticket</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <!-- Formulario para editar el ticket -->
          <form id="editForm">
            <div class="mb-3">
              <label for="editTicketTitle" class="form-label">Titulo:</label>
              <textarea class="form-control" id="editTicketTitle">${ticket.titleTicket}</textarea>
          </div>
          <div class="mb-3">
              <label for="editTicketDescription" class="form-label">Descripción:</label>
              <textarea class="form-control" id="editTicketDescription">${ticket.descriptionTicket}</textarea>
          </div>
          <div class="mb-3">
              <label for="editTicketPriority" class="form-label">Prioridad:</label>
              <select class="form-select" id="editTicketPriority">
                  <option value="" selected disabled>Seleccionar prioridad</option>
                  <option value="Alta" ${ticket.priorityTicket == 'Alta' ? 'selected' : ''}>Alta</option>
                  <option value="Media" ${ticket.priorityTicket == 'Media' ? 'selected' : ''}>Media</option>
                  <option value="Baja" ${ticket.priorityTicket == 'Baja' ? 'selected' : ''}>Baja</option>
              </select>
          </div>
          <div class="mb-3">
              <label for="editTicketEnvironment" class="form-label">Entorno:</label>
              <select class="form-select" id="editTicketEnvironment">
                  <option value="" selected disabled>Seleccionar entorno</option>
                  <option value="TST" ${ticket.environmentTicket == 'TST' ? 'selected' : ''}>TST</option>
                  <option value="UAT" ${ticket.environmentTicket == 'UAT' ? 'selected' : ''}>UAT</option>
                  <option value="ASE" ${ticket.environmentTicket == 'ASE' ? 'selected' : ''}>ASE</option>
                  <option value="PRO" ${ticket.environmentTicket == 'PRO' ? 'selected' : ''}>PRO</option>
              </select>
          </div>
          <div class="mb-3">
              <label for="editTicketTeamNameAssign" class="form-label">Equipo:</label>
              <select class="form-select" id="editTicketTeamNameAssign">
                  <option value="" selected disabled>Seleccionar equipo</option>
                  <c:forEach items="${createTicketTeamsList}" var="team">
                      <option value="${team.nameTeam}" ${ticket.teamNameAssign == team.nameTeam ? 'selected' : ''}>${team.nameTeam}</option>
                  </c:forEach>
              </select>
          </div>
            <!-- Agrega más campos de edición según sea necesario -->
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
          <button type="button" class="btn btn-primary" id="saveChangesButton">Guardar Cambios</button>
        </div>
      </div>
    </div>
  </div>