<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <div class="modal fade" id="deleteModalRelease" tabindex="-1" aria-labelledby="deleteModalLabelRelease" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" id="modal-header-delete-release">
                    <h5 class="modal-title" id="deleteModalLabel">Eliminar Release</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" id="modal-body-delete-release">
                    <p>¿Estás seguro de que quieres eliminar permanentemente este release?</p>
                    <p>También se borraran todos los proyectos que contenga</p>
                </div>
                <div class="modal-footer" id="modal-footer-delete-release">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-danger" id="deleteTicketButtonRelease">Eliminar</button>
                </div>
                <div class="alert alert-success" id="successMessageDeleteRelease" style="display: none;" role="alert">
                    La release ha sido eliminado correctamente.
                </div>
            </div>
        </div>
      </div>