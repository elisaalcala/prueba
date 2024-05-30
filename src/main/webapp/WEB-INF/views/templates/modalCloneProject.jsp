<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade" id="cloneModalProject" tabindex="-1" aria-labelledby="cloneModalLabelProject" aria-hidden="true">
  <div class="modal-dialog">
      <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="cloneModalLabelProject">Clonar Ticket - ${project.nameProject}</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form id="cloneForm">
                    <div class="mb-3">
                        <label for="cloneProjectTitle" class="form-label">Titulo:</label>
                        <textarea class="form-control" id="cloneProjectTitle">${project.titleProject}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="cloneProjectDescription" class="form-label">Descripción:</label>
                        <textarea class="form-control" id="cloneProjectDescription">${project.descriptionProject}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="cloneProjectType" class="form-label">Alcance:</label>
                        <select class="form-select" id="cloneProjectType">
                            <option value="" selected disabled>Seleccionar alcance</option>
                            <option value="Bajo" ${project.typeProject == 'Bajo' ? 'selected' : ''}>Bajo</option>
                            <option value="Medio" ${project.typeProject == 'Medio' ? 'selected' : ''}>Medio</option>
                            <option value="Alto" ${project.typeProject == 'Alto' ? 'selected' : ''}>Alto</option>
                            <option value="Masivo" ${project.typeProject == 'Masivo' ? 'selected' : ''}>Masivo</option>
                          </select>
                      </div>
                    <div class="mb-3">
                        <label for="cloneProjectPriority" class="form-label">Prioridad:</label>
                        <select class="form-select" id="cloneProjectPriority">
                            <option value="" selected disabled>Seleccionar prioridad</option>
                            <option value="Alta" ${project.priorityProject == 'Alta' ? 'selected' : ''}>Alta</option>
                            <option value="Media" ${project.priorityProject == 'Media' ? 'selected' : ''}>Media</option>
                            <option value="Baja" ${project.priorityProject == 'Baja' ? 'selected' : ''}>Baja</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="cloneProjectReleaseName" class="form-label">Release:</label>
                        <select class="form-select" id="cloneProjectReleaseName">
                            <option value="" selected disabled>Seleccionar release</option>
                            <c:forEach items="${openReleases}" var="release">
                                <option value="${release.nameRelease}" ${project.releaseName == release.nameRelease ? 'selected' : ''}>${release.nameRelease}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="cloneProjectEnvironment" class="form-label">Entorno:</label>
                        <select class="form-select" id="cloneProjectEnvironment">
                            <option value="" selected disabled>Seleccionar entorno</option>
                            <option value="TST" ${project.environmentProject == 'TST' ? 'selected' : ''}>TST</option>
                            <option value="UAT" ${project.environmentProject == 'UAT' ? 'selected' : ''}>UAT</option>
                            <option value="ASE" ${project.environmentProject == 'ASE' ? 'selected' : ''}>ASE</option>
                            <option value="PRO" ${project.environmentProject == 'PRO' ? 'selected' : ''}>PRO</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="cloneProjectTeamNameAssign" class="form-label">Equipo:</label>
                        <select class="form-select" id="cloneProjectTeamNameAssign">
                            <option value="" selected disabled>Seleccionar equipo</option>
                            <c:forEach items="${createTicketTeamsList}" var="team">
                                <option value="${team.nameTeam}" ${project.teamNameAssign == team.nameTeam ? 'selected' : ''}>${team.nameTeam}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
              </form>
          </div>
          <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
              <button type="button" class="btn btn-primary" id="cloneProjectButton">Enviar</button>
          </div>
      </div>
  </div>
</div>

