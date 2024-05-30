<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
 
    //Edit Ticket
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('saveChangesButton').addEventListener('click', function() {
            // Obtener los valores editados del formulario
            var url = `/tickets/${ticket.idTicket}/edit`;
            
            var editTicketTitle = document.getElementById('editTicketTitle').value;
            var editTicketDescription = document.getElementById('editTicketDescription').value;
            var editTicketPriority = document.getElementById('editTicketPriority').value;
            var editTicketEnvironment = document.getElementById('editTicketEnvironment').value;
            var editTicketTeamNameAssign = document.getElementById('editTicketTeamNameAssign').value;

            // Crear un objeto con los datos actualizados del ticket
            var editTicket = {
                titleTicket: editTicketTitle,
                descriptionTicket: editTicketDescription,
                priorityTicket: editTicketPriority,
                environmentTicket: editTicketEnvironment,
                teamNameAssign: editTicketTeamNameAssign
            };
            
            // Realizar la solicitud PUT para actualizar el ticket
            fetch(url, {
                method: 'PUT',
                headers: {
                'Content-Type': 'application/json'
                },
                body: JSON.stringify(editTicket)
            })
            .then(response => {
                if (!response.ok) {
                throw new Error('Error al actualizar el ticket');
                }
                // Si la actualización es exitosa, cerrar el modal
                var editModal = bootstrap.Modal.getInstance(document.getElementById('editModal'));
                editModal.hide();
                document.body.classList.remove('modal-open');
                document.querySelector('.modal-backdrop').remove();
               return response.json();
        })
        .then(data => {
            window.location.href = data.redirectUrl;
        })
        .catch(error => {
            console.error('Error:', error);
        });
        });
    });

    //Asignar Ticket Modal
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('saveAssignButton').addEventListener('click', function() {
            
            var employeeAssign = document.querySelector('.assignedEmployee').value;
            var url = `/tickets/${ticket.idTicket}/assign`;
            
            fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(employeeAssign)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al asignar el ticket');
                }
                // Si la asignación es exitosa, obtener la URL de redirección desde la respuesta JSON
                return response.json();
            })
            .then(data => {
                // Redirigir al método GET utilizando la URL proporcionada en la respuesta JSON
                window.location.href = data.redirectUrl;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });
        // Script para el botón "Asignarme a mí"
        document.getElementById('assignToMeButton').addEventListener('click', function() {
        debugger
            var selectedTeam = `${ticket.teamNameAssign}`;
            var employeeTeam = `${employee.nameTeam}`; 
            var selectElement = document.querySelector('.assignedEmployee');
        
            for (var i = 0; i < selectElement.options.length; i++) {
                if (selectElement.options[i].textContent === `${employee.employeeName} ${employee.employeeLastName}`) {
                    selectElement.options[i].selected = true;
                    break;
                }
            }
        
        });

        // Script para el enlace "Quitar Asignación"
        document.getElementById('quitAssign').addEventListener('click', function() {
            var selectElement = document.querySelector('.assignedEmployee');
            var optionExists = false;

            // Itera sobre las opciones y busca la opción seleccionada
            for (var i = 0; i < selectElement.options.length; i++) {
                if (selectElement.options[i].textContent === 'Sin asignar') {
                    selectElement.options[i].selected = true;
                    optionExists = true;
                    break;
                }
            }

            // Si no existe la opción, la crea
            if (!optionExists) {
                var newOption = document.createElement('option');
                newOption.value = 'Sin asignar';
                newOption.textContent = 'Sin asignar';
                newOption.selected = true;
                selectElement.appendChild(newOption);
            }
        });
        // Evento para ocultar la alerta cuando se pulse cualquier otra parte del documento
        document.addEventListener('click', function(event) {
            var clickedElement = event.target;
            var assignToMeButton = document.getElementById('assignToMeButton');
            var quitAssignButton = document.getElementById('quitAssign');
            var selectElement = document.querySelector('.assignedEmployee');

            // Verificar si el clic no proviene del botón "Asignarme a mí", el botón "Quitar Asignación" ni el select
            if (clickedElement !== assignToMeButton && clickedElement !== quitAssignButton && clickedElement !== selectElement) {
                alertMessageAssign.style.display = 'none';
            }
        });


    });
 
    //Cambiar estado ticket
    document.addEventListener('DOMContentLoaded', function() {
        var changeStatusButtons = document.querySelectorAll('.collapse-item.ticket-item');

        changeStatusButtons.forEach(function(button) {
            button.addEventListener('click', function(event) {
                var status = event.target.textContent.trim();

                var url = `/tickets/${ticket.idTicket}/edit`;

                var updatedTicket = {
                    statusTicket: status
                };

                fetch(url, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(updatedTicket)
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error al actualizar el ticket');
                    }
                    return response.json();
                })
                .then(data => {
                    window.location.href = data.redirectUrl;
                })
                .catch(error => {
                    console.error('Error:', error);
                });
            });
        });

    });
    
    //Move Ticket
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('saveMoveButton').addEventListener('click', function() {
            // Obtener los valores editados del formulario
            var url = `/tickets/${ticket.idTicket}/move`;
            var editedTeamNameAssign = document.querySelector('.assignedTeam').value;
            
            // Realizar la solicitud PUT para actualizar el ticket
            fetch(url, {
                method: 'PUT',
                headers: {
                'Content-Type': 'application/json'
                },
                body: JSON.stringify(editedTeamNameAssign)
            })
            .then(response => {
                if (!response.ok) {
                throw new Error('Error al actualizar el ticket');
                }
                // Si la actualización es exitosa, cerrar el modal
                var moveModal = bootstrap.Modal.getInstance(document.getElementById('moveModal'));
                moveModal.hide();
                document.body.classList.remove('modal-open');
                document.querySelector('.modal-backdrop').remove();
               // Si la asignación es exitosa, obtener la URL de redirección desde la respuesta JSON
               return response.json();
        })
        .then(data => {
            // Redirigir al método GET utilizando la URL proporcionada en la respuesta JSON
            window.location.href = data.redirectUrl;
        })
        .catch(error => {
            console.error('Error:', error);
        });
        });
    });
    
    //Clone Ticket
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('cloneTicketButton').addEventListener('click', function() {
                
            // Obtener los valores editados del formulario
            var url = `/tickets/create`;
            var cloneTicketTitle = document.getElementById('cloneTicketTitle').value;
            var cloneTicketDescription = document.getElementById('cloneTicketDescription').value;
            var cloneTicketPriority = document.getElementById('cloneTicketPriority').value;
            var cloneTicketEnvironment = document.getElementById('cloneTicketEnvironment').value;
            var cloneTicketTeamNameAssign = document.getElementById('cloneTicketTeamNameAssign').value;

            // Crear un objeto con los datos actualizados del ticket
            var cloneTicket = {
                titleTicket: cloneTicketTitle,
                descriptionTicket: cloneTicketDescription,
                priorityTicket: cloneTicketPriority,
                environmentTicket: cloneTicketEnvironment,
                teamNameAssign: cloneTicketTeamNameAssign
            };
            
            // Realizar la solicitud POST para crear el ticket
            fetch(url, {
                method: 'POST',
                headers: {
                'Content-Type': 'application/json'
                },
                body: JSON.stringify(cloneTicket)
            })
            .then(response => {
                if (!response.ok) {
                throw new Error('Error al crear el ticket');
                }
                // Si es exitosa, cerrar el modal
                var cloneModal = bootstrap.Modal.getInstance(document.getElementById('cloneModal'));
                cloneModal.hide();
                document.body.classList.remove('modal-open');
                document.querySelector('.modal-backdrop').remove();
                window.location.href = response.url;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });
    });

    //Delete Ticket
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('deleteTicketButton').addEventListener('click', function() {
            var url = `/tickets/${ticket.idTicket}/delete`;
            
            // Realizar la solicitud DELETE para eliminar el ticket
            fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(`${ticket.idTicket}`)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al eliminar el ticket');
                }
                var modalHeader = document.getElementById('modal-header-delete');
                var modalBody = document.getElementById('modal-body-delete');
                var modalFooter = document.getElementById('modal-footer-delete');
                modalBody.classList.add('d-none'); // Ocultar el modal
                modalFooter.classList.add('d-none'); 
                modalHeader.classList.add('d-none');

                // Si la eliminación es exitosa, mostrar mensaje de éxito y ocultar el cuerpo y el pie de página del modal
                document.getElementById('successMessageDelete').style.display = 'block';

                // Cerrar el modal después de 3 segundos y redirigir a la página relevante
                setTimeout(function(){
                    var deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
                    deleteModal.hide();
                    
                }, 3000);
                return response.json();
                
            })
            .then(data => {
                // Redirigir al método GET utilizando la URL proporcionada en la respuesta JSON
                window.location.href = data.redirectUrl;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });
    });

    //Date Format
    document.addEventListener("DOMContentLoaded", function() {
        // Función para formatear la fecha en el formato deseado
        function formatDateTime(dateTime) {
            var options = { 
                year: 'numeric', 
                month: '2-digit', 
                day: '2-digit', 
                hour: '2-digit', 
                minute: '2-digit', 
                second: '2-digit' 
            };
            return new Date(dateTime).toLocaleString(undefined, options);
        }

        // Función para comprobar si la fecha es nula y devolver un mensaje apropiado
        function formatFinishDate(finishDate) {
            if (finishDate == ``) {
                return "No finalizado";
            } else {
                return formatDateTime(finishDate);
            }
        }

        // Formatear las fechas
        var formattedInitialDate = formatDateTime(`${ticket.initialDate}`);
        var formattedModifyDate = formatDateTime(`${ticket.modifyDate}`);
        var formattedFinishDate = formatFinishDate(`${ticket.finishDate}`);

        // Actualizar el contenido HTML con las fechas formateadas
        document.getElementById("initialDate").textContent = formattedInitialDate;
        document.getElementById("modifyDate").textContent = formattedModifyDate;
        document.getElementById("finishDate").textContent = formattedFinishDate;
    });

    //Asignar Ticket Link
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('assignToMeLink').addEventListener('click', function() {
            
            var employeeAssignToMe = `${employee.userEmployee}`;
            var url = `/tickets/${ticket.idTicket}/assign`;
            
            fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(employeeAssignToMe)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al asignar el ticket');
                }
                return response.json();
            })
            .then(data => {
                window.location.href = data.redirectUrl;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });

    });

    //TimeLine
    document.addEventListener('DOMContentLoaded', function() {
        timeline(document.querySelectorAll('.timeline'), {
        verticalStartPosition: 'right',
        verticalTrigger: '150px'
        });
    })

</script>