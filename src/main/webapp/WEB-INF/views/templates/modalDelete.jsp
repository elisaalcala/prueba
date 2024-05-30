<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" id="modal-header-delete">
                    <h5 class="modal-title" id="deleteModalLabel">Eliminar Ticket</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" id="modal-body-delete">
                    <p>¿Estás seguro de que quieres eliminar permanentemente este ticket?</p>
                    <p>No podrás volver a recuperarlo</p>
                </div>
                <div class="modal-footer" id="modal-footer-delete">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-danger" id="deleteTicketButton">Eliminar</button>
                </div>
                <div class="alert alert-success" id="successMessageDelete" style="display: none;" role="alert">
                    El ticket ha sido eliminado correctamente.
                </div>
            </div>
        </div>
      </div>