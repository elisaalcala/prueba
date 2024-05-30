<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <div class="modal fade" id="deleteModalProject" tabindex="-1" aria-labelledby="deleteModalLabelProject" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" id="modal-header-delete-project">
                    <h5 class="modal-title" id="deleteModalLabel">Eliminar Ticket</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" id="modal-body-delete-project">
                    <p>¿Estás seguro de que quieres eliminar permanentemente este proyecto?</p>
                    <p>No podrás volver a recuperarlo</p>
                </div>
                <div class="modal-footer" id="modal-footer-delete-project">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-danger" id="deleteTicketButtonProject">Eliminar</button>
                </div>
                <div class="alert alert-success" id="successMessageDeleteProject" style="display: none;" role="alert">
                    El proyecto ha sido eliminado correctamente.
                </div>
            </div>
        </div>
      </div>