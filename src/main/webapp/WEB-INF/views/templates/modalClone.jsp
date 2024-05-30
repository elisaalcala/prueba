<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade" id="cloneModal" tabindex="-1" aria-labelledby="cloneModalLabel" aria-hidden="true">
  <div class="modal-dialog">
      <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="cloneModalLabel">Clonar Ticket - ${ticket.nameTicket}</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form id="cloneForm">
                    <div class="mb-3">
                        <label for="createTicketTitle" class="form-label">Titulo:</label>
                        <textarea class="form-control" id="cloneTicketTitle">${ticket.titleTicket}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="createTicketDescription" class="form-label">Descripción:</label>
                        <textarea class="form-control" id="cloneTicketDescription">${ticket.descriptionTicket}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="createTicketPriority" class="form-label">Prioridad:</label>
                        <select class="form-select" id="cloneTicketPriority">
                            <option value="" selected disabled>Seleccionar prioridad</option>
                            <option value="Alta" ${ticket.priorityTicket == 'Alta' ? 'selected' : ''}>Alta</option>
                            <option value="Media" ${ticket.priorityTicket == 'Media' ? 'selected' : ''}>Media</option>
                            <option value="Baja" ${ticket.priorityTicket == 'Baja' ? 'selected' : ''}>Baja</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="createTicketEnvironment" class="form-label">Entorno:</label>
                        <select class="form-select" id="cloneTicketEnvironment">
                            <option value="" selected disabled>Seleccionar entorno</option>
                            <option value="TST" ${ticket.environmentTicket == 'TST' ? 'selected' : ''}>TST</option>
                            <option value="UAT" ${ticket.environmentTicket == 'UAT' ? 'selected' : ''}>UAT</option>
                            <option value="ASE" ${ticket.environmentTicket == 'ASE' ? 'selected' : ''}>ASE</option>
                            <option value="PRO" ${ticket.environmentTicket == 'PRO' ? 'selected' : ''}>PRO</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="createTicketTeamNameAssign" class="form-label">Equipo:</label>
                        <select class="form-select" id="cloneTicketTeamNameAssign">
                            <option value="" selected disabled>Seleccionar equipo</option>
                            <c:forEach items="${createTicketTeamsList}" var="team">
                                <option value="${team.nameTeam}" ${ticket.teamNameAssign == team.nameTeam ? 'selected' : ''}>${team.nameTeam}</option>
                            </c:forEach>
                        </select>
                    </div>
                
              </form>
          </div>
          <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
              <button type="button" class="btn btn-primary" id="cloneTicketButton">Enviar</button>
          </div>
      </div>
  </div>
</div>

