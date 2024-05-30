<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Modal para editar el ticket -->
<div class="modal fade" id="editModalProject" tabindex="-1" aria-labelledby="editModalLabelProject" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="editModalLabelProject">Editar Project</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <!-- Formulario para editar el ticket -->
          <form id="editForm">
            <div class="mb-3">
              <label for="editProjectTitle" class="form-label">Titulo:</label>
              <textarea class="form-control" id="editProjectTitle">${project.titleProject}</textarea>
          </div>
          <div class="mb-3">
              <label for="editProjectDescription" class="form-label">Descripción:</label>
              <textarea class="form-control" id="editProjectDescription">${project.descriptionProject}</textarea>
          </div>
          <div class="mb-3">
            <label for="editProjectType" class="form-label">Alcance:</label>
            <select class="form-select" id="editProjectType">
                <option value="" selected disabled>Seleccionar alcance</option>
                <option value="Bajo" ${project.typeProject == 'Bajo' ? 'selected' : ''}>Bajo</option>
                <option value="Medio" ${project.typeProject == 'Medio' ? 'selected' : ''}>Medio</option>
                <option value="Alto" ${project.typeProject == 'Alto' ? 'selected' : ''}>Alto</option>
                <option value="Masivo" ${project.typeProject == 'Masivo' ? 'selected' : ''}>Masivo</option>
              </select>
          </div>
          <div class="mb-3">
              <label for="editProjectPriority" class="form-label">Prioridad:</label>
              <select class="form-select" id="editProjectPriority">
                  <option value="" selected disabled>Seleccionar prioridad</option>
                  <option value="Baja" ${project.priorityProject == 'Baja' ? 'selected' : ''}>Baja</option>
                  <option value="Media" ${project.priorityProject == 'Media' ? 'selected' : ''}>Media</option>
                  <option value="Alta" ${project.priorityProject == 'Alta' ? 'selected' : ''}>Alta</option>
              </select>
          </div>
          <div class="mb-3">
              <label for="editProjectEnvironment" class="form-label">Entorno:</label>
              <select class="form-select" id="editProjectEnvironment">
                  <option value="" selected disabled>Seleccionar entorno</option>
                  <option value="TST" ${project.environmentProject == 'TST' ? 'selected' : ''}>TST</option>
                  <option value="UAT" ${project.environmentProject == 'UAT' ? 'selected' : ''}>UAT</option>
                  <option value="ASE" ${project.environmentProject == 'ASE' ? 'selected' : ''}>ASE</option>
                  <option value="PRO" ${project.environmentProject == 'PRO' ? 'selected' : ''}>PRO</option>
              </select>
          </div>
            <!-- Agrega más campos de edición según sea necesario -->
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
          <button type="button" class="btn btn-primary" id="saveChangesButtonProject">Guardar Cambios</button>
        </div>
      </div>
    </div>
  </div>