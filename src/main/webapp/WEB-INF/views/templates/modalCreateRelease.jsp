<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade" id="createModalRelease" tabindex="-1" aria-labelledby="createModalLabelRelease" aria-hidden="true">
  <div class="modal-dialog">
      <div class="modal-content">
          <div class="modal-header">
              <h5 class="modal-title" id="createModalLabelProject">Crear Nueva Release</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form id="createForm">
                
                <div class="mb-3">
                    <label for="createInitialDate" class="form-label">Fecha de inicio:</label>
                    <input type="date" class="form-control" id="createInitialDate">
                </div>
                <div class="mb-3">
                    <label for="createRequirementsDate" class="form-label">Fecha de requerimientos:</label>
                    <input type="date" class="form-control" id="createRequirementsDate">
                </div>
                <div class="mb-3">
                    <label for="createPrjAssignmentDate" class="form-label">Fecha de asignación de proyecto:</label>
                    <input type="date" class="form-control" id="createPrjAssignmentDate">
                </div>
                <div class="mb-3">
                    <label for="createDevelopDate" class="form-label">Fecha de desarrollo:</label>
                    <input type="date" class="form-control" id="createDevelopDate">
                </div>
                <div class="mb-3">
                    <label for="createTstDate" class="form-label">Fecha de pruebas:</label>
                    <input type="date" class="form-control" id="createTstDate">
                </div>
                <div class="mb-3">
                    <label for="createUatDate" class="form-label">Fecha de aceptación de usuario:</label>
                    <input type="date" class="form-control" id="createUatDate">
                </div>
                <div class="mb-3">
                    <label for="createProDate" class="form-label">Fecha de producción:</label>
                    <input type="date" class="form-control" id="createProDate">
                </div>
              </form>
          </div>
          <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
              <button type="button" class="btn btn-primary" id="saveReleaseButton">Enviar</button>
          </div>
      </div>
  </div>
</div>

