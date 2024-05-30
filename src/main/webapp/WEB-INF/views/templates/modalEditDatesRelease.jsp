<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade" id="editModalRelease" tabindex="-1" aria-labelledby="editModalLabelRelease" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="editModalLabelProject">Editar Release</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="editForm">
          
          <div class="mb-3">
            <label for="editInitialDate" class="form-label">Fecha de inicio:</label>
            <input type="date" class="form-control" id="editInitialDate" value="${release.initialDate}">
          </div>
          <div class="mb-3">
            <label for="editRequirementsDate" class="form-label">Fecha de requerimientos:</label>
            <input type="date" class="form-control" id="editRequirementsDate" value="${release.requirementsDate}">
          </div>
          <div class="mb-3">
            <label for="editPrjAssignmentDate" class="form-label">Fecha de asignación de proyecto:</label>
            <input type="date" class="form-control" id="editPrjAssignmentDate" value="${release.prjAssignmentDate}">
          </div>
          <div class="mb-3">
            <label for="editDevelopDate" class="form-control">Fecha de desarrollo:</label>
            <input type="date" class="form-control" id="editDevelopDate" value="${release.developDate}">
          </div>
          <div class="mb-3">
            <label for="editTstDate" class="form-label">Fecha de pruebas:</label>
            <input type="date" class="form-control" id="editTstDate" value="${RELreleaseASE.tstDate}">
          </div>
          <div class="mb-3">
            <label for="editUatDate" class="form-label">Fecha de aceptación de usuario:</label>
            <input type="date" class="form-control" id="editUatDate" value="${release.uatDate}">
          </div>
          <div class="mb-3">
            <label for="editProDate" class="form-label">Fecha de producción:</label>
            <input type="date" class="form-control" id="editProDate" value="${release.proDate}">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-primary" id="updateReleaseButton">Actualizar</button>
      </div>
    </div>
  </div>
</div>

