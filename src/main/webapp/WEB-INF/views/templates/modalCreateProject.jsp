<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade" id="createModalProject" tabindex="-1" aria-labelledby="createModalLabelProject" aria-hidden="true">
  <div class="modal-dialog">
      <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="createModalLabelProject">Añadir Proyecto a ${release.nameRelease}</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form id="createForm">
                    <div class="mb-3">
                        <label for="createProjectTitle" class="form-label">Titulo:</label>
                        <textarea class="form-control" id="createProjectTitle"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="createProjectDescription" class="form-label">Descripción:</label>
                        <textarea class="form-control" id="createProjectDescription"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="createProjectType" class="form-label">Alcance:</label>
                        <select class="form-select" id="createProjectType">
                            <option value="" selected disabled>Seleccionar alcance</option>
                            <option value="Bajo" >Bajo</option>
                            <option value="Medio">Medio</option>
                            <option value="Alto" >Alto</option>
                            <option value="Masivo" >Masivo</option>
                          </select>
                      </div>
                    <div class="mb-3">
                        <label for="createProjectPriority" class="form-label">Prioridad:</label>
                        <select class="form-select" id="createProjectPriority">
                            <option value="" selected disabled>Seleccionar prioridad</option>
                            <option value="Alta" >Alta</option>
                            <option value="Media" >Media</option>
                            <option value="Baja" >Baja</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="createProjectEnvironment" class="form-label">Entorno:</label>
                        <select class="form-select" id="createProjectEnvironment">
                            <option value="" selected disabled>Seleccionar entorno</option>
                            <option value="TST" >TST</option>
                            <option value="UAT" >UAT</option>
                            <option value="ASE" >ASE</option>
                            <option value="PRO" >PRO</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="createProjectTeamNameAssign" class="form-label">Equipo:</label>
                        <select class="form-select" id="createProjectTeamNameAssign">
                            <option value="" selected disabled>Seleccionar equipo</option>
                            <option value="${team.nameTeam}">${team.nameTeam}</option>
                            <c:forEach items="${createTicketTeamsList}" var="teams">
                                <option value="${teams.nameTeam}">${teams.nameTeam}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
              </form>
          </div>
          <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
              <button type="button" class="btn btn-primary" id="saveProjectButton">Enviar</button>
          </div>
      </div>
  </div>
</div>

