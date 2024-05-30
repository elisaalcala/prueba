<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="createModalLabel" aria-hidden="true">
  <div class="modal-dialog">
      <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="createModalLabel">Crear Ticket</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form id="createForm">
                  <div class="mb-3">
                      <label for="createTicketTitle" class="form-label">Titulo:</label>
                      <textarea class="form-control" id="createTicketTitle"></textarea>
                  </div>
                  <div class="mb-3">
                      <label for="createTicketDescription" class="form-label">Descripción:</label>
                      <textarea class="form-control" id="createTicketDescription"></textarea>
                  </div>
                  <div class="mb-3">
                      <label for="createTicketPriority" class="form-label">Prioridad:</label>
                      <select class="form-select" id="createTicketPriority">
                          <option value="" selected disabled>Seleccionar prioridad</option>
                          <option value="Alta">Alta</option>
                          <option value="Media">Media</option>
                          <option value="Baja">Baja</option>
                      </select>
                  </div>
                  <div class="mb-3">
                      <label for="createTicketEnvironment" class="form-label">Entorno:</label>
                      <select class="form-select" id="createTicketEnvironment">
                          <option value="" selected disabled>Seleccionar entorno</option>
                          <option value="TST">TST</option>
                          <option value="UAT">UAT</option>
                          <option value="ASE">ASE</option>
                          <option value="PRO">PRO</option>
                      </select>
                  </div>
                  <div class="mb-3">
                      <label for="createTicketTeamNameAssign" class="form-label">Equipo:</label>
                      <select class="form-select" id="createTicketTeamNameAssign">
                          <option value="" selected disabled>Seleccionar equipo</option>
                          <c:forEach items="${createTicketTeamsList}" var="team">
                              <option value="${team.nameTeam}">${team.nameTeam}</option>
                          </c:forEach>
                      </select>
                  </div>
              </form>
          </div>
          <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
              <button type="button" class="btn btn-primary" id="saveTicketButton">Enviar</button>
          </div>
      </div>
  </div>
</div>

