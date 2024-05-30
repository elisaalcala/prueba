<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    
    //Edit Project
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('saveChangesButtonProject').addEventListener('click', function() {
            // Obtener los valores editados del formulario
            var url = `/projects/${project.idProject}/edit`;
            
            var editProjectTitle = document.getElementById('editProjectTitle').value;
            var editProjectDescription = document.getElementById('editProjectDescription').value;
            var editProjectPriority = document.getElementById('editProjectPriority').value;
            var editProjectType = document.getElementById('editProjectType').value;
            var editProjectEnvironment = document.getElementById('editProjectEnvironment').value;

            // Crear un objeto con los datos actualizados del ticket
            var editProject = {
                titleProject: editProjectTitle,
                descriptionProject: editProjectDescription,
                typeProject: editProjectType,
                priorityProject: editProjectPriority,
                environmentProject: editProjectEnvironment
            };
            
            // Realizar la solicitud PUT para actualizar el ticket
            fetch(url, {
                method: 'PUT',
                headers: {
                'Content-Type': 'application/json'
                },
                body: JSON.stringify(editProject)
            })
            .then(response => {
                if (!response.ok) {
                throw new Error('Error al actualizar el ticket');
                }
                // Si la actualización es exitosa, cerrar el modal
                var editModalProject = bootstrap.Modal.getInstance(document.getElementById('editModalProject'));
                editModalProject.hide();
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

    //Asignar Project Modal
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('saveAssignButtonProject').addEventListener('click', function() {
            
            var employeeAssign = document.querySelector('.assignedEmployee').value;
            var url = `/projects/${project.idProject}/assign`;
            
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
        document.getElementById('assignToMeButtonProject').addEventListener('click', function() {
        debugger
            var selectedTeam = `${project.teamNameAssign}`;
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
        document.getElementById('quitAssignProject').addEventListener('click', function() {
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
            var assignToMeButton = document.getElementById('assignToMeButtonProject');
            var quitAssignButton = document.getElementById('quitAssignProject');
            var selectElement = document.querySelector('.assignedEmployee');

            // Verificar si el clic no proviene del botón "Asignarme a mí", el botón "Quitar Asignación" ni el select
            if (clickedElement !== assignToMeButton && clickedElement !== quitAssignButton && clickedElement !== selectElement) {
                alertMessageAssignProject.style.display = 'none';
            }
        });


    });
    
    //Clone Project
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('cloneProjectButton').addEventListener('click', function() {
                
            // Obtener los valores editados del formulario
            var url = `/projects/create`;
            var cloneProjectTitle = document.getElementById('cloneProjectTitle').value;
            var cloneProjectDescription = document.getElementById('cloneProjectDescription').value;
            var cloneProjectPriority = document.getElementById('cloneProjectPriority').value;
            var cloneProjectType = document.getElementById('cloneProjectType').value;
            var cloneProjectReleaseName = document.getElementById('cloneProjectReleaseName').value;
            var cloneProjectEnvironment = document.getElementById('cloneProjectEnvironment').value;
            var cloneProjectTeamNameAssign = document.getElementById('cloneProjectTeamNameAssign').value;
            debugger
            // Crear un objeto con los datos actualizados del ticket
            var cloneProject = {
                titleProject: cloneProjectTitle,
                descriptionProject: cloneProjectDescription,
                typeProject: cloneProjectType,
                priorityProject: cloneProjectPriority,
                releaseName: cloneProjectReleaseName,
                environmentProject: cloneProjectEnvironment,
                teamNameAssign: cloneProjectTeamNameAssign
            };
            
            // Realizar la solicitud POST para crear el ticket
            fetch(url, {
                method: 'POST',
                headers: {
                'Content-Type': 'application/json'
                },
                body: JSON.stringify(cloneProject)
            })
            .then(response => {
                if (!response.ok) {
                throw new Error('Error al crear el ticket');
                }
                // Si es exitosa, cerrar el modal
                var cloneModalProject = bootstrap.Modal.getInstance(document.getElementById('cloneModalProject'));
                cloneModalProject.hide();
                document.body.classList.remove('modal-open');
                document.querySelector('.modal-backdrop').remove();
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
        var formattedInitialDate = formatDateTime(`${project.initialDate}`);
        var formattedModifyDate = formatDateTime(`${project.modifyDate}`);
        var formattedFinishDate = formatFinishDate(`${project.finishDate}`);

        // Actualizar el contenido HTML con las fechas formateadas
        document.getElementById("initialDate").textContent = formattedInitialDate;
        document.getElementById("modifyDate").textContent = formattedModifyDate;
        document.getElementById("finishDate").textContent = formattedFinishDate;

    });

    //Cambiar estado Project
    document.addEventListener('DOMContentLoaded', function() {
        var changeStatusButtonsProject = document.querySelectorAll('.collapse-item.project-item');
        
        changeStatusButtonsProject.forEach(function(button) {
            button.addEventListener('click', function(event) {
                var status = event.target.textContent.trim();

                var url = `/projects/${project.idProject}/edit`;

                var updatedProject = {
                    statusProject: status
                };

                fetch(url, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(updatedProject)
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error al actualizar el Project');
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

    //Delete Project
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('deleteTicketButtonProject').addEventListener('click', function() {
            var url = `/projects/${project.idProject}/delete`;
            
            // Realizar la solicitud DELETE para eliminar el ticket
            fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(`${project.idProject}`)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al eliminar el ticket');
                }
                var modalHeader = document.getElementById('modal-header-delete-project');
                var modalBody = document.getElementById('modal-body-delete-project');
                var modalFooter = document.getElementById('modal-footer-delete-project');
                modalBody.classList.add('d-none'); // Ocultar el modal
                modalFooter.classList.add('d-none'); 
                modalHeader.classList.add('d-none');

                // Si la eliminación es exitosa, mostrar mensaje de éxito y ocultar el cuerpo y el pie de página del modal
                document.getElementById('successMessageDeleteProject').style.display = 'block';

                // Cerrar el modal después de 3 segundos y redirigir a la página relevante
                setTimeout(function(){
                    var deleteModalProject = new bootstrap.Modal(document.getElementById('deleteModalProject'));
                    deleteModalProject.hide();
                    
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

    //Asignar Project Link
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('assignToMeLinkProject').addEventListener('click', function() {
            
            var employeeAssignToMe = `${employee.userEmployee}`;
            var url = `/projects/${project.idProject}/assign`;
            
            fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(employeeAssignToMe)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al asignar el proyecto');
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

</script>